package in.ankushs.browscap4j.domain;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import gnu.trove.map.TCharObjectMap;
import gnu.trove.map.hash.TCharObjectHashMap;
import in.ankushs.browscap4j.service.ResourceBuilder;
import in.ankushs.browscap4j.utils.PreConditions;

/**
 * @author Ankush Sharma
 */
public class Browscap {

    private static final Logger LOGGER = LoggerFactory.getLogger(Browscap.class);

    /*
     * A flag for indicating whether the browscap.csv file has been loaded into
     * memory. Its value is true if data has been loaded,and false otherwise
     */
    private static boolean allLoaded;

    /*
     * Name patterns(from the browscap.csv file) as key and its capabilities in
     * the form of BrowserCapabilities object as value .
     */
    private static Map<String, BrowserCapabilities> cache;

    private static ResourceBuilder resourceBuilder;

    private static Trie tree = new Trie();

    /**
     * Create a new Browscap instance. Once an instance has been created, the
     * allLoaded flag is set to true. Any futher initializations of the Browscap
     * object will not load data into memory again.Processing will be parallel
     * if @param enableParalle is true
     *
     * @param csvFile The browscap.csv file as a File object.
     * @throws IllegalArgumentException if {@code csvFile} does not exist.
     */
    public Browscap(final File file) {
        this(new BrowsCapConfig(file));
    }

    public Browscap(BrowsCapConfig config) {
        config.afterPropertiesSet();
        resourceBuilder = new ResourceBuilder(config);
        if (!allLoaded) {
            loadData();
            allLoaded = true;
        } else {
            LOGGER.debug("Data has already been loaded!");
        }

    }

    /**
     * Load data from the BrowscapFile
     */
    private void loadData() {
        LOGGER.info("Loading data ");
        Map<String, BrowserCapabilities> localCache;
        Trie localTree = new Trie();
        List<String> patterns = resourceBuilder.getNamePatterns();
        LOGGER.debug("Loaded {} patterns", patterns.size());
        localTree.makeTrie(patterns);
        localCache = resourceBuilder.getNamePatternsToBrowserCapabilitiesMap();
        LOGGER.info("Finished loading local data");
        tree = localTree;
        cache = localCache;
        LOGGER.info("Finished loading data");
    }

    /**
     * The main API method . Return the capabilities of a user agent.
     *
     * @param userAgent the user agent being queried.
     * @return null if no capabilities found for {@code userAgent} ,and a loaded
     * BrowserCapabilities object otherwise.
     */
    public BrowserCapabilities lookup(final String userAgent) throws Exception {
        PreConditions.checkNull(userAgent, "Cannot pass a null UserAgent String ! ");
        LOGGER.debug("Attempting to find BrowserCapabilities for User Agent String {}", userAgent);
        if (resourceBuilder.checkFile()) {
            loadData();
        }
        BrowserCapabilities browserCapabilities = resolve(userAgent);
        if (browserCapabilities == null) {
            browserCapabilities = new BrowserCapabilities.Builder().build();
        }
        return browserCapabilities;
    }

    private BrowserCapabilities resolve(final String userAgent) throws Exception {
        final String namePattern = getPattern(userAgent);
        final BrowserCapabilities browserCapabilities = cache.get(namePattern);
        LOGGER.debug("BrowserCapabilities {} found for user agent string {} ", browserCapabilities, userAgent);
        return browserCapabilities;
    }

    static abstract class AbstractNode {

        protected String leaf = null;

        protected short minLengthOfUserAgentSuffix;

        protected final char nodeChar;

        protected BitSetWithMask requiredCharacters;

        public AbstractNode(final char c) {
            this.nodeChar = c;
        }

        public abstract List<String> getLeafs();

        public abstract boolean hasChildren();

        public abstract void populateNextCheckNodes(char c, Collection<AbstractNode> nextToCheck);
    }

    private static final char ASTERIX = '*';
    private static final char QUESTION = '?';

    static class Node extends AbstractNode {

        private AbstractNode asterixNode = null;

        private TCharObjectMap<AbstractNode> children = new TCharObjectHashMap<>();

        private AbstractNode questionNode = null;

        public Node(final char c) {
            super(c);
        }

        private char calcMaxChar() {
            char max = this.nodeChar;
            for (final AbstractNode child : children.valueCollection()) {
                max = (char) Math.max(max, ((Node) child).calcMaxChar());
            }
            return max;
        }

        private short calcMinLengthOfUserAgentSuffix() {
            if (leaf != null) {
                this.minLengthOfUserAgentSuffix = 0;
                return 0;
            }
            short min = Short.MAX_VALUE;
            for (final AbstractNode child : children.valueCollection()) {
                short childValue = ((Node) child).calcMinLengthOfUserAgentSuffix();
                if (this.nodeChar != ASTERIX) {
                    childValue++;
                }
                min = (short) Math.min(childValue, min);
            }
            this.minLengthOfUserAgentSuffix = min;
            return min;
        }

        private BitSetWithMask calcRequiredCharacters() {
            if (leaf != null) {
                // still need to calc required chars for children
                for (final AbstractNode child : children.valueCollection()) {
                    ((Node) child).calcRequiredCharacters();
                }

                return requiredCharacters = new BitSetWithMask();
            }
            BitSetWithMask result = null;
            for (final AbstractNode child : children.valueCollection()) {
                final BitSetWithMask childResult = (BitSetWithMask) (((Node) child).calcRequiredCharacters()).clone();
                if (child.nodeChar != ASTERIX && child.nodeChar != QUESTION) {
                    childResult.set(child.nodeChar);
                }
                if (result == null) {
                    result = childResult;
                } else {
                    result.and(childResult);
                }
            }

            return this.requiredCharacters = result;
        }

        public List<String> getLeafs() {
            final List<String> result = new ArrayList<>(2);
            if (StringUtils.isNotBlank(this.leaf)) {
                result.add(leaf);
            }
            if (this.asterixNode != null) {
                result.addAll(this.asterixNode.getLeafs());
            }
            return result;
        }

        public int getMinLengthOfUserAgentSuffix() {
            return minLengthOfUserAgentSuffix;
        }

        @Override
        public boolean hasChildren() {
            return !children.isEmpty();
        }

        public void insertPattern(final String pattern, final char[] cs, final int i) {
            if (i == cs.length) {
                // this is the end of pattern
                if (this.leaf != null) {
                    throw new IllegalArgumentException("Duplicate pattern: '" + pattern + "'");
                }
                this.leaf = pattern;
                return;
            }

            final char c = cs[i];

            Node charNode = (Node) children.get(c);
            if (charNode == null) {
                charNode = new Node(c);
                children.put(c, charNode);
            }
            charNode.insertPattern(pattern, cs, i + 1);

            this.asterixNode = (Node) children.get(ASTERIX);
            this.questionNode = (Node) children.get(QUESTION);
        }

        private void optimize() {
            final Map<BitSetWithMask, BitSetWithMask> bitSets = new HashMap<>(1 << 18);
            optimizeImpl(bitSets);
        }

        public void optimizeImpl(final Map<BitSetWithMask, BitSetWithMask> bitSets) {
            for (final AbstractNode child : children.valueCollection()) {
                ((Node) child).optimizeImpl(bitSets);
            }

            for (final char c : children.keys()) {
                final Node child = (Node) children.get(c);
                if (child.children.size() == 1) {
                    final SingleChildNode singleChildNode = new SingleChildNode(child);
                    children.put(c, singleChildNode);
                    if (c == ASTERIX) {
                        this.asterixNode = singleChildNode;
                    } else if (c == QUESTION) {
                        this.questionNode = singleChildNode;
                    }
                }
            }

            if (children.size() == 1) {
                final TCharObjectMap<AbstractNode> singletonMap = new TCharObjectHashMap<>(1);
                singletonMap.put(children.keys()[0], (AbstractNode) children.values()[0]);

                this.children = singletonMap;
            } else if (children.size() == 0) {

                this.children = new TCharObjectHashMap<>(0);
            } else {
                this.children = new TCharObjectHashMap<>(this.children);
            }
            if (bitSets.containsKey(this.requiredCharacters)) {
                this.requiredCharacters = bitSets.get(this.requiredCharacters);
            } else {
                bitSets.put(this.requiredCharacters, this.requiredCharacters);
            }
        }

        public void populateNextCheckNodes(final char c, final Collection<AbstractNode> nextToCheck) {
            final AbstractNode byChar = children.get(c);
            if (byChar != null) {
                nextToCheck.add(byChar);
            }

            if (asterixNode != null) {
                asterixNode.populateNextCheckNodes(c, nextToCheck);
            }

            if (questionNode != null) {
                nextToCheck.add(questionNode);
            }

            if (nodeChar == ASTERIX) {
                nextToCheck.add(this);
            }
        }

        @Override
        public String toString() {
            return this.nodeChar + "=>[" + new String(this.children.keys()) + "]; " + this.leaf;
        }

    }

    static class SingleChildNode extends AbstractNode {

        private final AbstractNode child;

        public SingleChildNode(final Node src) {
            super(src.nodeChar);
            this.leaf = src.leaf;
            this.minLengthOfUserAgentSuffix = src.minLengthOfUserAgentSuffix;
            this.requiredCharacters = src.requiredCharacters;
            this.child = (AbstractNode) src.children.values()[0];
        }

        public List<String> getLeafs() {
            final List<String> result = new ArrayList<>(2);
            if (StringUtils.isNotBlank(this.leaf)) {
                result.add(leaf);
            }
            if (this.child.nodeChar == ASTERIX) {
                result.addAll(this.child.getLeafs());
            }
            return result;
        }

        @Override
        public boolean hasChildren() {
            return true;
        }

        public void populateNextCheckNodes(final char c, final Collection<AbstractNode> nextToCheck) {
            if (this.child.nodeChar == c || this.child.nodeChar == QUESTION) {
                nextToCheck.add(this.child);
            }

            if (this.child.nodeChar == ASTERIX) {
                this.child.populateNextCheckNodes(c, nextToCheck);
            }

            if (nodeChar == ASTERIX) {
                nextToCheck.add(this);
            }
        }

    }

    static class Trie {

        private int maxPatternChar = 127;

        private final Node root = new Node((char) 0);

        public List<String> getMatchedPatterns(final String userAgent) {
            final int userAgentLength = userAgent.length();
            if (userAgentLength >= Short.MAX_VALUE) {
                return Collections.singletonList("*");
            }

            final short[] charCounters = new short[maxPatternChar + 1];
            final BitSetWithMask charPresence = new BitSetWithMask(maxPatternChar);
            for (int i = 0; i < userAgentLength; i++) {
                char c = userAgent.charAt(i);
                if (c <= maxPatternChar) {
                    charCounters[c]++;
                    charPresence.set(c);
                }
            }

            final List<String> leafs = new ArrayList<>();

            final List<AbstractNode> toCheck = new ArrayList<>();
            toCheck.add(this.root);
            int currentChar = -1;
            final List<AbstractNode> nextToCheck = new ArrayList<>();

            while (!toCheck.isEmpty()) {
                currentChar++;
                final int uaCharsLeft = userAgentLength - currentChar;

                if (0 == uaCharsLeft) {
                    for (final AbstractNode node : toCheck) {
                        leafs.addAll(node.getLeafs());
                    }
                    break;
                }

                final char c = userAgent.charAt(currentChar);
                final int toCheckSize = toCheck.size();
                for (int i = 0; i < toCheckSize; i++) {
                    toCheck.get(i).populateNextCheckNodes(c, nextToCheck);
                }

                if (nextToCheck.isEmpty()) {
                    break;
                }

                toCheck.clear();

                if (c <= maxPatternChar) {
                    if (--charCounters[c] == 0) {
                        charPresence.clear(c);
                    }
                }

                final int nextToCheckSize = nextToCheck.size();
                for (int i = 0; i < nextToCheckSize; i++) {
                    final AbstractNode node = nextToCheck.get(i);
                    if (node.nodeChar == ASTERIX && !node.hasChildren()) {
                        leafs.addAll(node.getLeafs());
                    } else if (uaCharsLeft >= node.minLengthOfUserAgentSuffix
                            && charPresence.matchedMask(node.requiredCharacters)) {
                        toCheck.add(node);
                    }
                }
                nextToCheck.clear();
            }

            return leafs;
        }

        public void makeTrie(final Collection<String> patterns) {
            for (final String pattern : patterns) {
                root.insertPattern(pattern, pattern.toCharArray(), 0);
            }
            this.root.calcMinLengthOfUserAgentSuffix();
            this.maxPatternChar = this.root.calcMaxChar();
            this.root.calcRequiredCharacters();
            this.root.optimize();
        }
    }

    public String getPattern(final String userAgent) throws Exception {
        final List<String> patterns = tree.getMatchedPatterns(userAgent).stream().sorted((p1, p2) -> {
            return -Integer.compare(p1.length(), p2.length());
        }).collect(Collectors.toList());
        return patterns.get(0);
    }

    public static void main(String[] args) throws Exception {
        BrowsCapConfig config = new BrowsCapConfig(new File("D:\\source\\Browscap4j\\src\\test\\resources\\browscap.json"));
        Browscap b = new Browscap(config);
        b.lookup("Mozilla/5.0 (iPad; CPU OS 7_0 like Mac OS X) AppleWebKit/537.51.1 (KHTML, like Gecko) Version/7.0 Mobile/11A465 Safari/9537.53");
    }

}

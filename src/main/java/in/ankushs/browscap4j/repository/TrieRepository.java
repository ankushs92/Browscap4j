package in.ankushs.browscap4j.repository;


import com.google.common.collect.Interner;
import com.google.common.collect.Interners;
import in.ankushs.browscap4j.domain.Bits;
import in.ankushs.browscap4j.domain.*;
import in.ankushs.browscap4j.utils.PreConditions;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Stream;

/**
 * Created by ankushsharma on 04/12/17.
 */
@Slf4j
public class TrieRepository implements Repository {

    private TrieRepository() {
    }

    private static final TrieRepository trieRepo = new TrieRepository();

    //We'll use interner for fields that have repeating values. DO NOT USE for high cardinality fields!
    private static final Interner<String> interner = Interners.newWeakInterner();

    public static TrieRepository getInstance() {
        return trieRepo;
    }

    private static final Trie trie = new Trie();
    private static final Map<String, BrowserCapabilities> CACHE = new HashMap<>();

    @Override
    public void build(final File file) {
        PreConditions.notNull(file, "file cannot be null");

        try (final InputStream is = new FileInputStream(file);
             final BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            br.lines()
                    .skip(2) //Skip the first line
                    .forEach(line -> {
                        //Split by comma, and strip the strings of ""
                        final String[] array = line.split(",");
                        final String[] sanitizedArray = Stream.of(array)
                                .map(string -> string.replace("\"", ""))
                                .toArray(String[]::new);

                        final String namePattern = sanitizedArray[0];
                        final BrowserCapabilities browserCapabilities =
                                BrowserCapabilities
                                    .builder()
                                    .browser(sanitizedArray[5])
                                    .browserType(BrowserType.from(interner.intern(sanitizedArray[6])))
                                    .browserBits(Bits.from(interner.intern(sanitizedArray[7])))
                                    .browserMaker(sanitizedArray[8])
                                    .browserVersion(sanitizedArray[10])
                                    .deviceBrandName(sanitizedArray[46])
                                    .deviceMaker(sanitizedArray[42])
                                    .deviceName(sanitizedArray[41])
                                    .devicePointingMethod(DevicePointingMethod.from(interner.intern(sanitizedArray[44])))
                                    .deviceType(DeviceType.from(interner.intern(sanitizedArray[43])))
                                    .platform(sanitizedArray[13])
                                    .platformBits(Bits.from(interner.intern(sanitizedArray[16])))
                                    .platformMaker(sanitizedArray[17])
                                    .platformVersion(sanitizedArray[14])
                                .build();

                        CACHE.put(namePattern, browserCapabilities);

                        //Also, load up the trie!
                        trie.insert(namePattern);
                    });
        } catch (final Exception ex) {
            log.error("", ex);
        }
    }

    @Override
    public BrowserCapabilities lookup(final String userAgent) {
        PreConditions.notEmptyString(userAgent, "userAgent cannot be null or empty");
        //The longest name pattern is the winner
        final String namePattern = trie.searchForNamePatterns(userAgent.toLowerCase().trim())
                                        .stream()
                                        .sorted(Comparator.comparingInt(String::length))
                                        .findFirst()
                                        .orElse("DefaultProperties");

        log.debug("Name pattern : {}", namePattern);
        return CACHE.get(namePattern);
    }

    private static class Node {

        Map<Character, Node> children;
        boolean isLeaf;

        Node() {
            children = new HashMap<>();
            isLeaf = false;
        }

    } 

    private static class Trie {
        private final Node root;

        Trie() {
            //Root node
            this.root = new Node();
        }

        void insert(final String word) {
            Node current = root;
            for (int i = 0; i < word.length(); i++) {
                final char ch = word.charAt(i);
                Node node = current.children.get(ch);
                if (Objects.isNull(node)) {
                    node = new Node();
                    current.children.put(ch, node);
                }
                current = node;
            }
            current.isLeaf = true;
        }

        Set<String> searchForNamePatterns(final String userAgent) {
            PreConditions.notEmptyString(userAgent, "userAgent cannot be null or empty");
            final Set<String> namePatterns = new HashSet<>();
            final int length = userAgent.length();
            Node current = root;

            for(final Entry<Character,Node> entry : current.children.entrySet()){
                final char prefix = entry.getKey();
                Node node = entry.getValue();

                for(int i = 0 ; i < length; i++){
                    final char ch = userAgent.charAt(i);
                    switch(ch){
                        case '*' : {
                            break;
                        }
                        case '?' : {
                            break;
                        }
                        default : {
                            if(ch == prefix){
                                if(node.isLeaf){
//                                    namePatterns.add
                                }
                            }
                            else{
                                //Break the loop
                                break;
                            }
                        }
                    }
                }
            }
            log.debug("Name Patterns Candidates : {} ", namePatterns);
            return namePatterns;
        }
    }



    public static void main(String[] args) throws InterruptedException {
//        final File file = new File("/Users/ankushsharma/Downloads/browscap.csv");
//        val trie = TrieRepository.getInstance();
//        long start = System.currentTimeMillis();
//        trie.build(file);
//        long stop = System.currentTimeMillis();
//        System.out.println(stop - start);
//        Thread.sleep(100000);
//        System.out.println(trie.lookup("Ankush"));

        System.out.println("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.103 Safari/537.36".toLowerCase().trim());

    }


}

package in.ankushs.browscap4j.domain;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.ankushs.browscap4j.service.ResourceBuilder;
import in.ankushs.browscap4j.utils.PreConditions;

/**
 * 
 * @author Ankush Sharma
 *
 */
public class Browscap {

	private static final Logger logger = LoggerFactory.getLogger(Browscap.class);
	private static final String UNKNOWN = "Unknown";

	/*
	 * A flag for indicating whether the browscap.csv file has been loaded into
	 * memory. Its value is true if data has been loaded,and false otherwise
	 */
	private static boolean allLoaded;

	/*
	* Whether to enable parallel processing or not.
	* */
	private final boolean enableParallel;

	private ResourceBuilder resourceBuilder;

	/*
	 * Name patterns(from the browscap.csv file) as key and already computed
	 * regex's as Pattern objects as value .
	 */
	private static Map<String, Pattern> namePatternToRegexMap;

	/*
	 * Name patterns(from the browscap.csv file) as key and its capabilities in
	 * the form of BrowserCapabilities object as value .
	 */
	private static Map<String, BrowserCapabilities> cache;

	/**
	 * Create a new Browscap instance. Once an instance has been created, the
	 * allLoaded flag is set to true. Any futher initializations of the Browscap
	 * object will not load data into memory again.By default,processing will be serial,
	 * and not parallel.
	 * 
	 * @param csvFile
	 *            The browscap.csv file as a File object.
	 * @throws IllegalArgumentException
	 *             if {@code csvFile} does not exist.
	 */
	public Browscap(final File csvFile) {
		this(csvFile,false);
	}

	/**
	 * Create a new Browscap instance. Once an instance has been created, the
	 * allLoaded flag is set to true. Any futher initializations of the Browscap
	 * object will not load data into memory again.Processing will be parallel
	 * if @param enableParalle is true
	 *
	 * @param csvFile
	 *            The browscap.csv file as a File object.
	 * @param enableParallel
	 * 			  flag that specifies whether the processing should be parallel or serial.If set to true,processing of browscap.csv will be done in parallel using Java 8 ParallelStreams.
	 * @throws IllegalArgumentException
	 *             if {@code csvFile} does not exist.
	 */
	public Browscap(final File csvFile, final boolean enableParallel){
		PreConditions.checkNull(csvFile,"csvFile cannot be null");
		PreConditions.checkExpression(!csvFile.exists(), "The csvFile does not exist");
		this.enableParallel = enableParallel;
		if (!allLoaded) {
			resourceBuilder = new ResourceBuilder(csvFile);
			logger.info("Loading data ");

			namePatternToRegexMap = resourceBuilder.getRegexNamePatternsMap();
			cache = resourceBuilder.getNamePatternsToBrowserCapabilitiesMap();

			logger.info("Finished loading data");
			allLoaded = true;
		} else {
			logger.debug("Data has already been loaded!");
		}

	}

	/**
	 * The main API method . Return the capabilities of a user agent.
	 * 
	 * @param userAgent
	 *            the user agent being queried.
	 * @return null if no capabilities found for {@code userAgent} ,and a loaded
	 *         BrowserCapabilities object otherwise.
	 */
	public BrowserCapabilities lookup(final String userAgent) {
		PreConditions.checkNull(userAgent, "Cannot pass a null UserAgent String ! ");
		logger.debug("Attempting to find BrowserCapabilities for User Agent String {}", userAgent);
		BrowserCapabilities browserCapabilities = resolve(userAgent);
		if (browserCapabilities == null) {
			browserCapabilities = new BrowserCapabilities.Builder().browser(UNKNOWN).deviceBrandName(UNKNOWN)
					.deviceCodeName(UNKNOWN).deviceName(UNKNOWN).deviceType(UNKNOWN).isMobile(false).isTablet(false)
					.platform(UNKNOWN).platformMaker(UNKNOWN).platformVersion(UNKNOWN).build();
		}
		return browserCapabilities;
	}

	private BrowserCapabilities resolve(final String userAgent) {
		// Java 8 Magic !
		final Set<Entry<String,Pattern>> set = namePatternToRegexMap.entrySet();
		Stream<Entry<String,Pattern>> stream = null;
		if(enableParallel){
			stream = set.parallelStream();
		}
		else{
			stream = set.stream();
		}
		final Optional<Entry<String, Pattern>> namePatternRegexEntry =
				 stream
				.filter(entry -> {
					final Matcher matcher = entry.getValue().matcher(userAgent);
					return matcher.matches();
				})
				.findFirst();

		if (!namePatternRegexEntry.isPresent()) {
			logger.debug("No Browsercapabilities found for user agent string {} ", userAgent);
			return null;
		}

		final String namePattern = namePatternRegexEntry.get().getKey();
		final BrowserCapabilities browserCapabilities = cache.get(namePattern);
		logger.debug("BrowserCapabilities {} found for user agent string {} ", browserCapabilities, userAgent);
		return browserCapabilities;
	}
}

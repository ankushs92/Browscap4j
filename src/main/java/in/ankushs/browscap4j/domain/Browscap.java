package in.ankushs.browscap4j.domain;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.ankushs.browscap4j.service.ResourceBuilder;
import in.ankushs.browscap4j.utils.PreConditions;

/**
 * 
 * @author Ankush Sharma
 *
 */
public final class Browscap {

	private static final Logger logger = LoggerFactory.getLogger(Browscap.class);
	private static final String UNKNOWN = "Unknown";

	/*
	 * A flag for indicating whether the browscap.csv file has been loaded into
	 * memory. Its value is true if data has been loaded,and false otherwise
	 */
	private static boolean allLoaded;

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
	 * object will not load data into memory again.
	 * 
	 * @param csvFile
	 *            The browscap.csv file as a File object.
	 * @throws IllegalArgumentException
	 *             if {@code csvFile} does not exist.
	 */
	public Browscap(final File csvFile) {

		PreConditions.checkExpression(!csvFile.exists(), "The csvFile does not exist");
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
		final Optional<Entry<String, Pattern>> namePatternRegexEntry = namePatternToRegexMap
				.entrySet()
				.parallelStream()
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

	public static void main(String[] args) {
		System.out.println("Here");
	}
}

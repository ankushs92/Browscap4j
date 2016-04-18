package com.browscap4j.domain;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.browscap4j.service.ResourceBuilder;
import com.browscap4j.utils.PreConditions;

public final class Browscap {

	private static final Logger logger = LoggerFactory.getLogger(Browscap.class);
	private final File csvFile;
	private static boolean allLoaded;
	
	private ResourceBuilder resourceBuilder;
	private static Map<String, Pattern> regexToNamePatternsMap;
	private static Map<String, BrowserCapabilities> cache;

	public Browscap(final File csvFile) {
		
		PreConditions.checkExpression(!csvFile.exists(), "The csvFile does not exist");
		this.csvFile = csvFile;
		if (!allLoaded) {
			resourceBuilder = new ResourceBuilder(csvFile);
			logger.info("Loading data ");

			regexToNamePatternsMap = resourceBuilder.getRegexNamePatternsMap();
			cache = resourceBuilder.getNamePatternsToBrowserCapabilitiesMap();

			logger.info("Finished loading data");
			allLoaded = true;
		}
		else {
			logger.debug("Data has already been loaded!");
		}
	}

	public BrowserCapabilities lookup(final String userAgent) {
		PreConditions.checkNull(userAgent, "Cannot pass a null UserAgent String ! ");
		logger.debug("Attempting to find BrowserCapabilities for User Agent String {}", userAgent);
		
		// Java 8 Magic !
		final Optional<Entry<String,Pattern>> namePatternRegexEntry = regexToNamePatternsMap
				.entrySet()
				.parallelStream() 
				.filter(entry -> {
					final Matcher matcher  = entry.getValue().matcher(userAgent);
					return matcher.matches();
				})
				.findFirst();
		
		if(!namePatternRegexEntry.isPresent()){
			logger.debug("No Browsercapabilities found for user agent string {} ", userAgent);
			return null;
		}
			
		final String namePattern = namePatternRegexEntry.get().getKey();
		final BrowserCapabilities browserCapabilities = cache.get(namePattern);
		logger.debug("BrowserCapabilities {} found for user agent string {} ", browserCapabilities, userAgent);

		return browserCapabilities;
	}
}

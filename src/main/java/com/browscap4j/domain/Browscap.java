package com.browscap4j.domain;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.browscap4j.service.ResourceBuilder;
import com.browscap4j.utils.PreConditions;

public final class Browscap {

	private static final Logger logger = LoggerFactory.getLogger(Browscap.class);

	private final File csvFile;
	private static boolean allLoaded;

	private ResourceBuilder resourceBuilder;
	private static Map<String, String> regexNamePatternsMap;
	private static Map<String, BrowserCapabilities> cache;

	public Browscap(final File csvFile) {
		PreConditions.checkExpression(!csvFile.exists(), "The csvFile does not exist");
		this.csvFile = csvFile;
		if (!allLoaded) {
			resourceBuilder = new ResourceBuilder(csvFile);
			logger.info("Loading data ");

			regexNamePatternsMap = resourceBuilder.getRegexNamePatternsMap();
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
		// Java 8 Magic !
		logger.debug("Attempting to find BrowserCapabilities for User Agent String {}", userAgent);
		final String namePattern = regexNamePatternsMap
					.entrySet()
					.parallelStream()
					.filter(entry -> userAgent.matches(entry.getValue()))
					.collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()))
					.keySet()
					.stream()
					.max((String n1, String n2) -> {
						return Integer.compare(n1.length(), n2.length());
					})
					.get();
		final BrowserCapabilities browserCapabilities = cache.get(namePattern);
		if (browserCapabilities == null) {
			logger.debug("No Browsercapabilities found for user agent string {} ", userAgent);
		} else {
			logger.debug("BrowserCapabilities {} found for user agent string {} ", browserCapabilities, userAgent);

		}
		return browserCapabilities;
	}
}

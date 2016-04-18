package com.browscap4j.service;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.browscap4j.domain.BrowserCapabilities;
import com.browscap4j.utils.Strings;

public final class ResourceBuilder {
	private static final Logger logger = LoggerFactory.getLogger(ResourceBuilder.class);
	private final File file;
	private final ParsingService parsingService = CsvParsingService.getInstance();

	private static final String UNKNOWN ="Unknown";
	
	public ResourceBuilder(final File file) {
		this.file = file;
	}

	public Map<String, Pattern> getRegexNamePatternsMap() {
		List<String[]> records = null;
		try {
			records = parsingService.getRecords(file);
		} catch (final IOException ex) {
			logger.error("", ex);
		}
		// The NamePatterns should be first sorted based on ascending order of
		// length.
		return records
			.stream()
			.sorted((String[] record1,String[] record2)->{
				return - Integer.compare(record1[0].length(), record2[0].length());
			})
			.collect(Collectors.toMap(record -> record[0], record -> Pattern.compile(RegexResolver.toRegex(record[0]),Pattern.CASE_INSENSITIVE),
					(n1,n2)->{
						throw new IllegalStateException(String.format("Duplicate key %s", n1));
					},
					LinkedHashMap::new));
	}

	public Map<String, BrowserCapabilities> getNamePatternsToBrowserCapabilitiesMap() {
		List<String[]> records = null;
		try {
			records = parsingService.getRecords(file);
		} catch (final IOException ex) {
			logger.error("", ex);
		}

		return records.stream()
				.collect(Collectors.toMap(record -> record[0], // Key
					record -> {
						final String namePattern = record[0];
						final String browser = Strings.hasText(record[5]) ? record[5] : UNKNOWN;
						final String deviceName = Strings.hasText(record[41]) ? record[41] : UNKNOWN;
						final String deviceType = Strings.hasText(record[43]) ? record[43] : UNKNOWN;
						final String deviceCodeName = Strings.hasText(record[45]) ? record[45] : UNKNOWN;
						final String deviceBrandName = Strings.hasText(record[46]) ? record[46] : UNKNOWN;
						final String platform = Strings.hasText( record[13]) ?  record[13] : UNKNOWN;
						final String platformMaker = Strings.hasText( record[17]) ?  record[17] : UNKNOWN;
						final Boolean isMobile = BooleanUtils.toBoolean(record[32]);
						final Boolean isTablet = BooleanUtils.toBoolean(record[33]);
						
						return new BrowserCapabilities.Builder().browser(browser).deviceCodeName(deviceCodeName)
								.deviceName(deviceName).deviceBrandName(deviceBrandName).deviceType(deviceType)
								.platform(platform).platformMaker(platformMaker).isTablet(isTablet).isMobile(isMobile)
								.build();
				}));
				
	}
}

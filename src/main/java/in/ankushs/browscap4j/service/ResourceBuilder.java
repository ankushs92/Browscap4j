package in.ankushs.browscap4j.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.ankushs.browscap4j.domain.BrowserCapabilities;
import in.ankushs.browscap4j.utils.Strings;

/**
 *
 * Builds and loads the browscap.csv file into appropriate data structures .
 * 
 * @author Ankush Sharma
 *
 */
public final class ResourceBuilder {
    private static final Logger logger = LoggerFactory.getLogger(ResourceBuilder.class);
    private final File file;
    private LocalDateTime releaseDate;
    private Long version;
    private final ParsingService parsingService = CsvParsingService.getInstance();
    private List<String[]> records;
    private static final String UNKNOWN = "Unknown";

    public ResourceBuilder(final File file) {
        this.file = file;
        try {
            this.version = parsingService.getVersion(file);
            this.releaseDate = parsingService.getReleaseDate(file);
            this.records = parsingService.getRecords(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a <a href=
     * "https://docs.oracle.com/javase/7/docs/api/java/util/LinkedHashMap.html">LinkedHashMap</a>
     * with name pattern as key and its regex representation in the form of a
     * <a href=
     * "https://docs.oracle.com/javase/7/docs/api/java/util/regex/Pattern.html">Pattern</a>
     * object as value.
     * 
     * @return a LinkedHashMap with name pattern as key and regex as Pattern
     *         object as value
     */
    // com.google.re2j.Pattern
    public List<String> getNamePatterns() {

        return records.stream()
                // The NamePatterns should be first sorted based on ascending
                // order of
                // length.
                .map(array -> {
                    return array[0];
                }).sorted((record1, record2) -> {
                    return -Integer.compare(record1.length(), record2.length());
                }).collect(Collectors.toList());
    }

    /**
     * Creates a <a href=
     * "https://docs.oracle.com/javase/7/docs/api/java/util/HashMap.html">HashMap</a>
     * with name pattern as key and browser capabilities as a
     * BrowserCapabilities object as value.
     * 
     * @return a HashMap with name pattern as key and BrowserCapabilities as
     *         value
     */
    public Map<String, BrowserCapabilities> getNamePatternsToBrowserCapabilitiesMap() {
        return records.stream().collect(Collectors.toMap(record -> record[0], // Key
                record -> {

                    final String browser = (Strings.hasText(record[5]) ? record[5] : UNKNOWN).intern();
                    final String browserType = (Strings.hasText(record[6]) ? record[6] : UNKNOWN).intern();
                    final String deviceName = (Strings.hasText(record[41]) ? record[41] : UNKNOWN).intern();
                    final String deviceType = (Strings.hasText(record[43]) ? record[43] : UNKNOWN).intern();
                    final String deviceCodeName = (Strings.hasText(record[45]) ? record[45] : UNKNOWN).intern();
                    final String deviceBrandName = (Strings.hasText(record[46]) ? record[46] : UNKNOWN).intern();
                    final String platform = (Strings.hasText(record[13]) ? record[13] : UNKNOWN).intern();
                    final String platformMaker = (Strings.hasText(record[17]) ? record[17] : UNKNOWN).intern();
                    final String platformVersion = (Strings.hasText(record[14]) ? record[14] : UNKNOWN).intern();
                    final boolean isMobile = Boolean.valueOf(record[32]);
                    final boolean isTablet = Boolean.valueOf(record[33]);

                    return new BrowserCapabilities.Builder().browser(browser).browserType(browserType)
                            .deviceCodeName(deviceCodeName).deviceName(deviceName).deviceBrandName(deviceBrandName)
                            .deviceType(deviceType).platform(platform).platformMaker(platformMaker)
                            .platformVersion(platformVersion).isTablet(isTablet).isMobile(isMobile).build();
                }));
    }

    public Long getVersion() {
        return version;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

}

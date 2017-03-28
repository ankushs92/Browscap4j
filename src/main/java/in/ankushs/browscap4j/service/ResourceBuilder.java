package in.ankushs.browscap4j.service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import in.ankushs.browscap4j.domain.BrowserCapabilities;

/**
 * Builds and loads the browscap.csv file into appropriate data structures .
 * 
 * @author Ankush Sharma
 */
public final class ResourceBuilder {
    private final LocalDateTime releaseDate;
    private final Long version;
    private final ParsingService parsingService;

    public ResourceBuilder(final File file) {
        this.parsingService = ParsingServiceFactory.getParsingService(file);
        this.version = parsingService.getVersion();
        this.releaseDate = parsingService.getReleaseDate();
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
     * object as value
     */
    // com.google.re2j.Pattern
    public List<String> getNamePatterns() {
        return parsingService.getNamePatterns();
    }

    /**
     * Creates a <a href=
     * "https://docs.oracle.com/javase/7/docs/api/java/util/HashMap.html">HashMap</a>
     * with name pattern as key and browser capabilities as a
     * BrowserCapabilities object as value.
     * 
     * @return a HashMap with name pattern as key and BrowserCapabilities as
     * value
     */
    public Map<String, BrowserCapabilities> getNamePatternsToBrowserCapabilitiesMap() {
        return parsingService.getNamePatternsToBrowserCapabilitiesMap();
    }

    public Long getVersion() {
        return version;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

}

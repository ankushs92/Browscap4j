package in.ankushs.browscap4j.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import in.ankushs.browscap4j.domain.BrowserCapabilities;

/**
 * @author Ankush Sharma
 */
public interface ParsingService {

    static final String UNKNOWN = "Unknown";
    static final String RELEASE_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss Z";

    Long getVersion();

    LocalDateTime getReleaseDate();

    List<String> getNamePatterns();

    Map<String, BrowserCapabilities> getNamePatternsToBrowserCapabilitiesMap();

}

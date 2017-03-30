package in.ankushs.browscap4j.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.ankushs.browscap4j.domain.BrowsCapConfig;
import in.ankushs.browscap4j.domain.BrowserCapabilities;
import in.ankushs.browscap4j.utils.PreConditions;

/**
 * Builds and loads the browscap.csv file into appropriate data structures .
 * 
 * @author Ankush Sharma
 */
public final class ResourceBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceBuilder.class);

    private final LocalDateTime releaseDate;
    private final Long version;
    private final ParsingService parsingService;

    private final BrowsCapConfig config;

    public ResourceBuilder(BrowsCapConfig config) {
        this.config = config;
        checkFile();
        this.parsingService = ParsingServiceFactory.getParsingService(config.getFile());
        this.version = parsingService.getVersion();
        this.releaseDate = parsingService.getReleaseDate();
    }

    /**
     * Check if the file exists and has content
     * 
     * @param file
     * @return true if the file exists and has content
     */
    public static boolean isFileNotEmpty(final File file) {
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file));) {
                return br.readLine() != null;
            } catch (IOException e) {
                LOGGER.error("Failed to check validity of {}", file.getAbsolutePath());
            }
        }
        return false;
    }

    private Long getRemoteVersion() {
        Long remoteVersion = null;
        try {
            URL versionNumberUrl = config.getVersionNumberURL();
            HttpURLConnection versionNumberCon = (HttpURLConnection) versionNumberUrl.openConnection();
            if (versionNumberCon.getResponseCode() == 200) {
                remoteVersion = Long.valueOf(IOUtils.toString(versionNumberCon.getInputStream(), "UTF-8"));
            }
        } catch (IOException e) {
            LOGGER.error("Failed to get remote version with error {}", e.getMessage());
        }
        PreConditions.checkNull(remoteVersion, "Could'nt get remote version");
        return remoteVersion;
    }

    public boolean isOutDated() {
        if (System.currentTimeMillis() - config.getFile().lastModified() >= config.getUpdateInterval()) {
            return version != null && version < getRemoteVersion();
        }
        return false;
    }

    /**
     * Update the browscapFile when the autoUpdate is allowed
     * 
     * @return true when the file has been updated
     */
    public boolean checkFile() {
        if (config.isAutoUpdate()) {
            if (!isFileNotEmpty(config.getFile())) {
                try {
                    LOGGER.debug("Downloading {}", config.getFile().getAbsolutePath());
                    FileUtils.copyURLToFile(config.getFileURL(), config.getFile());
                    config.getFile().setLastModified(System.currentTimeMillis());
                    LOGGER.debug("Downloaded {}", config.getFile().getAbsolutePath());
                    return true;
                } catch (IOException e) {
                    LOGGER.error("Failed to download from {}, to  {}  with {}", config.getFileURL(),
                            config.getFile().getAbsolutePath(), e.getMessage());
                }
            } else if (isOutDated()) {
                File tmpFile = new File(config.getFile().getAbsolutePath() + ".tmp");
                File oldFile = new File(config.getFile().getAbsolutePath() + ".old");
                try {
                    LOGGER.debug("Downloading {}", tmpFile.getAbsolutePath());
                    FileUtils.copyURLToFile(config.getFileURL(), tmpFile);
                    LOGGER.debug("Downloaded {}", tmpFile.getAbsolutePath());
                } catch (IOException e) {
                    LOGGER.error("Failed to download from {}, to  {}  with {}", config.getFileURL(),
                            config.getFile().getAbsolutePath(), e.getMessage());
                }
                try {
                    LOGGER.debug("Moving {} to {} ", config.getFile().getAbsolutePath(), oldFile.getAbsolutePath());
                    FileUtils.moveFile(config.getFile(), oldFile);
                    LOGGER.debug("Moved {} to {} ", config.getFile().getAbsolutePath(), oldFile.getAbsolutePath());
                    LOGGER.debug("Moving {} to {}", tmpFile.getAbsolutePath(), config.getFile().getAbsolutePath());
                    FileUtils.moveFile(tmpFile, config.getFile());
                    config.getFile().setLastModified(System.currentTimeMillis());
                    LOGGER.debug("Moved {} to {}", tmpFile.getAbsolutePath(), config.getFile().getAbsolutePath());
                    LOGGER.debug("Deleting {}", oldFile.getAbsolutePath());
                    oldFile.delete();
                    LOGGER.debug("Deleted {}", oldFile.getAbsolutePath());
                    return true;
                } catch (IOException e) {
                    LOGGER.error("Failed to move from {}, to  {}  with {}", tmpFile.getAbsolutePath(),
                            config.getFile().getAbsolutePath(), e.getMessage());
                }
            }
        }
        return false;
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

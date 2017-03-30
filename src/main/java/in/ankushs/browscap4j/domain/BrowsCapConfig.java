package in.ankushs.browscap4j.domain;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import in.ankushs.browscap4j.utils.PreConditions;

public class BrowsCapConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(BrowsCapConfig.class);

    public static final URL DEFAULT_VERSION_URL;

    /*
     * update interval of 5 days
     */
    public static final Long UPDATE_INTERVAL = 432000000L;

    static {
        URL temp;
        try {
            temp = new URL("http://browscap.org/version-number");
        } catch (MalformedURLException e) {
            temp = null;
        }
        DEFAULT_VERSION_URL = temp;
    }

    private boolean autoUpdate = false;

    private Long updateInterval = null;

    private URL fileURL = null;

    private File file = null;

    private URL versionNumberURL = null;

    public BrowsCapConfig() {
    }

    public BrowsCapConfig(File file) {
        PreConditions.checkNull(file, "file cannot be null");
        boolean validExtension = file.getAbsolutePath().endsWith(".csv") || file.getAbsolutePath().endsWith(".ini")
                || file.getAbsolutePath().endsWith(".json") || file.getAbsolutePath().endsWith(".xml");
        PreConditions.checkExpression(validExtension, "[.csv, .ini, .json, .xml] are the only allowed extensions");
        this.file = file;
    }

    public void afterPropertiesSet() {
        if (fileURL == null) {
            fileURL = BrowscapFileType.findByFileName(file.getAbsolutePath()).getUrl();
        }
        if (autoUpdate && versionNumberURL == null) {
            versionNumberURL = DEFAULT_VERSION_URL;
        }
        if (autoUpdate && updateInterval == null) {
            updateInterval = UPDATE_INTERVAL;
        }
    }

    public File getFile() {
        return file;
    }

    public URL getFileURL() {
        return fileURL;
    }

    public Long getUpdateInterval() {
        return updateInterval;
    }

    public URL getVersionNumberURL() {
        return versionNumberURL;
    }

    public boolean isAutoUpdate() {
        return autoUpdate;
    }

    public void setAutoUpdate(boolean autoUpdate) {
        this.autoUpdate = autoUpdate;
    }

    public void setFile(File file) {
        PreConditions.checkNull(file, "file cannot be null");
        boolean validExtension = file.getAbsolutePath().endsWith(".csv") || file.getAbsolutePath().endsWith(".ini")
                || file.getAbsolutePath().endsWith(".json") || file.getAbsolutePath().endsWith(".xml");
        PreConditions.checkExpression(validExtension, "[.csv, .ini, .json, .xml] are the only allowed extensions");
        this.file = file;
    }

    public void setFileURL(String fileURL) {
        try {
            this.fileURL = new URL(fileURL);
            this.autoUpdate = true;
        } catch (MalformedURLException e) {
            LOGGER.error("Invalid fileURL {}", fileURL);
        }
    }

    public void setFileURL(URL fileURL) {
        this.fileURL = fileURL;
        this.autoUpdate = true;
    }

    public void setUpdateInterval(Long updateInterval) {
        this.updateInterval = updateInterval;
        this.autoUpdate = true;
    }

    public void setVersionNumberURL(String versionNumberURL) {
        try {
            this.versionNumberURL = new URL(versionNumberURL);
            this.autoUpdate = true;
        } catch (MalformedURLException e) {
            LOGGER.error("Invalid versionNumberURL {}", versionNumberURL);
        }
    }

    public void setVersionNumberURL(URL versionNumberURL) {
        this.versionNumberURL = versionNumberURL;
        this.autoUpdate = true;
    }

}

package in.ankushs.browscap4j.domain;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import in.ankushs.browscap4j.utils.PreConditions;

public class BrowsCapConfig {

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

    public Long getUpdateInterval() {
        return updateInterval;
    }

    public void setUpdateInterval(Long updateInterval) {
        this.updateInterval = updateInterval;
    }

    private URL fileURL = null;

    private File file = null;

    private URL versionNumberURL = null;

    public BrowsCapConfig() {
    }

    public BrowsCapConfig(File file) {
        PreConditions.checkNull(file, "file cannot be null");
        this.file = file;
    }

    public void afterPropertiesSet() {
        if (versionNumberURL == null) {
            versionNumberURL = DEFAULT_VERSION_URL;
        }
        if (fileURL == null) {
            fileURL = BrowscapFileType.findByFileName(file.getAbsolutePath()).getUrl();
        }
        if (updateInterval == null) {
            updateInterval = UPDATE_INTERVAL;
        }
    }

    public void setAutoUpdate(boolean autoUpdate) {
        this.autoUpdate = autoUpdate;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setFileURL(URL fileURL) {
        this.fileURL = fileURL;
    }

    public void setVersionNumberURL(URL versionNumberURL) {
        this.versionNumberURL = versionNumberURL;
    }

    public File getFile() {
        return file;
    }

    public boolean isAutoUpdate() {
        return autoUpdate;
    }

    public URL getFileURL() {
        return fileURL;
    }

    public URL getVersionNumberURL() {
        return versionNumberURL;
    }

}

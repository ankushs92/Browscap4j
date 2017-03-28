package in.ankushs.browscap4j.domain;

import java.net.MalformedURLException;
import java.net.URL;

public enum BrowscapFileType {

    XML(".xml", "http://browscap.org/stream?q=BrowsCapXML"), JSON(".json",
            "http://browscap.org/stream?q=BrowsCapJSON"), CSV(".csv", "http://browscap.org/stream?q=BrowsCapCSV");

    private String extension;
    private URL url;

    BrowscapFileType(String extension, String spec) {
        this.extension = extension;
        try {
            this.url = new URL(spec);
        } catch (MalformedURLException e) {
        }
    }

    public String getExtension() {
        return extension;
    }

    public URL getUrl() {
        return url;
    }

    public static BrowscapFileType findByFileName(String filename) {
        for (BrowscapFileType type : BrowscapFileType.values()) {
            if (filename.endsWith(type.getExtension())) {
                return type;
            }
        }
        return CSV;
    }

}

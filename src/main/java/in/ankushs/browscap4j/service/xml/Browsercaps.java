package in.ankushs.browscap4j.service.xml;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "browsercaps")
@XmlAccessorType(XmlAccessType.FIELD)
public class Browsercaps {

    private static final String RELEASE_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss Z";

    @XmlElementWrapper(name = "gjk_browscap_version")
    @XmlElement(name = "item")
    private List<Item> browscapVersion;

    @XmlElementWrapper(name = "browsercapitems")
    @XmlElement(name = "browscapitem")
    private List<Browscapitem> browscapitems;

    private static Map<String, Item> versionAndReleaseDate;
    private static Long version;
    private static LocalDateTime releaseDate;

    private static Map<String, Browscapitem> map;

    @Override
    public String toString() {
        return "Browsercaps [browscapVersion=" + browscapVersion + ", browscapitems=" + browscapitems + "]";
    }

    public Map<String, Item> getBrowscapVersion() {
        if (versionAndReleaseDate == null) {
            versionAndReleaseDate = browscapVersion.stream()
                    .collect(Collectors.toMap(item -> item.getName(), item -> item));
        }
        return versionAndReleaseDate;
    }

    public Long getVersion() {
        if (version == null) {
            Item versionItem = getBrowscapVersion().get("Version");
            version = Long.valueOf(versionItem.getValue());
        }
        return version;
    }

    public LocalDateTime getReleaseDate() {
        if (releaseDate == null) {
            Item releaseItem = getBrowscapVersion().get("Released");
            DateTimeFormatter f = DateTimeFormatter.ofPattern(RELEASE_DATE_FORMAT, Locale.ENGLISH);
            releaseDate = LocalDateTime.parse(releaseItem.getValue(), f);
        }
        return releaseDate;
    }

    public List<String> getBrowscapitems() {
        return browscapitems.stream().map(browscapitem -> browscapitem.getName()).collect(Collectors.toList());
    }

    public Map<String, Browscapitem> getBrowscapitemsMap() {
        if (map == null) {
            map = browscapitems.stream()
                    .collect(Collectors.toMap(browscapitem -> browscapitem.getName(), browscapitem -> browscapitem));
        }
        return map;
    }

}

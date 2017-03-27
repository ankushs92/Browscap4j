package in.ankushs.browscap4j.xml;

import java.util.List;
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

    @XmlElementWrapper(name = "gjk_browscap_version")
    @XmlElement(name = "item")
    private List<Item> browscapVersion;

    @XmlElementWrapper(name = "browsercapitems")
    @XmlElement(name = "browscapitem")
    private List<Browscapitem> browscapitems;

    @Override
    public String toString() {
        return "Browsercaps [browscapVersion=" + browscapVersion + ", browscapitems=" + browscapitems + "]";
    }

    public Map<String, Item> getBrowscapVersion() {
        return browscapVersion.stream().collect(Collectors.toMap(item -> item.getName(), item -> item));
    }

    public List<Browscapitem> getBrowscapitems() {
        return browscapitems;
    }

}

package in.ankushs.browscap4j.service.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class Item {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private String value = "Unknown";

    @Override
    public String toString() {
        return "Item [name=" + name + ", value=" + value + "]";
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

}

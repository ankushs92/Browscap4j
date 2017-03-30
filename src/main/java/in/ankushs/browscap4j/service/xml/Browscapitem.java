package in.ankushs.browscap4j.service.xml;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Browscapitem {

    @XmlAttribute(name = "name")
    private String name;

    @XmlElement(name = "item")
    private List<Item> items;

    @Override
    public String toString() {
        return "Browscapitem [name=" + name + ", items=" + items + "]";
    }

    public String getName() {
        return name;
    }

    private Map<String, Item> getItems() {
        return items.stream().collect(Collectors.toMap(item -> item.getName(), item -> item));
    }

    public String getString(String key) {
        return getItems().containsKey(key) ? getItems().get(key).getValue() : null;
    }

    public Boolean getBoolean(String key) {
        return getItems().containsKey(key) ? Boolean.parseBoolean(getItems().get(key).getValue()) : null;
    }
    
    public Double getDouble(String key) {
        return getItems().containsKey(key) ? Double.valueOf(getItems().get(key).getValue()) : null;
    }

}

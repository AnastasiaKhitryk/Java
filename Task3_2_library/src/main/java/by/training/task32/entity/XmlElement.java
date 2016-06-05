package by.training.task32.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlElement {

    private String name;
    private String value;
    private Map<String, String> attributeMap = new HashMap<>();
    private List<XmlElement> children = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Map<String, String> getAttributeMap() {
        return attributeMap;
    }

    public void setAttributeMap(Map<String, String> attributeMap) {
        this.attributeMap = attributeMap;
    }

    public List<XmlElement> getChildren() {
        return children;
    }

    public void setChildren(List<XmlElement> children) {
        this.children = children;
    }
}

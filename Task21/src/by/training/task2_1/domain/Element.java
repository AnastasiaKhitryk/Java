package by.training.task2_1.domain;

import java.util.ArrayList;
import java.util.List;

public class Element{

    private String name;
    private AttributeList attributeList;
    private String value;
    private List<Element> childrenList;
    private Element parent;

    public Element(){
        childrenList = new ArrayList<Element>();
    }

    public Element getParent() {
        return parent;
    }

    public void setParent(Element parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AttributeList getAttributeList() {
        return attributeList;
    }

    public void setAttributeList(AttributeList attributeList) {
        this.attributeList = attributeList;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Element> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<Element> childrenList) {
        this.childrenList = childrenList;
    }

    public void setChildElement(Element child) {
        this.childrenList.add(child);
    }
}

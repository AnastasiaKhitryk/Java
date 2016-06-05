package by.training.task32.entity;

import java.util.ArrayList;
import java.util.List;

public class Document {

    private List<XmlElement> xmlElements = new ArrayList<>();

    public XmlElement getElementByName(String tagName) {
        for (XmlElement element : xmlElements) {
            if (element.getName().equals(tagName)) {
                return element;
            }
        }
        return null;
    }

    public XmlElement getElementByValue(String value) {
        for (XmlElement element : xmlElements) {
            if (element.getValue().equals(value)) {
                return element;
            }
        }
        return null;
    }

    public List<XmlElement> getXmlElements() {
        return xmlElements;
    }

    public void setXmlElements(List<XmlElement> xmlElements) {
        this.xmlElements = xmlElements;
    }

}

package by.training.task32.service;

import by.training.task32.entity.Document;
import by.training.task32.entity.XmlElement;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.nio.file.Files.newBufferedReader;

public class DomParser implements IParser {

    @Override
    public Document parse(Path xmlPath) {

        if (!Files.exists(xmlPath)){
            throw new RuntimeException("file exists!");
        }

        Document document = new Document();
        try (BufferedReader bytes = newBufferedReader(xmlPath)){
            document.setXmlElements(findXmlTags(bytes, false, ""));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return document;
    }

    public Document parse(String xmlPath) {
        return parse(Paths.get(xmlPath));
    }

    private static List<XmlElement> findXmlTags(BufferedReader bytes, boolean searchChildren, String parentName) throws IOException {
        List<XmlElement> xmlElementList = new ArrayList<>();
        XmlElement currentElement = new XmlElement();
        StringBuilder buffer = new StringBuilder();
        boolean currentTagInProcess = false;
        int charCode;

        while (true) {
            bytes.mark(1);
            charCode = bytes.read();

            if (charCode == -1) {
                break;
            }
            char ch = (char) charCode;
            if (ch == '<' && !currentTagInProcess) { //simple
                if ((char) bytes.read() != '/') {
                    bytes.reset();
                    String tagValue = readTagValue(bytes);
                    currentElement = new XmlElement();
                    if (extractNameAndAttributes(tagValue, currentElement)) {
                        xmlElementList.add(currentElement);
                    } else {
                        currentTagInProcess = true;
                    }
                }
            } else if (ch == '<' && currentTagInProcess) { //complex
                if (StringUtils.isNotBlank(buffer)) {       //value exists
                    currentElement.setValue(StringUtils.trim(buffer.toString()));
                    buffer.setLength(0);
                    xmlElementList.add(currentElement);
                    currentTagInProcess = false;
                } else {
                    bytes.reset();
                    currentElement.getChildren().addAll(findXmlTags(bytes, true, currentElement.getName()));
                    xmlElementList.add(currentElement);
                    currentTagInProcess = false;
                }
            } else if (ch == '/' || ch == '>') { //end of tag
                if (StringUtils.contains(buffer, parentName)) {
                    if (searchChildren) {
                        return xmlElementList;
                    }
                }
                buffer.setLength(0);
            } else {
                buffer.append(ch);
            }
        }

        return xmlElementList;
    }


    private static String readTagValue(BufferedReader bytes) throws IOException {
        StringBuilder result = new StringBuilder();
        int charCode;
        while (true) {
            charCode = bytes.read();
            if (charCode == -1) {
                break;
            }
            char ch = (char) charCode;
            if (ch == '>') {
                break;
            }
            if(ch == '<') {
                continue;
            }
            result.append(ch);
        }

        return result.toString();
    }

    private static boolean extractNameAndAttributes(String tagValue, XmlElement element) {
        boolean isSimple;
        if (isSimple = tagValue.endsWith("/")) {
            tagValue = tagValue.substring(0, tagValue.length() - 1);
        }

        tagValue = StringUtils.replace(tagValue, "\n", "");
        String[] attr = tagValue.split(" ");
        element.setName(attr[0]);

        Pattern p = Pattern.compile("[\\w:.-//\\w]{1,}=\"[\\w:.-//]{1,}\"");
        Matcher m = p.matcher(tagValue);
        while (m.find()) {
            String[] attribute = m.group().split("=");
            element.getAttributeMap().put(attribute[0], attribute[1].replace("\"", ""));
        }

        return isSimple;
    }
}

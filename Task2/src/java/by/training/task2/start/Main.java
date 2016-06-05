package java.by.training.task2.start;

import java.by.training.task2.service.DOMMenuParser;
import java.by.training.task2.entity.Category;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;

public class Main {

    private static final String PATH = "src/resourses/menu.xml";

    public static void main(String[] args) throws IOException, SAXException {

        DOMParser parser = new DOMParser();
        parser.parse(PATH);
        Document document = parser.getDocument();
        List<Category> menu = DOMMenuParser.extractCategoryList(document);

        System.out.println(menu);
    }
}

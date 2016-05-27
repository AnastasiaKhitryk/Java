package by.training.task3.parser.start;

import by.training.task3.parser.entity.Category;
import by.training.task3.parser.handler.MenuSaxHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;

public class SaxDemo {
    public static void main(String[] args) throws IOException, SAXException {
        XMLReader reader = XMLReaderFactory.createXMLReader();
        MenuSaxHandler handler = new MenuSaxHandler();
        reader.setContentHandler(handler);
        reader.parse(new InputSource("src/by/training/task3/parser/resource/menu.xml"));

        List<Category> menu = handler.getCategoryList();

        for(Category category: menu){
            System.out.println(category.getName());
        }
    }
}

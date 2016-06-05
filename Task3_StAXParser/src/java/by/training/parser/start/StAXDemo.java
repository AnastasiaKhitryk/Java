package java.by.training.parser.start;

import org.xml.sax.SAXException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.by.training.parser.domain.Category;
import java.by.training.parser.service.StAXMenuParser;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class StAXDemo {
    private static final String PATH = "src/resource/menu.xml";

    public static void main(String[] args) throws IOException, SAXException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
            InputStream input = new FileInputStream(PATH);
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);

            List<Category> menu = StAXMenuParser.process(reader);

            for (Category category : menu) {
                System.out.println(category.getId());
                System.out.println(category.getName());
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}

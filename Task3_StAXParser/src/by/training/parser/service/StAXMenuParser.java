package by.training.parser.service;

import by.training.parser.domain.ComplexDescription;
import org.xml.sax.SAXException;
import by.training.parser.domain.Category;
import by.training.parser.domain.Food;
import by.training.parser.domain.MenuTagName;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class StAXMenuParser {
    public static void main(String[] args) throws IOException, SAXException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        try {
            InputStream input = new FileInputStream("src/by/training/parser/resource/menu.xml");
            XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
            List<Category> menu = process(reader);

            for (Category category : menu) {
                System.out.println(category.getId());
                System.out.println(category.getName());
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    private static List<Category> process(XMLStreamReader reader) throws XMLStreamException {
        List<Category> menu = new ArrayList<Category>();
        Category category = null;
        Food food = null;
        String header = "";
        List<String> additions = new ArrayList<String>();
        List<Category> categoryList = new ArrayList<Category>();
        List<Food> foodList = new ArrayList<Food>();
        ComplexDescription complexDescription = null;

        MenuTagName elementName = null;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    elementName = MenuTagName.getElementTagName(reader.getLocalName());
                    switch (elementName) {
                        case CATEGORY:
                            category = new Category();
                            foodList = new ArrayList<Food>();
                            category.setId(reader.getAttributeValue(null, "id"));
                            category.setName(reader.getAttributeValue(null, "name"));
                            break;
                        case FOOD:
                            food = new Food();
                            food.setId(reader.getAttributeValue(null, "id"));
                            break;
                        case COMPLEXDESCRIPTION:
                            complexDescription = new ComplexDescription();
                            header = "";
                            additions = new ArrayList<String>();
                            break;
                    }
                    break;
                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();

                    if (text.isEmpty()) {
                        break;
                    }
                    switch (elementName) {
                        case NAME:
                            food.setName(text);
                            break;
                        case PHOTO:
                            food.setPhoto(text);
                            break;
                        case COUNT:
                            food.setCount(text);
                            break;
                        case DESCRIPTION:
                            food.setDescription(text);
                            break;
                        case ADDITION:
                            additions.add(text);
                            break;
                        case PORTION:
                            food.setPortion(text);
                            break;
                        case PRICE:
                            food.setPrice(text);
                            break;
                        case HEADER:
                            header = text;

                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    elementName = MenuTagName.getElementTagName(reader.getLocalName());
                    switch (elementName) {
                        case CATEGORY:
                            category.setFoods(foodList);
                            menu.add(category);
                            foodList = null;
                            break;
                        case COMPLEXDESCRIPTION:
                            complexDescription.setHeader(header);
                            complexDescription.setComplexDescription(additions);
                            food.setComplexDescription(complexDescription);
                            break;
                        case FOOD:
                            foodList.add(food);
                            food = null;
                            break;
                    }
            }
        }
        return menu;
    }
}

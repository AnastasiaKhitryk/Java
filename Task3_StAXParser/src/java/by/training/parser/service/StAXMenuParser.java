package java.by.training.parser.service;

import java.by.training.parser.domain.Food;
import java.by.training.parser.domain.Category;
import java.by.training.parser.domain.MenuTagName;
import java.by.training.parser.domain.ComplexDescription;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StAXMenuParser {

    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String EMPTY = " ";

    public static List<Category> process(XMLStreamReader reader) throws XMLStreamException {
        List<Category> menu = new ArrayList<>();
        Category category = null;
        Food food = null;
        ComplexDescription complexDescription = null;
        String header = EMPTY;
        List<String> additions = new ArrayList<>();
        List<Food> foodList = new ArrayList<>();


        MenuTagName elementName = null;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    elementName = MenuTagName.getElementTagName(reader.getLocalName());
                    switch (elementName) {
                        case CATEGORY:
                            category = new Category();
                            foodList = new ArrayList<>();

                            category.setId(reader.getAttributeValue(null, ID));
                            category.setName(reader.getAttributeValue(null, NAME));
                            break;
                        case FOOD:
                            food = new Food();
                            food.setId(reader.getAttributeValue(null, ID));
                            break;
                        case COMPLEXDESCRIPTION:
                            complexDescription = new ComplexDescription();
                            header = EMPTY;
                            additions = new ArrayList<>();
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

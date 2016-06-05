package java.by.training.task2.service;

import java.by.training.task2.entity.Category;
import java.by.training.task2.entity.ComplexDescription;
import java.by.training.task2.entity.ComplexPrice;
import java.by.training.task2.entity.Food;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOMMenuParser {

    private static final String CATEGORY = "category";
    private static final String FOOD = "food";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String PHOTO = "photo";
    private static final String COMPLEX_DESCRIPTION = "complexDescription";
    private static final String HEADER = "header";
    private static final String ADDITION = "addition";
    private static final String PORTION = "portion";
    private static final String DESCRIPTION = "description";
    private static final String COUNT = "count";
    private static final String PRICE = "price";
    private static final String COMPLEX_PRICE = "complexPrice";

    public static List<Category> extractCategoryList(Document document) throws IOException, SAXException {

        List<Category> menu = new ArrayList<>();

        Element root = document.getDocumentElement();
        NodeList categoryNodes = root.getElementsByTagName(CATEGORY);

        for (int i = 0; i < categoryNodes.getLength(); i++) {
            Category category = new Category();

            Element categoryElement = (Element) categoryNodes.item(i);
            category.setId(categoryElement.getAttribute(ID));
            category.setName(categoryElement.getAttribute(NAME));

            NodeList foodNodes = categoryElement.getElementsByTagName(FOOD);
            List<Food> foodList = extractFoodList(foodNodes);
            category.setFoods(foodList);

            menu.add(category);
        }

        return menu;
    }

    private static List<Food> extractFoodList(NodeList foodNodes) {
        List<Food> foodList = new ArrayList<Food>();

        for (int j = 0; j < foodNodes.getLength(); j++) {
            Food food = new Food();

            Element foodElement = (Element) foodNodes.item(j);

            food.setId((foodElement.getAttribute(ID)));
            food.setName((getSingleChildStringValue(foodElement, NAME)));
            food.setPhoto((getSingleChildStringValue(foodElement, PHOTO)));

            fillDescription(foodElement, food);
            filPrice(foodElement, food);
            fillCount(foodElement, food);

            foodList.add(food);
        }

        return foodList;
    }

    private static void fillDescription(Element foodElement, Food food) {
        if (isComplexElement(foodElement, COMPLEX_DESCRIPTION)) {
            ComplexDescription complexDescription = new ComplexDescription();

            complexDescription.setHeader((getSingleChildStringValue(foodElement, HEADER)));
            NodeList descriptionNodes = foodElement.getElementsByTagName(ADDITION);

            for (int i = 0; i < descriptionNodes.getLength(); i++) {
                String context = descriptionNodes.item(i).getTextContent();
                if (context != null) {
                    complexDescription.setDescription(context);
                }
            }
            food.setComplexDescription(complexDescription);
        } else {
            food.setDescription((getSingleChildStringValue(foodElement, DESCRIPTION)));
        }
    }


    private static String getSingleChildStringValue(Element element, String childName) {

        NodeList nodeList = element.getElementsByTagName(childName);
        Element child = (Element) nodeList.item(0);

        return child.getTextContent().trim();
    }

    private static void filPrice(Element foodElement, Food food) {
        if (isComplexElement(foodElement, COMPLEX_PRICE)) {

            ComplexPrice complexPrice = new ComplexPrice();
            NodeList priceNodes = foodElement.getElementsByTagName(PRICE);

            for (int k = 0; k < priceNodes.getLength(); k++) {
                String context = priceNodes.item(k).getTextContent();
                if (context != null) {
                    complexPrice.setPrice(context);
                }
            }

            food.setComplexPrice(complexPrice);
        } else {
            food.setPrice((getSingleChildStringValue(foodElement, PRICE)));
        }
    }

    private static void fillCount(Element foodElement, Food food) {
        if (isComplexElement(foodElement, PORTION)) {
            food.setPortion((getSingleChildStringValue(foodElement, PORTION)));
        } else {
            food.setCount(getSingleChildStringValue(foodElement, COUNT));
        }
    }

    private static boolean isComplexElement(Element foodElement, String tagName) {
        return foodElement.getElementsByTagName(tagName).getLength() != 0;
    }

}

package by.training.task2;

import by.training.task2.entity.Category;
import by.training.task2.entity.ComplexDescription;
import by.training.task2.entity.ComplexPrice;
import by.training.task2.entity.Food;
import org.w3c.dom.*;
import org.apache.xerces.parsers.DOMParser;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOMMenuParser {
    public static void main(String[] args) throws IOException, SAXException {

        DOMParser parser = new DOMParser();
        parser.parse("src/by/training/task2/menu.xml");
        Document document = parser.getDocument();

        Element root = document.getDocumentElement();

        List<Category> menu = new ArrayList<Category>();

        NodeList categoryNodes = root.getElementsByTagName("category");
        Category category = null;
        List<Food> foodList = null;

        for(int i=0; i<categoryNodes.getLength();i++){
            category = new Category();
            foodList = new ArrayList<Food>();
            Food food = null;

            Element categoryElement = (Element) categoryNodes.item(i);
            NodeList foodNodes = categoryElement.getElementsByTagName("food"); //get complex type

            category.setId(categoryElement.getAttribute("id"));
            category.setName(categoryElement.getAttribute("name"));

            for(int j=0; j<foodNodes.getLength();j++){
                food = new Food();

                Element foodElement = (Element) foodNodes.item(j);

                food.setId((foodElement.getAttribute("id")));
                food.setName((getSingleChild(foodElement,"name").getTextContent().trim()));
                food.setPhoto((getSingleChild(foodElement,"photo").getTextContent().trim()));

                getDescription(foodElement,food);
                getPrice(foodElement,food);
                getCount(foodElement,food);

                foodList.add(food);
            }

            category.setFoods(foodList);
            menu.add(category);
        }
        System.out.println("debug");
    }

    private static void getDescription(Element foodElement, Food food){
        if (foodElement.getElementsByTagName("complexDescription").getLength() != 0){
            ComplexDescription complexDescription = new ComplexDescription();

            complexDescription.setHeader((getSingleChild(foodElement,"header").getTextContent().trim()));
            NodeList descriptionNodes = foodElement.getElementsByTagName("addition");

            for(int k=0; k<descriptionNodes.getLength(); k++){
                String context =(descriptionNodes.item(k).getTextContent());
                if(context!=null) {
                    complexDescription.setDescription(context);
                }
                else continue;
            }
            food.setComplexDescription(complexDescription);
        }
        else{
            food.setDescription((getSingleChild(foodElement,"description").getTextContent().trim()));
        }
    }

    private static Element getSingleChild(Element element, String childName){
        NodeList nlist = element.getElementsByTagName(childName);
        Element child = (Element) nlist.item(0);
        return  child;
    }

    private static void getPrice(Element foodElement, Food food){
        if (foodElement.getElementsByTagName("complexPrice").getLength() != 0){
            ComplexPrice complexPrice = new ComplexPrice();
            NodeList priceNodes = foodElement.getElementsByTagName("price");
            for(int k=0; k<priceNodes.getLength(); k++){
                String context =(priceNodes.item(k).getTextContent());
                if(context!=null) {
                    complexPrice.setPrice(context);
                }
                else continue;
            }
            food.setComplexPrice(complexPrice);
        }
        else{
            food.setPrice((getSingleChild(foodElement,"price").getTextContent().trim()));
        }
    }

    private static void getCount(Element foodElement, Food food){
        if (foodElement.getElementsByTagName("portion").getLength() != 0){
            food.setPortion((getSingleChild(foodElement,"portion").getTextContent().trim()));
        }
        else{
            food.setCount(getSingleChild(foodElement,"count").getTextContent().trim());
        }
    }
}

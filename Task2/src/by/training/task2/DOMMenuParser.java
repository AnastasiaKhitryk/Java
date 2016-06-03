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
    
    public static final String PATH = "src/by/training/task2/menu.xml";
    public static final String CATEGORY = "category";
    public static final String FOOD = "food";
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String PHOTO = "photo";
    public static final String COMPLEX_DESCRIPTION = "complexDescription";
    public static final String HEADER = "header";
    public static final String ADDITION = "addition"
    public static final String PORTION = "portion";
    public static final String DESCRIPTION = "description";
    public static final String PRICE = "price";
    public static final String COUNT = "count";
    public static final String PRICE = "price";
    public static final String COMPLEX_PRICE = "complexPrice"          //include?
    
    public static void main(String[] args) throws IOException, SAXException {

        DOMParser parser = new DOMParser();
        parser.parse(PATH);
        Document document = parser.getDocument();

        Element root = document.getDocumentElement();

        List<Category> menu = new ArrayList<Category>();

        NodeList categoryNodes = root.getElementsByTagName(CATEGORY);
        Category category = null;                                                       //?????
        List<Food> foodList = null;

        for(int i=0; i<categoryNodes.getLength(); i++){
            category = new Category();
            foodList = new ArrayList<Food>();
            Food food = null;

            Element categoryElement = (Element) categoryNodes.item(i);
            NodeList foodNodes = categoryElement.getElementsByTagName(FOOD); //get complex type

            category.setId(categoryElement.getAttribute(ID));
            category.setName(categoryElement.getAttribute(NAME));

            for(int j=0; j<foodNodes.getLength();j++){
                food = new Food();

                Element foodElement = (Element) foodNodes.item(j);

                food.setId((foodElement.getAttribute(ID)));
                food.setName((getSingleChild(foodElement, NAME).getTextContent().trim()));
                food.setPhoto((getSingleChild(foodElement, PHOTO).getTextContent().trim()));

                getDescription(foodElement,food);
                getPrice(foodElement,food);
                getCount(foodElement,food);

                foodList.add(food);
            }

            category.setFoods(foodList);
            menu.add(category);
        }
    }

    private static void getDescription(Element foodElement, Food food){
        if (foodElement.getElementsByTagName(COMPLEX_DESCRIPTION).getLength() != 0){
            ComplexDescription complexDescription = new ComplexDescription();

            complexDescription.setHeader((getSingleChild(foodElement,HEADER).getTextContent().trim()));
            NodeList descriptionNodes = foodElement.getElementsByTagName(ADDITION);

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
            food.setDescription((getSingleChild(foodElement, DESCRIPTION).getTextContent().trim()));
        }
    }

    private static Element getSingleChild(Element element, String childName){
        NodeList nlist = element.getElementsByTagName(childName);
        Element child = (Element) nlist.item(0);
        return  child;
    }

    private static void getPrice(Element foodElement, Food food){
        if (foodElement.getElementsByTagName(COMPLEX_PRICE).getLength() != 0){
            ComplexPrice complexPrice = new ComplexPrice();
            NodeList priceNodes = foodElement.getElementsByTagName(PRICE);
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
            food.setPrice((getSingleChild(foodElement,PRICE).getTextContent().trim()));
        }
    }

    private static void getCount(Element foodElement, Food food){
        if (foodElement.getElementsByTagName(PORTION).getLength() != 0){
            food.setPortion((getSingleChild(foodElement,PORTION).getTextContent().trim()));
        }
        else{
            food.setCount(getSingleChild(foodElement,COUNT).getTextContent().trim());
        }
    }
}

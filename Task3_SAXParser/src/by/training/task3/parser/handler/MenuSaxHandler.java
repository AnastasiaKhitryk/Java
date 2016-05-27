package by.training.task3.parser.handler;

import by.training.task3.parser.entity.*;
import by.training.task3.parser.entity.MenuTagName;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class MenuSaxHandler extends DefaultHandler{
    private Category category;
    private Food food;
    private String header;
    private List<String> additions;
    private List<Category> categoryList = new ArrayList<Category>();
    private List<Food> foodList = new ArrayList<Food>();
    private ComplexDescription complexDescription;
    private StringBuilder text;

    public List<Category> getCategoryList(){
        return categoryList;
    }

    public void startDocument()throws SAXException{
        System.out.println("Parsing started ...");
    }

    public void endDocument()throws SAXException{
        System.out.println("Parsing ended.");
    }

    public void startElement(String uri, String localName, String gName,
                             Attributes attributes) throws SAXException {
        System.out.println("startElement  -> " +" uri: "+uri+" , localName: "+localName
        +", gName: "+gName);
        text = new StringBuilder();
        if(gName.equals("food")){
            food = new Food();
            food.setId(attributes.getValue("id"));
        }
        if(gName.equals("category")){
            category = new Category();
            foodList = new ArrayList<Food>();
            category.setId(attributes.getValue("id"));
            category.setName(attributes.getValue("name"));
        }
        if(gName.equals("cDescription")){
            complexDescription = new ComplexDescription();
            additions = new ArrayList<String>();
            header = "";
        }
    }

    public void characters(char[] buffer,int start,int length){
        text.append(buffer,start,length);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException{
        MenuTagName tagName = MenuTagName.valueOf(qName.toUpperCase());

        switch (tagName){
            case CATEGORY:
                category.setFoods(foodList);
                categoryList.add(category);
                foodList = null;
                category = null;
                break;
            case FOOD:
                foodList.add(food);
                food = null;
                break;
            case NAME:
                food.setName(text.toString());
                break;
            case PHOTO:
                food.setPhoto(text.toString());
                break;
            case DESCRIPTION:
                food.setDescription(text.toString());
                break;
            case COMPLEXDESCRIPTION:
                complexDescription.setHeader(header);
                complexDescription.setComplexDescription(additions);
                food.setComplexDescription(complexDescription);
                complexDescription = null;
                break;
            case ADDITION:
                additions.add(text.toString());
                break;
            case HEADER:
                header = text.toString();
                break;
            case PORTION:
                food.setPortion(text.toString());
                break;
            case COUNT:
                food.setCount(text.toString());
                break;
            case PRICE:
                food.setPrice(text.toString());
                break;
        }
    }
}

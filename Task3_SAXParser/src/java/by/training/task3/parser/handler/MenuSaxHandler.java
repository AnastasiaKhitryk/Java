package java.by.training.task3.parser.handler;

import java.by.training.task3.parser.entity.*;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.by.training.task3.parser.entity.MenuTagName;
import java.util.ArrayList;
import java.util.List;

public class MenuSaxHandler extends DefaultHandler{

    private static final String CATEGORY = "category";
    private static final String FOOD = "food";
    private static final String COMPLEX_DESCRIPTION = "complexDescription";
    private static final String ID = "id";
    private static final String NAME = "name";

    private Category category;
    private Food food;
    private String header;
    private List<String> additions;
    private List<Category> categoryList = new ArrayList<>();
    private List<Food> foodList = new ArrayList<>();
    private ComplexDescription complexDescription;
    private StringBuilder text;

    public List<Category> getCategoryList(){
        return categoryList;
    }

    public void startElement(String uri, String localName, String gName,
                             Attributes attributes) throws SAXException {
        text = new StringBuilder();
        if(gName.equals(FOOD)){
            food = new Food();
            food.setId(attributes.getValue(ID));
        }
        if(gName.equals(CATEGORY)){
            category = new Category();
            foodList = new ArrayList<>();
            category.setId(attributes.getValue(ID));
            category.setName(attributes.getValue(NAME));
        }
        if(gName.equals(COMPLEX_DESCRIPTION)){
            complexDescription = new ComplexDescription();
            additions = new ArrayList<>();
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
            case COMPLEX_DESCRIPTION:
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

package by.training.parser.domain;

import java.util.ArrayList;
import java.util.List;

public class ComplexPrice {
    private List<String> complexPrice = new ArrayList<String>();

    public List<String> getComplexPrice() {
        return complexPrice;
    }

    public void setComplexPrice(ArrayList<String> complexPrice) {
        this.complexPrice = complexPrice;
    }

    public void setPrice(String price){
        this.complexPrice.add(price);
    }
}

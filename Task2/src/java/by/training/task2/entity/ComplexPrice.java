package java.by.training.task2.entity;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (null == o || getClass() != o.getClass()) return false;

        ComplexPrice that = (ComplexPrice) o;

        return complexPrice != null ? complexPrice.equals(that.complexPrice) : that.complexPrice == null;

    }

    @Override
    public int hashCode() {
        return complexPrice != null ? complexPrice.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ComplexPrice{" +
                "complexPrice=" + complexPrice +
                '}';
    }
}

package by.training.news.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlType(propOrder = {"categoryList"})
public class Catalog {
    private List<Category> categoryList = new ArrayList<>();

    @XmlElement(name="category")
    @XmlElementWrapper(name="categories")
    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public void addCategory(Category ... category) {
        for(Category c:category){
            categoryList.add(c);
        }
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Catalog catalog = (Catalog) o;

        return categoryList != null ? categoryList.equals(catalog.categoryList) : catalog.categoryList == null;

    }

    @Override
    public int hashCode() {
        return categoryList != null ? categoryList.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "categoryList=" + categoryList +
                '}';
    }
}

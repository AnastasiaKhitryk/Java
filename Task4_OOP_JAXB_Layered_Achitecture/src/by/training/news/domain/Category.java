package by.training.news.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlType(propOrder = {"subCategoryList"},name = "categories")
public class Category {
    private String name;
    private List<SubCategory> subCategoryList = new ArrayList<>();

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @XmlElement(name="subcategory")
    @XmlElementWrapper(name="subcategories")
    public void setSubCategoryList(List<SubCategory> subCategoryList) {
        this.subCategoryList = subCategoryList;
    }

    public void addSubCategory(SubCategory ... subCategories) {
        for(SubCategory s:subCategories){
            this.subCategoryList.add(s);
        }
    }

    public List<SubCategory> getSubCategoryList() {
        return subCategoryList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (name != null ? !name.equals(category.name) : category.name != null) return false;
        return subCategoryList != null ? subCategoryList.equals(category.subCategoryList) : category.subCategoryList == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (subCategoryList != null ? subCategoryList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", subCategoryList=" + subCategoryList +
                '}';
    }
}

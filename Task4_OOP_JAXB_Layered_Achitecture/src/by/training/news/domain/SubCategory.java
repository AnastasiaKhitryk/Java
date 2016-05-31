package by.training.news.domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlType(propOrder = {"newsList"})
public class SubCategory {
    private String name;
    private List<News> newsList = new ArrayList<News>();

    @XmlAttribute(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "news")
    @XmlElementWrapper
    public void setNewsList(List<News> newsList) {
        this.newsList = newsList;
    }

    public void addNews(News ... news) {
        for(News n:news){
            this.newsList.add(n);
        }
    }

    public List<News> getNewsList() {
        return newsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubCategory that = (SubCategory) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return newsList != null ? newsList.equals(that.newsList) : that.newsList == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (newsList != null ? newsList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SubCategory{" +
                "name='" + name + '\'' +
                ", newsList=" + newsList +
                '}';
    }
}

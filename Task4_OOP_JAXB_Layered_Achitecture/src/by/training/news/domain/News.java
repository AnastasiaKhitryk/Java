package by.training.news.domain;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"name","provider","date","body"})
public class News {
    private String name;
    private String provider;
    private String date;
    private String body;

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @XmlElement
    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProvider() {
        return provider;
    }

    @XmlElement
    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @XmlElement
    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        News news = (News) o;

        if (name != null ? !name.equals(news.name) : news.name != null) return false;
        if (provider != null ? !provider.equals(news.provider) : news.provider != null) return false;
        if (date != null ? !date.equals(news.date) : news.date != null) return false;
        return body != null ? body.equals(news.body) : news.body == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (provider != null ? provider.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "News{" +
                "name='" + name + '\'' +
                ", provider='" + provider + '\'' +
                ", date='" + date + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}

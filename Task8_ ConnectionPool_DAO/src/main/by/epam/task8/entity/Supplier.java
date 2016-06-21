package by.epam.task8.entity;

import java.io.Serializable;
import java.sql.Blob;

public class Supplier extends Model implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
    private Blob image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Supplier supplier = (Supplier) o;

        if (name != null ? !name.equals(supplier.name) : supplier.name != null) return false;
        if (description != null ? !description.equals(supplier.description) : supplier.description != null)
            return false;
        return image != null ? image.equals(supplier.image) : supplier.image == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image=" + image +
                '}';
    }
}


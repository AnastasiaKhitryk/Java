package by.training.task2.entity;

public class Food {

    private String id;
    private String photo;
    private String name;
    private String description;
    private String portion;
    private String price;
    private ComplexPrice complexPrice;
    private ComplexDescription complexDescription;
    private String count;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

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

    public String getPortion() {
        return portion;
    }

    public void setPortion(String portion) {
        this.portion = portion;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public ComplexPrice getComplexPrice() {
        return complexPrice;
    }

    public void setComplexPrice(ComplexPrice complexPrice) {
        this.complexPrice = complexPrice;
    }

    public ComplexDescription getComplexDescription() {
        return complexDescription;
    }

    public void setComplexDescription(ComplexDescription complexDescription) {
        this.complexDescription = complexDescription;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }


}

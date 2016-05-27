package by.training.task3.parser.entity;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        if (id != null ? !id.equals(food.id) : food.id != null) return false;
        if (photo != null ? !photo.equals(food.photo) : food.photo != null) return false;
        if (name != null ? !name.equals(food.name) : food.name != null) return false;
        if (description != null ? !description.equals(food.description) : food.description != null) return false;
        if (portion != null ? !portion.equals(food.portion) : food.portion != null) return false;
        if (price != null ? !price.equals(food.price) : food.price != null) return false;
        if (complexPrice != null ? !complexPrice.equals(food.complexPrice) : food.complexPrice != null) return false;
        if (complexDescription != null ? !complexDescription.equals(food.complexDescription) : food.complexDescription != null)
            return false;
        return count != null ? count.equals(food.count) : food.count == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (portion != null ? portion.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (complexPrice != null ? complexPrice.hashCode() : 0);
        result = 31 * result + (complexDescription != null ? complexDescription.hashCode() : 0);
        result = 31 * result + (count != null ? count.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id='" + id + '\'' +
                ", photo='" + photo + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", portion='" + portion + '\'' +
                ", price='" + price + '\'' +
                ", complexPrice=" + complexPrice +
                ", complexDescription=" + complexDescription +
                ", count='" + count + '\'' +
                '}';
    }
}

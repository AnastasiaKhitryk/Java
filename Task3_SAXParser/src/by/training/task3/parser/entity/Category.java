package by.training.task3.parser.entity;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String id;
    private String name;
    private List<Food> foods;


    public Category(){

    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }

    public void setFood(Food food) {
        foods.add(food);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package by.training.task2_1.domain;

import java.util.List;

public class AttributeList {
    private List<Attribute> list;

    public List<Attribute> getList() {
        return list;
    }

    public void setList(List<Attribute> list) {
        this.list = list;
    }
    public void setAttribute(Attribute attribute) {
        this.list.add(attribute);
    }
}

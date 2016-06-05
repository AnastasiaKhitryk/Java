package java.by.training.task2.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComplexDescription {

    private String header;
    private List<String> complexDescription = new ArrayList<String>();

    public ComplexDescription(){}

    public static ComplexDescription valueOf(ComplexDescription complexDescription) {
        ComplexDescription copy = new ComplexDescription();
        copy.setComplexDescription(complexDescription.getComplexDescription());
        copy.setHeader(complexDescription.getHeader());

        return copy;
    }

    public List<String> getComplexDescription() {
        return Collections.unmodifiableList(complexDescription);
    }

    public void setComplexDescription(List<String> complexDescription) {
        this.complexDescription = complexDescription;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setDescription(String description){
        this.complexDescription.add(description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (null == o || getClass() != o.getClass()) return false;

        ComplexDescription that = (ComplexDescription) o;

        if (header != null ? !header.equals(that.header) : that.header != null) return false;
        return complexDescription != null ? complexDescription.equals(that.complexDescription) : that.complexDescription == null;

    }

    @Override
    public int hashCode() {
        int result = header != null ? header.hashCode() : 0;
        result = 31 * result + (complexDescription != null ? complexDescription.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ComplexDescription{" +
                "header='" + header + '\'' +
                ", complexDescription=" + complexDescription +
                '}';
    }
}

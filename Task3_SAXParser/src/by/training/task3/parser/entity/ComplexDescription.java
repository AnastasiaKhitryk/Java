package by.training.task3.parser.entity;

import com.sun.org.glassfish.gmbal.Description;

import javax.management.Descriptor;
import java.util.ArrayList;
import java.util.List;

public class ComplexDescription {

    private String header;
    private List<String> complexDescription = new ArrayList<String>();
    private Description addition;
    public List<String> getComplexDescription() {
        return complexDescription;
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


}

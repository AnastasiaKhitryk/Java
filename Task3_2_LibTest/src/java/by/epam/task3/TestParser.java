package java.by.epam.task3;

import by.training.task32.parser.DomParser;

public class TestParser {


    public static  void main(String[] args) {
        DomParser parser = new DomParser();
        parser.parse("resources/menu.xml");
    }
}

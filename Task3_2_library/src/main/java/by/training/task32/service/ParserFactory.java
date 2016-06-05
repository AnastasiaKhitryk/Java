package by.training.task32.service;

import by.training.task32.entity.ParserType;
import by.training.task32.service.DomParser;
import by.training.task32.service.IParser;

public final class ParserFactory {

    private ParserFactory() {}

    public static IParser getParser(ParserType type) {
        IParser parser = null;
        switch (type) {
            case DOM:
                parser = new DomParser();
                break;
            case SAX:
                //TODO
                break;
            case STAX:
                //TODO
                break;
            default:
                break;
        }

        return parser;
    }

}

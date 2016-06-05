package java.by.training.parser.domain;

public enum MenuTagName {
    CATEGORY,
    FOOD,
    PHOTO,
    NAME,
    DESCRIPTION,
    PORTION,
    PRICE,
    ADDITION,
    COMPLEX_DESCRIPTION,
    COMPLEX_PRICE,
    COUNT,
    MENU,
    HEADER;

    public static MenuTagName getElementTagName(String element) {
        switch (element){
            case "category":
                return CATEGORY;
            case "food":
                return FOOD;
            case "photo":
                return PHOTO;
            case "name":
                return NAME;
            case "description":
                return DESCRIPTION;
            case "portion":
                return PORTION;
            case "price":
                return PRICE;
            case "addition":
                return ADDITION;
            case "complexDescription":
                return  COMPLEX_DESCRIPTION;
            case "complexPrice":
                return COMPLEX_PRICE;
            case "count":
                return COUNT;
            case "header":
                return HEADER;
            case "menu":
                return MENU;
            default: throw new EnumConstantNotPresentException(MenuTagName.class,element);

        }
    }
}

package by.training.task2_1.service;

import by.training.task2_1.domain.Attribute;
import by.training.task2_1.domain.AttributeList;
import by.training.task2_1.domain.Element;
import by.training.task2_1.domain.TagType;

import java.io.FileInputStream;
import java.io.IOException;

public class DOMParser {


    public static void main(String[] args) throws IOException {

        FileInputStream inFile = new FileInputStream("menu.xml");
        byte[] str = new byte[inFile.available()];
        inFile.read(str);
        String text = new String(str);

        Element root = new Element();
        parse(text,root);

    }


    private static void parse(String text, Element parent){
        char o = text.charAt(0);
        //text.replaceAll("/\r", " ");
        //text.replaceAll("/\n", " ");
        //text = text.trim();
        if (isCloseTag(text)) {
            correctTextClose(text);
            parent = parent.getParent();
            parse(text,parent);
        }else{
            TagType tagType = initializeTagType(text);

            switch (tagType) {
                case COMPLEX:
                    Element newElement = new Element();
                    newElement.setParent(parent);
                    String tagText = seachTagText(text);
                    text = text.substring(tagText.length()+1,text.length());
                    newElement.setName(seachElementName(tagText));
                  //  newElement.setAttributeList(seachAttribyteList(tagText));
                    parent.setChildElement(newElement);
                    parse(text, newElement);
                    break;
                case SINGLE:
                    parent.setValue(searchSimpleText(text));
                    text = text.substring(parent.getValue().length()+1,text.length());
                    break;
                default:
                    break;
            }
        }
    }

    private static String searchSimpleText(String text){
        String returnString = "";
        int i=0;

        do{
            returnString=returnString+text.charAt(i);
            i++;
        }while (text.charAt(i)!='<');
        return  returnString;
    }

    private static AttributeList seachAttribyteList(String text){

        AttributeList returnList = new AttributeList();
        Attribute attribute = new Attribute();
        String tagName = "";
        String tagValue = "";
        Boolean attrValue = false;

        for(int i=0; i<text.length(); i++){

            if(text.charAt(i)==' '){
                continue;
            }
            else {

                if(attrValue==true){
                    if(text.charAt(i)!= '"') {
                        tagValue = tagValue + text.charAt(i);
                    }
                        else {
                            attrValue = false;
                            attribute.setValue(tagValue);
                            tagValue=null;
                        }
                    }

                }

                if(text.charAt(i)!='=' && attrValue == false)
                    tagName = tagName+text.charAt(i);
                else {
                    if(text.charAt(i)=='='){
                        attribute.setName(tagName.trim());
                        tagName=null;
                    }
                    continue;
                }
                if(text.charAt(i) == '"'){
                    attrValue = true;
                }
            }
        returnList.setAttribute(attribute);

        return returnList;
    }

    private static String seachElementName(String text){
        String returnString = "";
        for(int i=0;i<text.length();i++){
            if(text.charAt(i)==' '){
                return returnString;
            }
            else returnString=returnString+text.charAt(i);
        }
        return returnString;
    }

    private static String seachTagText(String text){
        String tagText = "";
        boolean openTagName = false;
        for(int i=0;i<text.length();i++){
            char ch=text.charAt(i);
            if(ch=='<'){
                if(openTagName==false){
                    openTagName = true;
                }
            }else if(openTagName == true && ch !='>'){
                tagText = tagText + ch;
            } else {
                if(ch=='>'){
                    return tagText;
                }
            }
        }
        return null;
    }

    private static String correctTextClose(String text){
        String returnText="";
        int i=0;
        if(text.charAt(0)=='<' && text.charAt(1)=='/'){
            do{
                i++;

            }while (text.charAt(i)!='>');
        }

        for(int j=i+1;j<text.length();j++){
            returnText=returnText+text.charAt(j);
        }
        return returnText;
    }

    private static TagType initializeTagType(String text){
        if(text.charAt(0)=='<' && text.charAt(1)!='/'){
              return TagType.COMPLEX;
        }
        else  if (text.charAt(0)!='<' && text.charAt(0)!='>'){
            return TagType.SINGLE;
        }
        else return null;
    }

    private static boolean isCloseTag(String text){
        if(text.charAt(0)=='<' && text.charAt(1) =='/'){
            return true;
        }
        else  return false;
    }

}

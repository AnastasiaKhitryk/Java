package by.training.news.view;

import by.training.news.domain.Request;
import by.training.news.domain.Response;
import org.json.simple.JSONObject;
import java.util.Scanner;

public class View {
    public Request doUserAction(){

        Scanner reader= new Scanner(System.in);
        Request request = new Request();
        String params;
        JSONObject object;

        System.out.println("Comands: \n  1. Save new news;\n  2. Find news\n");

        object = new JSONObject();
        object.put("category","Books");
        object.put("subcategory","It");
        object.put("name","Linux Programming Interface ");
        object.put("provider","Piter");
        object.put("date","03.06.2016");
        object.put("body","Cложная книга о системном программировании объемом 1500+ страниц ... ");

        params=object.toJSONString();

        request.setCommandName("SAVE_NEW_NEWS");
        request.setParams(params);


//        object = new JSONObject();
//        object.put("name","Конфликтология: Учебное пособие.");
//
//        params = object.toJSONString();
//
//        request.setCommandName("FIND_NEWS");
//        request.setParams(params);


        return request;
    }

    public void printAnswer(Response response){
        System.out.println(response.getCommandName());
        System.out.println(response.getMessage());
    }


}

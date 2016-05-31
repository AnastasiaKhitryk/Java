package by.training.news.start;

import by.training.news.controller.Controller;
import by.training.news.domain.Request;
import by.training.news.domain.Response;
import by.training.news.view.View;

public class Main {
    public static void main(String[] args){
        View view = new View();
        Controller controller = new Controller();

        Request request = view.doUserAction();
        Response response = controller.doAction(request);

        view.printAnswer(response);
    }
}

package by.training.news.command.impl;

import by.training.news.command.ICommand;
import by.training.news.domain.Request;
import by.training.news.domain.Response;
import by.training.news.service.IService;
import by.training.news.service.ServiceFactory;
import by.training.news.service.exception.ServiceException;

public class SaveNewNews implements ICommand{
    public Response execute(Request request){

        ServiceFactory factory = ServiceFactory.getInstance();
        IService service = factory.getService();

        Response response = new Response();

        try {
            service.saveNewNews(request.getParams());
            response.setStatus(true);
            response.setMessage("Your news has been added :) ");

        }catch (ServiceException ex){
            //logging
            response.setStatus(false);
            response.setMessage("Error adding news " + ex.getMessage());
        }

        return response;


    }
}

package by.training.news.command.impl;

import by.training.news.command.ICommand;
import by.training.news.domain.News;
import by.training.news.domain.Request;
import by.training.news.domain.Response;
import by.training.news.service.IService;
import by.training.news.service.ServiceFactory;
import by.training.news.service.exception.ServiceException;

import java.util.List;

public class FindNews implements ICommand{
    public Response execute(Request request){

        ServiceFactory factory = ServiceFactory.getInstance();
        IService service = factory.getService();

        Response response = new Response();

        try {
            String news = service.findNews(request.getParams());
            response.setStatus(true);
            response.setMessage("Result: " + news);

        }catch (ServiceException ex){
            //logging
            response.setStatus(false);
            response.setMessage("Error: " + ex.getMessage());
        }

        return response;
    }
}

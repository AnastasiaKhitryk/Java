package by.training.news.controller;

import by.training.news.command.ICommand;
import by.training.news.domain.Request;
import by.training.news.domain.Response;

public class Controller {

    CommandHelper helper = new CommandHelper();

    public Response doAction(Request request){
        String commandName = request.getCommandName();

        ICommand command = helper.getCommand(commandName);
        Response response = command.execute(request);
        response.setCommandName(commandName);

        return response;
    }
}

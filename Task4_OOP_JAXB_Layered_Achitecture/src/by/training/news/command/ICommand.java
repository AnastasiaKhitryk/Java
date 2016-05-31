package by.training.news.command;

import by.training.news.domain.Request;
import by.training.news.domain.Response;

public interface ICommand {
    Response execute(Request request);
}

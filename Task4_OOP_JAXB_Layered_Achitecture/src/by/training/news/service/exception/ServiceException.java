package by.training.news.service.exception;

public class ServiceException extends Exception{
    public ServiceException(){}

    public ServiceException(String message){
        super(message);
    }

    public ServiceException(String message, Exception ex){
        super(message,ex);
    }
}

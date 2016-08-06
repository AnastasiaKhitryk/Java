package by.epam.task15_2.service.exception;

public class ServiceException extends Exception {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Exception ex) {
        super(message, ex);
    }
}
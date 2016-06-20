package by.epam.task8.dao.exception;

public class ConnectionPoolException extends Exception{
    public ConnectionPoolException(){}

    public ConnectionPoolException(String message){
        super(message);
    }

    public ConnectionPoolException(String message, Exception e){
        super(message, e);
    }
}

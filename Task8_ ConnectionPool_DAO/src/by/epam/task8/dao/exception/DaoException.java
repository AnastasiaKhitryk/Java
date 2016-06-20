package by.epam.task8.dao.exception;

public class DaoException extends Exception {

    public DaoException(){}

    public DaoException(String message){
        super(message);
    }

    public DaoException(String message, Exception e){
        super(message, e);
    }
}

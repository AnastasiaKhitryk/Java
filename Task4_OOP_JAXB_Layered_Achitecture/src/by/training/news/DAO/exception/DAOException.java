package by.training.news.DAO.exception;

public class DAOException extends Exception{
    public DAOException(){}

    public DAOException(String message){
        super(message);
    }

    public DAOException(String message, Exception ex){
        super(message,ex);
    }
}

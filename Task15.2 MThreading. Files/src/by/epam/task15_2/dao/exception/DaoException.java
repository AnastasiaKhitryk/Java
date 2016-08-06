package by.epam.task15_2.dao.exception;

public class DaoException extends Exception {
    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Exception ex) {
        super(message, ex);
    }
}
package main.java.com.esport.service.exception;

public class ServiceWrongEmailException extends ServiceException{
	
	private static final long serialVersionUID = 1L;

	public ServiceWrongEmailException() {
        super();
    }
	
	public ServiceWrongEmailException(String message) {
        super(message);
    }

    public ServiceWrongEmailException(String message, Exception ex) {
        super(message, ex);
    }
}

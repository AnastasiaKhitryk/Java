package main.java.com.esport.service.exception;

public class ServiceWrongPasswordException extends ServiceException{
	
	private static final long serialVersionUID = 1L;

	public ServiceWrongPasswordException() {
        super();
    }
	
	public ServiceWrongPasswordException(String message) {
        super(message);
    }

    public ServiceWrongPasswordException(String message, Exception ex) {
        super(message, ex);
    }
}

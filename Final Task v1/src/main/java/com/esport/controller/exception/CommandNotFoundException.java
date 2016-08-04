package main.java.com.esport.controller.exception;

public class CommandNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public CommandNotFoundException(){
	}
	
	public CommandNotFoundException(String message) {
        super(message);
    }

    public CommandNotFoundException(String message, Exception ex) {
        super(message, ex);
    }
}

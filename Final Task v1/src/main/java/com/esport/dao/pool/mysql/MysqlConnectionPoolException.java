package main.java.com.esport.dao.pool.mysql;

public class MysqlConnectionPoolException extends Exception{

	private static final long serialVersionUID = 1L;

	public MysqlConnectionPoolException(){
		super();
	}
	
	public MysqlConnectionPoolException(String message){
		super(message);
	}
	
	public MysqlConnectionPoolException(String message, Exception ex){
		super(message, ex);
	}
}

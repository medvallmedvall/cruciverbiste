package dao;

@SuppressWarnings("serial")
public class InvalidUpdateException extends Exception{
	
	public InvalidUpdateException() {
		super();
	}
	
	public InvalidUpdateException(String message) {
		super(message);
	}
}

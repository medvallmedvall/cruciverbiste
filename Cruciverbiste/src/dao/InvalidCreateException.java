package dao;

@SuppressWarnings("serial")
public class InvalidCreateException  extends Exception{
	
	public InvalidCreateException() {
		super();
	}

	public InvalidCreateException(String message) {
		super(message);
	}
}

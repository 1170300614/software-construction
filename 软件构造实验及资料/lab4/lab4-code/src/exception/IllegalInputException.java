package exception;

public class IllegalInputException extends RuntimeException{
	private String message;
	
	public IllegalInputException() { super(); }
	
	public IllegalInputException(String message) {
		super(message);
		this.message = message;
	}
	
	@Override public String getMessage() { return message; }
}
package exception;

public class NumberFormatException extends RuntimeException {
	private String message;
	
	public NumberFormatException() { super(); }
	
	public NumberFormatException(String message) {
		super(message);
		this.message = message;
	}
	
	@Override public String getMessage() { return message; }
}
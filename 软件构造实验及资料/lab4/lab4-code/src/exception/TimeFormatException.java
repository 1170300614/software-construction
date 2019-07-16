package exception;

public class TimeFormatException extends RuntimeException {
	private String message;
	
	public TimeFormatException() { super(); }
	
	public TimeFormatException(String message) {
		super(message);
		this.message = message;
	}
	
	@Override public String getMessage() { return message; }
}
package exception;

public class TimeException extends RuntimeException {
	private String message;
	
	public TimeException() { super(); }
	
	public TimeException(String message) {
		super(message);
		this.message = message;
	}
	
	@Override public String getMessage() { return message; }
}

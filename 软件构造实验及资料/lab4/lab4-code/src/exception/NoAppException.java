package exception;

public class NoAppException extends RuntimeException {
	private String message;
	
	public NoAppException() { super(); }
	
	public NoAppException(String message) {
		super(message);
		this.message = message;
	}
	
	@Override public String getMessage() { return message; }
}

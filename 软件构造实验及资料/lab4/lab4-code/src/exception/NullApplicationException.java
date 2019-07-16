package exception;

public class NullApplicationException extends RuntimeException {
	private String message;
	
	public NullApplicationException() { super(); }
	
	public NullApplicationException(String message) {
		super(message);
		this.message = message;
	}
	
	@Override public String getMessage() { return message; }
}
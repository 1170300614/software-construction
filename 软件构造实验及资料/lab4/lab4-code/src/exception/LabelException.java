package exception;

public class LabelException extends RuntimeException {
	private String message;
	
	public LabelException() { super(); }
	
	public LabelException(String message) {
		super(message);
		this.message = message;
	}
	
	@Override public String getMessage() { return message; }
}
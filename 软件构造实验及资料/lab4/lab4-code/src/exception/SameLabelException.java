package exception;

public class SameLabelException extends RuntimeException{
	private String message;
	
	public SameLabelException() { super(); }
	
	public SameLabelException(String message) {
		super(message);
		this.message = message;
	}
	
	@Override public String getMessage() { return message; }
}
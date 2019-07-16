package exception;

public class SentenceException extends RuntimeException {
	private String message;
	
	public SentenceException() { super(); }
	
	public SentenceException(String message) {
		super(message);
		this.message = message;
	}
	
	@Override public String getMessage() { return message; }
}
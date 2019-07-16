package exception;

public class UsageException extends RuntimeException{
	private String message;
	
	public UsageException() { super(); }
	
	public UsageException(String message) {
		super(message);
		this.message = message;
	}
	
	@Override public String getMessage() { return message; }
}

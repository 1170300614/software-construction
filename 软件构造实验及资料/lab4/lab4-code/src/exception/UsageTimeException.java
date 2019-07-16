package exception;

public class UsageTimeException extends RuntimeException{
	private String message;
	
	public UsageTimeException() { super(); }
	
	public UsageTimeException(String message) {
		super(message);
		this.message = message;
	}
	
	@Override public String getMessage() { return message; }
}
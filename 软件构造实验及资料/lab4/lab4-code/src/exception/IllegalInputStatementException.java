package exception;

public class IllegalInputStatementException extends RuntimeException{
	private String message;
	
	public IllegalInputStatementException() { super(); }
	
	public IllegalInputStatementException(String message) {
		super(message);
		this.message = message;
	}
	
	@Override public String getMessage() { return message; }
}

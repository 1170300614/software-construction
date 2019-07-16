package exception;

public class OrbitException extends RuntimeException {
	private String message;
	
	public OrbitException() { super(); }
	
	public OrbitException(String message) {
		super(message);
		this.message = message;
	}
	
	@Override public String getMessage() { return message; }
}

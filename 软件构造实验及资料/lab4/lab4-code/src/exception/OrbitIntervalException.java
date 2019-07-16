package exception;
public class OrbitIntervalException extends RuntimeException {
	private String message;
	
	public OrbitIntervalException() { super(); }
	
	public OrbitIntervalException(String message) {
		super(message);
		this.message = message;
	}
	
	@Override public String getMessage() { return message; }
}

package exception;

public class TrackNumberException extends RuntimeException{
	private String message;
	
	public TrackNumberException() { super(); }
	
	public TrackNumberException(String message) {
		super(message);
		this.message = message;
	}
	
	@Override public String getMessage() { return message; }
}

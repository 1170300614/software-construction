package exception;

public class EqualLabelException extends RuntimeException{
	private String message;
	
     public EqualLabelException() {
		// TODO Auto-generated constructor stub
	 super();
     }
	
	public EqualLabelException(String message) {
		super(message);
		this.message = message;
	}
	
	@Override public String getMessage() { return message; }
}

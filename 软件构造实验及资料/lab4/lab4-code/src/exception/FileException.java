package exception;

public class FileException extends Exception{
	private String message;
	public FileException() {
        super();
    }
    public FileException(String message) {
        super(message);
        this.message =message;
    }
    @Override
    public String getMessage() {
    	return message;
    }
    
}

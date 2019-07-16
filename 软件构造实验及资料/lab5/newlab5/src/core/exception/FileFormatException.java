package core.exception;

public class FileFormatException extends RuntimeException {
    /**
     * a.
     */
    private String message;
    /**
     * a.
     */
    public FileFormatException() {
        super();
    }
    /**
     * aa.
     *
     * @param messages s
     */
    public FileFormatException(final String messages) {
        super(messages);
        this.message = messages;
    }
    /**
     * a.
     *
     * @return c
     */
    @Override public String getMessage() {
        return message;
    }
}

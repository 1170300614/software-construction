package core.exception;

public class FileException extends Exception {
    /** a. */
    private String message;
    /** a. */
    public FileException() {
        super();
    }
    /**
     * a.
     *
     * @param messages c
     */
    public FileException(final String messages) {
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

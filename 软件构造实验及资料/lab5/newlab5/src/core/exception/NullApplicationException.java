package core.exception;

public class NullApplicationException extends RuntimeException {
    /**
     * a.
     */
    private String message;
    /**
     * a.
     */
    public NullApplicationException() {
        super();
    }
    /**
     * a.
     *
     * @param messages s
     */
    public NullApplicationException(final String messages) {
        super(messages);
        this.message = messages;
    }
    /**
     * a.
     *
     * @return a
     */
    @Override public String getMessage() {
        return message;
    }
}

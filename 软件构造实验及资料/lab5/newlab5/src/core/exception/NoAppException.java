package core.exception;

public class NoAppException extends RuntimeException {
    /**
     * a.
     */
    private String message;
    /**
     * a.
     */
    public NoAppException() {
        super();
    }
    /**
     * a.
     *
     * @param messages a
     */
    public NoAppException(final String messages) {
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

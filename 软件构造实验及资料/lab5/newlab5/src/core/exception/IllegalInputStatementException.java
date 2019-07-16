package core.exception;

public class IllegalInputStatementException extends RuntimeException {
    /** a. */
    private String message;
    /**
     * a.
     */
    public IllegalInputStatementException() {
        super();
    }
    /**
     * a.
     *
     * @param messages a
     */
    public IllegalInputStatementException(final String messages) {
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

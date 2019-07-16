package core.exception;

public class TimeException extends RuntimeException {
    /**
     * a.
     */
    private String message;
    /**
     * a.
     */
    public TimeException() {
        super();
    }
    /**
     * a.
     *
     * @param messages a
     */
    public TimeException(final String messages) {
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

package core.exception;

public class TimeFormatException extends RuntimeException {
    /**
     * a.
     */
    private String message;
    /**
     * a.
     */
    public TimeFormatException() {
        super();
    }
    /**
     * a.
     *
     * @param messages a
     */
    public TimeFormatException(final String messages) {
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

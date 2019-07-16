package core.exception;

public class UsageTimeException extends RuntimeException {
    /**
     * a.
     */
    private String message;
    /**
     * a.
     */
    public UsageTimeException() {
        super();
    }
    /**
     * a.
     *
     * @param messages a
     */
    public UsageTimeException(final String messages) {
        super(messages);
        this.message = messages;
    }
    /**
     * a.
     *
     * @returna
     */
    @Override public String getMessage() {
        return message;
    }
}

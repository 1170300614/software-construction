package core.exception;

public class UsageException extends RuntimeException {
    /**
     * a.
     */
    private String message;
    /**
     * a.
     */
    public UsageException() {
        super();
    }
    /**
     * a.
     *
     * @param messages a
     */
    public UsageException(final String messages) {
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

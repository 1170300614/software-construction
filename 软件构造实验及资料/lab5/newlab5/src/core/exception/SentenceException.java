package core.exception;

public class SentenceException extends RuntimeException {
    /**
     * a.
     */
    private String message;
    /**
     * a.
     */
    public SentenceException() {
        super();
    }
    /**
     * a.
     *
     * @param messages a
     */
    public SentenceException(final String messages) {
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

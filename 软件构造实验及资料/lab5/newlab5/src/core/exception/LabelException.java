package core.exception;

public class LabelException extends RuntimeException {
    /** a. */
    private String message;
    /**
     * a.
     */
    public LabelException() {
        super();
    }
    /**
     * a.
     *
     * @param messages a
     */
    public LabelException(final String messages) {
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

package core.exception;

public class SameLabelException extends RuntimeException {
    /**
     * a.
     */
    private String message;
    /**
     * a.
     */
    public SameLabelException() {
        super();
    }
    /**
     * a.
     *
     * @param messages a
     */
    public SameLabelException(final String messages) {
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

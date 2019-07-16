package core.exception;

public class NumberFormatException extends RuntimeException {
    /**
     * a.
     */
    private String message;
    /**
     * a.
     */
    public NumberFormatException() {
        super();
    }
    /**
     * a.
     *
     * @param messages s
     */
    public NumberFormatException(final String messages) {
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

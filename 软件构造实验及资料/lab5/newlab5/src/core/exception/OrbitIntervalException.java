package core.exception;

public class OrbitIntervalException extends RuntimeException {
    /**
     * a.
     */
    private String message;
    /**
     * a.
     */
    public OrbitIntervalException() {
        super();
    }
    /**
     * a.
     *
     * @param messages f
     */
    public OrbitIntervalException(final String messages) {
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

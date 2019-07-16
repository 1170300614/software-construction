package core.exception;

public class OrbitException extends RuntimeException {
    /**
     * a.
     */
    private String message;
    /**
     * a.
     */
    public OrbitException() {
        super();
    }
    /**
     * a.
     *
     * @param messages a
     */
    public OrbitException(final String messages) {
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

package core.exception;

public class TrackNumberException extends RuntimeException {
    /**
     * a.
     */
    private String message;
    /**
     * a.
     */
    public TrackNumberException() {
        super();
    }
    /**
     * a.
     *
     * @param messages a
     */
    public TrackNumberException(final String messages) {
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

package core.exception;
/**
 * a.
 */
public class EqualLabelException extends RuntimeException {
    /**a.*/
    private String message;
    /**
     * a.
     */
    public EqualLabelException() {
        // TODO Auto-generated constructor stub
        super();
    }
    /**
     * a.
     *
     * @param messages c
     */
    public EqualLabelException(final String messages) {
        super(messages);
        this.message = messages;
    }
    /**
     * a.
     *
     * @return c
     */
    @Override public String getMessage() {
        return message;
    }
}

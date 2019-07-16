package core.exception;

public class IllegalInputException extends RuntimeException {
    /**a.
     */
    private String message;
    /**a.
     */
    public IllegalInputException() {
        super();
    }
    /**a.
     * @param messages a
     */
    public IllegalInputException(final String messages) {
        super(messages);
        this.message = messages;
    }
    /**a.
     * @return a
     */
    @Override public String getMessage() {
        return message;
    }
}

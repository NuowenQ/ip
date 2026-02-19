package speed.messages;

/**
 * Class for storing respond status from the user
 */
public class Response {
    private final String message;
    private final boolean isError;

    /**
     * constructor for Respond class
     * @param message store the reply of the bot
     */
    public Response(String message) {
        this.message = message;
        this.isError = false;
    }

    /**
     * constructor for Respond class
     * @param message store the reply of the bot
     */
    public Response(String message, boolean isError) {
        this.message = message;
        this.isError = isError;
    }

    /**
     * Obtain the error status of the response
     * @return The indicator whether the message is an error message
     */
    public boolean isError() {
        return isError;
    }

    /**
     * Obtain the message to output to the user
     * @return The message that to return to user
     */
    public String getMessage() {
        return this.message;
    }
}

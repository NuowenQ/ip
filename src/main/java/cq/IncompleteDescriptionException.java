package cq;

/**
 * Exception thrown when a task description is incomplete or missing required information.
 */
public class IncompleteDescriptionException extends Exception {
    /**
     * Constructs a new IncompleteDescriptionException with the error message.
     *
     * @param message The detail message explaining what information is missing.
     */
    public IncompleteDescriptionException(String message) {
        super(message);
    }
}

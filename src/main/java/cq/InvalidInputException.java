package cq;
/**
 * Exception thrown when a user enters an unrecognized command or invalid input.
 */
public class InvalidInputException extends Exception {
    /**
     * Constructs a new InvalidInputException with the specified detail message.
     *
     * @param message The detail message explaining why the input is invalid.
     */
    public InvalidInputException(String message) {
        super(message);
    }
}

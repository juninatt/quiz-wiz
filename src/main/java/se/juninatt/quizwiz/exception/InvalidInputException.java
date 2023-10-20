package se.juninatt.quizwiz.exception;

/**
 * Custom exception class representing a bad request.
 */
public class InvalidInputException extends RuntimeException {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message describing the error.
     */
    public InvalidInputException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * @param message The detail message describing the error.
     * @param cause   The cause of the exception.
     */
    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }

}

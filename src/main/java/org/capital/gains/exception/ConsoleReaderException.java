package org.capital.gains.exception;

/**
 * Exception thrown when errors occur during console reading operations.
 */
public class ConsoleReaderException extends Exception {

    /**
     * Constructs a new ConsoleReaderException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public ConsoleReaderException(String message) {
        super(message);
    }

}

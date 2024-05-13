package org.capital.gains.exception;

/**
 * StockMarketValidatorException is an exception class that is thrown when a validation fails due to a string not matching the specified regex pattern.
 */
public class StockMarketValidatorException extends Exception {

    /**
     * Constructs a new StockMarketValidatorException with the specified detail message.
     *
     * @param message the detail message (which is saved for later retrieval by the getMessage() method)
     */
    public StockMarketValidatorException(String message) {
        super(message);
    }

}

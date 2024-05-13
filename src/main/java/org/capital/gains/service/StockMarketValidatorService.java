package org.capital.gains.service;

import org.capital.gains.exception.StockMarketValidatorException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.capital.gains.util.MessagesContants.VALIDATION_REGEX_ERROR;

/**
 * The StockMarketValidatorService class provides methods to validate stock market operations based on a regex pattern.
 * It loads the regex pattern from an external properties file and uses it to validate input strings.
 */
public class StockMarketValidatorService {

    private final Pattern pattern;

    /**
     * Constructs a new StockMarketValidatorService instance.
     * Loads the regex pattern from the 'application.properties' file and compiles it into a Pattern object.
     * Throws an IllegalStateException if the properties file cannot be loaded or if the regex pattern is invalid.
     */
    public StockMarketValidatorService() {
        PropertiesReaderService propertiesReaderService = new PropertiesReaderService();

        String regex = propertiesReaderService.getProperty("regex.validator");

        pattern = Pattern.compile(regex);
    }

    /**
     * Validates the input string against the regex pattern.
     * Throws a StockMarketValidatorException if the input string does not match the pattern.
     *
     * @param input The input string to validate.
     * @throws StockMarketValidatorException If the input string does not match the regex pattern.
     */
    public void validatePatternStockLine(String input) throws StockMarketValidatorException {
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) {
            throw new StockMarketValidatorException(VALIDATION_REGEX_ERROR);
        }
    }

}

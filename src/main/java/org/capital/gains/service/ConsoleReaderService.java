package org.capital.gains.service;

import org.capital.gains.exception.ConsoleReaderException;
import org.capital.gains.exception.StockMarketValidatorException;
import org.capital.gains.mapper.JsonToStockMarketMapper;
import org.capital.gains.models.StockMarket;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.capital.gains.util.MessagesContants.CONSOLE_ERROR;

/**
 * Utility class for reading stock market operations from the console input.
 */
public class ConsoleReaderService {

    private final Scanner scanner;
    private final JsonToStockMarketMapper converter;
    private final StockMarketValidatorService validatorService;

    /**
     * Constructs a new ConsoleReaderService with a default scanner and JSON to StockMarket mapper.
     */
    public ConsoleReaderService() {
        scanner = new Scanner(System.in);
        converter = new JsonToStockMarketMapper();
        validatorService = new StockMarketValidatorService();
    }

    /**
     * Reads stock market operations from the console input and converts them into a list of StockMarket objects.
     *
     * @return a list of StockMarket objects representing the stock market operations entered by the user
     * @throws ConsoleReaderException if an error occurs while reading or parsing the input
     */
    public List<List<StockMarket>> readStockMarketOperations() throws ConsoleReaderException, StockMarketValidatorException {
        List<List<StockMarket>> operations = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                // End read process when client enter blank line
                break;
            }

            // validate line structure to avoid read incorrect documents
            validatorService.validatePatternStockLine(line);

            try {
                List<StockMarket> operationsLine = converter.convertListToStockMarket(line);
                operations.add(operationsLine);
            } catch (Exception e) {
                throw new ConsoleReaderException(CONSOLE_ERROR);
            }
        }
        scanner.close();

        return operations;
    }

}

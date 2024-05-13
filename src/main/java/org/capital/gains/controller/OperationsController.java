package org.capital.gains.controller;

import org.capital.gains.exception.ConsoleReaderException;
import org.capital.gains.exception.StockMarketValidatorException;
import org.capital.gains.models.StockMarket;
import org.capital.gains.models.StockMarketResponse;
import org.capital.gains.service.CapitalGainTaxService;
import org.capital.gains.service.ConsoleReaderService;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The OperationsController class manages the flow of operations related to calculating taxes from stock market data.
 */
public class OperationsController {

    private static final Logger logger = Logger.getLogger(OperationsController.class.getName());
    private final ConsoleReaderService consoleReaderService;
    private final CapitalGainTaxService capitalGainTaxService;

    /**
     * Constructs a new OperationsController and initializes required services.
     */
    public OperationsController(ConsoleReaderService consoleReaderService, CapitalGainTaxService capitalGainTaxService) {
        this.consoleReaderService = consoleReaderService;
        this.capitalGainTaxService = capitalGainTaxService;
    }

    /**
     * Retrieves taxes from stock market operations and prints them to the console.
     * Catches ConsoleReaderException and StockMarketValidatorException and logs error messages.
     */
    public void getTaxesFromOperations() {
        try {
            List<List<StockMarket>> operations = consoleReaderService.readStockMarketOperations();
            List<List<StockMarketResponse>> taxes = this.capitalGainTaxService.calculateTaxes(operations);
            for (int i = 0; i < taxes.size(); i++) {
                System.out.println(taxes.get(i) + (i < taxes.size() - 1 ? "," : ""));
            }
        } catch (ConsoleReaderException | StockMarketValidatorException e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }

}

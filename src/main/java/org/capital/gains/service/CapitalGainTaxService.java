package org.capital.gains.service;

import org.capital.gains.models.StockMarket;
import org.capital.gains.models.StockMarketResponse;

import java.util.ArrayList;
import java.util.List;

import static org.capital.gains.enums.OperationTypeEnum.BUY;
import static org.capital.gains.enums.OperationTypeEnum.SELL;

/**
 * This class provides functionality to calculate capital gain taxes based on stock market operations.
 */
public class CapitalGainTaxService {

    private double weightedAverage;
    private int currentStockQuantity;
    private double losses;
    private final double minimumAmount;
    private final double taxConfig;

    /**
     * Constructs a new CapitalGainTaxService and initializes tax configurations from properties file.
     * Initializes weighted average, current stock quantity, and losses to zero.
     */
    public CapitalGainTaxService() {
        PropertiesReaderService propertiesReaderService = new PropertiesReaderService();
        minimumAmount = Double.parseDouble(propertiesReaderService.getProperty("config.minimum-amount"));
        taxConfig = Double.parseDouble(propertiesReaderService.getProperty("config.tax"));

        weightedAverage = 0;
        currentStockQuantity = 0;
        losses = 0;
    }

    /**
     * Calculates taxes for the provided list of stock market operations.
     *
     * @param stock a list of lists containing stock market operations
     * @return a list of lists containing taxes for each operation
     */
    public List<List<StockMarketResponse>> calculateTaxes(List<List<StockMarket>> stock) {
        List<List<StockMarketResponse>> totalTaxes = new ArrayList<>();

        for (List<StockMarket> operations : stock) {
            List<StockMarketResponse> taxes = new ArrayList<>();
            for (StockMarket operation : operations) {
                if (BUY.getKey().equals(operation.getOperationType())) {
                    buyStocks(operation.getUnitCost(), operation.getQuantity());
                    taxes.add(new StockMarketResponse("tax", 0)); // No tax for buying stocks
                } else if (SELL.getKey().equals(operation.getOperationType())) {
                    double tax = sellStocks(operation.getUnitCost(), operation.getQuantity());
                    taxes.add(new StockMarketResponse("tax", tax));
                }
            }
            totalTaxes.add(taxes);
        }

        return totalTaxes;
    }

    /**
     * Calculates the weighted average and updates the current stock quantity after buying stocks.
     *
     * @param unitCost the unit cost of the stocks
     * @param quantity the quantity of stocks bought
     */
    private void buyStocks(double unitCost, int quantity) {
        weightedAverage = ((currentStockQuantity * weightedAverage) + (quantity * unitCost))
                          / (currentStockQuantity + quantity);
        currentStockQuantity += quantity;
    }

    /**
     * Calculates taxes and updates losses after selling stocks.
     *
     * @param unitCost the unit cost of the stocks
     * @param quantity the quantity of stocks sold
     * @return the tax amount for the stock sale
     */
    private double sellStocks(double unitCost, int quantity) {
        double minimumProfit = weightedAverage * quantity;
        double sale = unitCost * quantity;

        currentStockQuantity -= quantity;

        if (sale <= minimumAmount && unitCost > weightedAverage) {
            return 0;
        }

        // loss
        if (sale < minimumProfit) {
            losses += (minimumProfit - sale);
            return 0;
        }

        // profit
        double netProfit = Math.abs(minimumProfit - sale);

        // If there are accumulated losses
        if (losses > 0) {
            if (losses >= netProfit) {
                losses -= netProfit;
                return 0;
            } else {
                netProfit -= losses;
                losses = 0;
            }
        }

        return netProfit * taxConfig;
    }

}

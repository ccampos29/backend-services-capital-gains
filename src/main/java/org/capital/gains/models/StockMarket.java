package org.capital.gains.models;

/**
 * Represents a stock market operation with information such as operation type, unit cost, and quantity.
 */
public class StockMarket {

    private String operationType;
    private double unitCost;
    private int quantity;

    /**
     * Constructs a new StockMarket object with the specified operation type, unit cost, and quantity.
     *
     * @param operationType the type of operation (e.g., "buy" or "sell")
     * @param unitCost      the unit cost of the operation
     * @param quantity      the quantity of the operation
     */
    public StockMarket(String operationType, double unitCost, int quantity) {
        this.operationType = operationType;
        this.unitCost = unitCost;
        this.quantity = quantity;
    }

    /**
     * Gets the type of operation.
     *
     * @return the operation type
     */
    public String getOperationType() {
        return operationType;
    }

    /**
     * Gets the unit cost of the operation.
     *
     * @return the unit cost
     */
    public double getUnitCost() {
        return unitCost;
    }


    /**
     * Gets the quantity of the operation.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

}

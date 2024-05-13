package org.capital.gains.enums;

/**
 * Enum representing keys used in stock market operations.
 */
public enum keyStockMarketEnum {

    /**
     * Key representing the type of operation (e.g., "buy" or "sell").
     */
    OPERATION("operation"),
    /**
     * Key representing the unit cost of the operation.
     */
    UNIT_COST("unit-cost"),
    /**
     * Key representing the quantity of the operation.
     */
    QUANTITY("quantity");

    private final String key;

    /**
     * Constructs a new KeyStockMarketEnum with the specified key.
     *
     * @param key the key value
     */
    keyStockMarketEnum(String key) {
        this.key = key;
    }

    /**
     * Returns the key value.
     *
     * @return the key value
     */
    public String getKey() {
        return key;
    }
}

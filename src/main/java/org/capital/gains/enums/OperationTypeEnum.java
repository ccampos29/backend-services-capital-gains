package org.capital.gains.enums;

/**
 * OperationTypeEnum is an enumeration representing the types of operations in a stock market, such as buy or sell.
 */
public enum OperationTypeEnum {

    /**
     * Represents a "buy" operation in the stock market.
     */
    BUY("buy"),

    /**
     * Represents a "sell" operation in the stock market.
     */
    SELL("sell");

    private final String key;

    /**
     * Constructs a new OperationTypeEnum with the specified key.
     *
     * @param key the key associated with the operation type
     */
    OperationTypeEnum(String key) {
        this.key = key;
    }

    /**
     * Returns the key associated with the operation type.
     *
     * @return the key associated with the operation type
     */
    public String getKey() {
        return key;
    }
}
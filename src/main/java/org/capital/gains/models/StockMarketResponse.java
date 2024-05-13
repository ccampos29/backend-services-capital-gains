package org.capital.gains.models;

import java.util.Objects;

/**
 * This class represents a Data Transfer Object (DTO) for displaying information about each stock market operation.
 */
public class StockMarketResponse {

    private String key;
    private double tax;

    /**
     * Constructs a new StockMarketResponse object with the given key and tax amount.
     *
     * @param key the key associated with the stock market operation
     * @param tax the tax amount associated with the stock market operation
     */
    public StockMarketResponse(String key, double tax) {
        this.key = key;
        this.tax = tax;
    }

    /**
     * Returns a string representation of the StockMarketResponse object in the format: {"tax":X.XX}
     *
     * @return a string representation of the StockMarketResponse object
     */
    @Override
    public String toString() {
        return "{\"tax\":" + tax + "}";
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param o the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockMarketResponse that = (StockMarketResponse) o;
        return Double.compare(tax, that.tax) == 0 && Objects.equals(key, that.key);
    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of hash tables such as those provided by HashMap.
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(key, tax);
    }
}

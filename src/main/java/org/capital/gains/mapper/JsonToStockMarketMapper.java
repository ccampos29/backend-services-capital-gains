package org.capital.gains.mapper;

import org.capital.gains.models.StockMarket;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static org.capital.gains.enums.keyStockMarketEnum.OPERATION;
import static org.capital.gains.enums.keyStockMarketEnum.QUANTITY;
import static org.capital.gains.enums.keyStockMarketEnum.UNIT_COST;

/**
 * Mapper class to convert JSON strings to StockMarket objects.
 */
public class JsonToStockMarketMapper {

    /**
     * Converts a JSON array string representing a list of stock market operations into a list of StockMarket objects.
     *
     * @param jsonElements the JSON array string to convert
     * @return a list of StockMarket objects representing the stock market operations
     */
    public List<StockMarket> convertListToStockMarket(String jsonElements) {
        List<StockMarket> stockMarketList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(jsonElements);

        jsonArray.forEach(element -> stockMarketList.add(this.convertToStockMarket((JSONObject) element)));

        return stockMarketList;
    }

    /**
     * Converts a JSON object representing a single stock market operation into a StockMarket object.
     *
     * @param jsonElement the JSON object to convert
     * @return a StockMarket object representing the stock market operation
     */
    public StockMarket convertToStockMarket(JSONObject jsonElement) {
        String operationType = jsonElement.getString(OPERATION.getKey());
        double unitCost = jsonElement.getDouble(UNIT_COST.getKey());
        int quantity = jsonElement.getInt(QUANTITY.getKey());
        return new StockMarket(operationType, unitCost, quantity);
    }
}
package org.capital.gains.service;

import org.capital.gains.exception.StockMarketValidatorException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class StockMarketValidatorServiceTest {

    @Test
    void validatePatternStockLine_ValidInput_ShouldNotThrowException() throws StockMarketValidatorException {
        String input = "[{\"operation\":\"buy\", \"unit-cost\":10.00, \"quantity\": 100},{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 50},{\"operation\":\"sell\", \"unit-cost\":15.00, \"quantity\": 50}]";

        PropertiesReaderService propertiesReaderService = mock(PropertiesReaderService.class);
        when(propertiesReaderService.getProperty("regex.validator")).thenReturn("\\\\[\\\\s*\\\\{\\\\s*\"operation\"\\\\s*:\\\\s*\"(buy|sell)\"\\\\s*,\\\\s*\"unit-cost\"\\\\s*:\\\\s*\\\\d+(\\\\.\\\\d+)?\\\\s*,\\\\s*\"quantity\"\\\\s*:\\\\s*\\\\d+\\\\s*\\\\}(\\\\s*,\\\\s*\\\\{\\\\s*\"operation\"\\\\s*:\\\\s*\"(buy|sell)\"\\\\s*,\\\\s*\"unit-cost\"\\\\s*:\\\\s*\\\\d+(\\\\.\\\\d+)?\\\\s*,\\\\s*\"quantity\"\\\\s*:\\\\s*\\\\d+\\\\s*\\\\})*\\\\s*\\\\]");

        StockMarketValidatorService validatorService = new StockMarketValidatorService();

        validatorService.validatePatternStockLine(input); // No exception expected
    }

    @Test
    void validatePatternStockLine_InvalidInput_ShouldThrowException() {
        String input = "Invalid input";

        PropertiesReaderService propertiesReaderService = mock(PropertiesReaderService.class);
        when(propertiesReaderService.getProperty("regex.validator")).thenReturn("^\\{.*\\}$");

        StockMarketValidatorService validatorService = new StockMarketValidatorService();

        assertThrows(StockMarketValidatorException.class, () -> validatorService.validatePatternStockLine(input));
    }

}

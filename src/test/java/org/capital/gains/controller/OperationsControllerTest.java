package org.capital.gains.controller;

import org.capital.gains.exception.ConsoleReaderException;
import org.capital.gains.exception.StockMarketValidatorException;
import org.capital.gains.models.StockMarket;
import org.capital.gains.models.StockMarketResponse;
import org.capital.gains.service.CapitalGainTaxService;
import org.capital.gains.service.ConsoleReaderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


class OperationsControllerTest {
    private ConsoleReaderService consoleReaderServiceMock;
    private CapitalGainTaxService capitalGainTaxServiceMock;
    private OperationsController operationsController;

    @BeforeEach
    public void setUp() {
        consoleReaderServiceMock = mock(ConsoleReaderService.class);
        capitalGainTaxServiceMock = mock(CapitalGainTaxService.class);
        operationsController = new OperationsController(consoleReaderServiceMock, capitalGainTaxServiceMock);
    }

    @Test
    void getTaxesFromOperations_ShouldPrintTaxes() throws ConsoleReaderException, StockMarketValidatorException {
        List<StockMarket> stockMarketList = List.of(new StockMarket("sell", 10, 100));
        List<StockMarketResponse> stockMarketResponseList = List.of(new StockMarketResponse("key", 1));
        when(consoleReaderServiceMock.readStockMarketOperations()).thenReturn(List.of(stockMarketList));
        when(capitalGainTaxServiceMock.calculateTaxes(any())).thenReturn(List.of(stockMarketResponseList));

        operationsController.getTaxesFromOperations();

        verify(consoleReaderServiceMock).readStockMarketOperations();
        verify(capitalGainTaxServiceMock).calculateTaxes(any());
    }

}

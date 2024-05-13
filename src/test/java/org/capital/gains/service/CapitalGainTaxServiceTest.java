package org.capital.gains.service;

import org.capital.gains.models.StockMarket;
import org.capital.gains.models.StockMarketResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CapitalGainTaxServiceTest {

    private CapitalGainTaxService capitalGainTaxService;

    @BeforeEach
    void setUp() {
        capitalGainTaxService = new CapitalGainTaxService();
    }

    @Test
    void testCalculateTaxes_Case1() {
        List<List<StockMarketResponse>> expectedResponse = new ArrayList<>(
                List.of(List.of(
                        new StockMarketResponse("tax", 0),
                        new StockMarketResponse("tax", 0),
                        new StockMarketResponse("tax", 0)
                ))
        );

        List<List<StockMarket>> stock = new ArrayList<>(
                List.of(List.of(
                        new StockMarket("buy", 10.00, 100),
                        new StockMarket("sell", 15.00, 50),
                        new StockMarket("sell", 15.00, 50)
                ))
        );

        testCase(stock, expectedResponse);
    }

    @Test
    void testCalculateTaxes_Case2() {
        List<List<StockMarketResponse>> expectedResponse = new ArrayList<>(
                List.of(List.of(
                        new StockMarketResponse("tax", 0),
                        new StockMarketResponse("tax", 10000),
                        new StockMarketResponse("tax", 0)
                ))
        );

        List<List<StockMarket>> stock = new ArrayList<>(
                List.of(List.of(
                        new StockMarket("buy", 10.00, 10000),
                        new StockMarket("sell", 20.00, 5000),
                        new StockMarket("sell", 5.00, 5000)
                ))
        );

        testCase(stock, expectedResponse);
    }

    @Test
    void testCalculateTaxes_Case1Case2() {
        List<List<StockMarketResponse>> expectedResponse = new ArrayList<>(
                List.of(List.of(
                                new StockMarketResponse("tax", 0),
                                new StockMarketResponse("tax", 0),
                                new StockMarketResponse("tax", 0)
                        ),
                        List.of(
                                new StockMarketResponse("tax", 0),
                                new StockMarketResponse("tax", 10000),
                                new StockMarketResponse("tax", 0)
                        )
                )
        );

        List<List<StockMarket>> stock = new ArrayList<>(
                List.of(List.of(
                        new StockMarket("buy", 10.00, 100),
                        new StockMarket("sell", 15.00, 50),
                        new StockMarket("sell", 15.00, 50)
                ), List.of(
                        new StockMarket("buy", 10.00, 10000),
                        new StockMarket("sell", 20.00, 5000),
                        new StockMarket("sell", 5.00, 5000)
                ))
        );

        testCase(stock, expectedResponse);
    }

    @Test
    void testCalculateTaxes_Case3() {
        List<List<StockMarketResponse>> expectedResponse = new ArrayList<>(
                List.of(List.of(
                        new StockMarketResponse("tax", 0),
                        new StockMarketResponse("tax", 0),
                        new StockMarketResponse("tax", 1000)
                ))
        );

        List<List<StockMarket>> stock = new ArrayList<>(
                List.of(List.of(
                        new StockMarket("buy", 10.00, 10000),
                        new StockMarket("sell", 5.00, 5000),
                        new StockMarket("sell", 20.00, 3000)
                ))
        );

        testCase(stock, expectedResponse);
    }

    @Test
    void testCalculateTaxes_Case4() {
        List<List<StockMarketResponse>> expectedResponse = new ArrayList<>(
                List.of(List.of(
                        new StockMarketResponse("tax", 0),
                        new StockMarketResponse("tax", 0),
                        new StockMarketResponse("tax", 0)
                ))
        );

        List<List<StockMarket>> stock = new ArrayList<>(
                List.of(List.of(
                        new StockMarket("buy", 10.00, 10000),
                        new StockMarket("buy", 55.00, 5000),
                        new StockMarket("sell", 15.00, 10000)
                ))
        );

        testCase(stock, expectedResponse);
    }

    @Test
    void testCalculateTaxes_Case5() {
        List<List<StockMarketResponse>> expectedResponse = new ArrayList<>(
                List.of(List.of(
                        new StockMarketResponse("tax", 0),
                        new StockMarketResponse("tax", 0),
                        new StockMarketResponse("tax", 0),
                        new StockMarketResponse("tax", 10000)
                ))
        );

        List<List<StockMarket>> stock = new ArrayList<>(
                List.of(List.of(
                        new StockMarket("buy", 10.00, 10000),
                        new StockMarket("buy", 25.00, 5000),
                        new StockMarket("sell", 15.00, 10000),
                        new StockMarket("sell", 25.00, 5000)
                ))
        );

        testCase(stock, expectedResponse);
    }

    @Test
    void testCalculateTaxes_Case6() {
        List<List<StockMarketResponse>> expectedResponse = new ArrayList<>(
                List.of(List.of(
                        new StockMarketResponse("tax", 0),
                        new StockMarketResponse("tax", 0),
                        new StockMarketResponse("tax", 0),
                        new StockMarketResponse("tax", 0),
                        new StockMarketResponse("tax", 3000)
                ))
        );

        List<List<StockMarket>> stock = new ArrayList<>(
                List.of(List.of(
                        new StockMarket("buy", 10.00, 10000),
                        new StockMarket("sell", 2.00, 5000),
                        new StockMarket("sell", 20.00, 2000),
                        new StockMarket("sell", 20.00, 2000),
                        new StockMarket("sell", 25.00, 1000)
                ))
        );

        testCase(stock, expectedResponse);
    }

    @Test
    void testCalculateTaxes_Case7() {
        List<List<StockMarketResponse>> expectedResponse = new ArrayList<>(
                List.of(List.of(
                        new StockMarketResponse("tax", 0),
                        new StockMarketResponse("tax", 0),
                        new StockMarketResponse("tax", 0),
                        new StockMarketResponse("tax", 0),
                        new StockMarketResponse("tax", 3000),
                        new StockMarketResponse("tax", 0),
                        new StockMarketResponse("tax", 0),
                        new StockMarketResponse("tax", 3700),
                        new StockMarketResponse("tax", 0)
                ))
        );

        List<List<StockMarket>> stock = new ArrayList<>(
                List.of(List.of(
                        new StockMarket("buy", 10.00, 10000),
                        new StockMarket("sell", 2.00, 5000),
                        new StockMarket("sell", 20.00, 2000),
                        new StockMarket("sell", 20.00, 2000),
                        new StockMarket("sell", 25.00, 1000),
                        new StockMarket("buy", 20.00, 10000),
                        new StockMarket("sell", 15.00, 5000),
                        new StockMarket("sell", 30.00, 4350),
                        new StockMarket("sell", 30.00, 650)
                ))
        );

        testCase(stock, expectedResponse);
    }

    @Test
    void testCalculateTaxes_Case8() {
        List<List<StockMarketResponse>> expectedResponse = new ArrayList<>(
                List.of(List.of(
                        new StockMarketResponse("tax", 0),
                        new StockMarketResponse("tax", 80000),
                        new StockMarketResponse("tax", 0),
                        new StockMarketResponse("tax", 60000)
                ))
        );

        List<List<StockMarket>> stock = new ArrayList<>(
                List.of(List.of(
                        new StockMarket("buy", 10.00, 10000),
                        new StockMarket("sell", 50.00, 10000),
                        new StockMarket("buy", 20.00, 10000),
                        new StockMarket("sell", 50.00, 10000)
                ))
        );

        testCase(stock, expectedResponse);
    }

    @Test
    void testCalculateTaxes_Case9() {
        List<List<StockMarketResponse>> expectedResponse = new ArrayList<>(
                List.of(List.of(
                        new StockMarketResponse("tax", 0),
                        new StockMarketResponse("tax", 0),
                        new StockMarketResponse("tax", 0),
                        new StockMarketResponse("tax", 0),
                        new StockMarketResponse("tax", 0),
                        new StockMarketResponse("tax", 0),
                        new StockMarketResponse("tax", 1000),
                        new StockMarketResponse("tax", 2400)
                ))
        );

        List<List<StockMarket>> stock = new ArrayList<>(
                List.of(List.of(
                        new StockMarket("buy", 5000.00, 10),
                        new StockMarket("sell", 4000.00, 5),
                        new StockMarket("buy", 15000.00, 5),
                        new StockMarket("buy", 4000.00, 2),
                        new StockMarket("buy", 23000.00, 2),
                        new StockMarket("sell", 20000.00, 1),
                        new StockMarket("sell", 12000.00, 10),
                        new StockMarket("sell", 15000.00, 3)
                ))
        );

        testCase(stock, expectedResponse);
    }

    private void testCase(List<List<StockMarket>> stock, List<List<StockMarketResponse>> expectedResponse) {
        List<List<StockMarketResponse>> taxes = capitalGainTaxService.calculateTaxes(stock);

        assertEquals(expectedResponse.size(), taxes.size());
        for (int i = 0; i < expectedResponse.size(); i++) {
            assertArrayEquals(expectedResponse.get(i).toArray(), taxes.get(i).toArray());
        }
    }

}

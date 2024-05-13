package org.capital.gains.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PropertiesReaderServiceTest {
    private PropertiesReaderService propertiesReaderService;
    private Properties mockProperties;

    @BeforeEach
    void setUp() {
        mockProperties = mock(Properties.class);
        propertiesReaderService = new PropertiesReaderService() {
            public Properties getProperties() {
                return mockProperties;
            }
        };
    }

    @Test
    void getProperty_existingKey_returnsValue() {
        when(mockProperties.getProperty("config.tax")).thenReturn("0.2");

        String actualValue = propertiesReaderService.getProperty("config.tax");

        Assertions.assertEquals("0.2", actualValue);
    }

    @Test
    void getProperty_nonExistingKey_returnsNull() {
        when(mockProperties.getProperty("nonExistingKey")).thenReturn(null);

        String actualValue = propertiesReaderService.getProperty("nonExistingKey");

        Assertions.assertNull(actualValue);
    }

}

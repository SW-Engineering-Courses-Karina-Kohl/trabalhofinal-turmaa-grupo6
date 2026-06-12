package br.edu.ufrgs.util;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class DateValidatorTest {

    // Valid scenarios
    @Test
    void shouldAcceptValidDate() {
        LocalDate validDate = LocalDate.of(2026, 10, 10);
        LocalDate result = DateValidator.validate(validDate);
        
        assertEquals(validDate, result);
    }

    // Null input handling
    @Test
    void shouldThrowExceptionWhenDateIsNull() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> DateValidator.validate(null)
        );
        
        assertEquals("Data do pedido não informada.", exception.getMessage());
    }

    // Utility class constructor coverage 
    @Test
    void shouldInstantiateUtilityClassForCoverage() {
        DateValidator validator = new DateValidator();
        assertNotNull(validator);
    }
}
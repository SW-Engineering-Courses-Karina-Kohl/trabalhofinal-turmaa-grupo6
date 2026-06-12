package br.edu.ufrgs.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class DistanceValidatorTest {

    private static final double DOUBLE_DELTA = 0.0001;

    // Valid scenarios
    @Test
    void shouldAcceptValidDistance() {
        double result = DistanceValidator.validate("150.5");
        assertEquals(150.5, result, DOUBLE_DELTA);
    }

    // Null input handling
    @Test
    void shouldThrowExceptionWhenDistanceIsNull() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> DistanceValidator.validate(null)
        );
        assertEquals("Distância não informada.", exception.getMessage());
    }

    // Invalid format and type handling
    @Test
    void shouldThrowExceptionWhenDistanceIsInvalidString() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> DistanceValidator.validate("texto_invalido")
        );
        assertEquals("Distância deve ser um número válido.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDistanceIsEmptyString() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> DistanceValidator.validate("   ")
        );
        assertEquals("Distância deve ser um número válido.", exception.getMessage());
    }

    // Special floating-point value handling
    @Test
    void shouldThrowExceptionWhenDistanceIsNaN() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> DistanceValidator.validate("NaN")
        );
        assertEquals("Distância deve ser maior que zero.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDistanceIsInfinite() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> DistanceValidator.validate("Infinity")
        );
        assertEquals("Distância deve ser maior que zero.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDistanceIsNegativeInfinite() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> DistanceValidator.validate("-Infinity")
        );
        assertEquals("Distância deve ser maior que zero.", exception.getMessage());
    }

    // Business logic boundary validation
    @Test
    void shouldThrowExceptionWhenDistanceIsZero() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> DistanceValidator.validate("0.0")
        );
        assertEquals("Distância deve ser maior que zero.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDistanceIsNegative() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> DistanceValidator.validate("-10.5")
        );
        assertEquals("Distância deve ser maior que zero.", exception.getMessage());
    }
}
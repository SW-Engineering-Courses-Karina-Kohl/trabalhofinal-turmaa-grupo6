package br.edu.ufrgs.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class WeightValidatorTest {

    private static final double DOUBLE_DELTA = 0.0001;

    // Valid scenarios
    @Test
    void shouldAcceptValidWeight() {
        double result = WeightValidator.validate("2.5");
        assertEquals(2.5, result, DOUBLE_DELTA);
    }

    // Null input handling
    @Test
    void shouldThrowExceptionWhenWeightIsNull() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> WeightValidator.validate(null)
        );
        assertEquals("Peso não informado.", exception.getMessage());
    }

    // Invalid format and type handling
    @Test
    void shouldThrowExceptionWhenWeightIsInvalidString() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> WeightValidator.validate("texto_invalido")
        );
        assertEquals("Peso deve ser um número válido.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenWeightIsEmptyString() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> WeightValidator.validate("   ")
        );
        assertEquals("Peso deve ser um número válido.", exception.getMessage());
    }

    // Special floating-point value handling
    @Test
    void shouldThrowExceptionWhenWeightIsNaN() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> WeightValidator.validate("NaN")
        );
        assertEquals("Peso deve ser maior que zero.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenWeightIsInfinite() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> WeightValidator.validate("Infinity")
        );
        assertEquals("Peso deve ser maior que zero.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenWeightIsNegativeInfinite() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> WeightValidator.validate("-Infinity")
        );
        assertEquals("Peso deve ser maior que zero.", exception.getMessage());
    }

    // Business logic boundary validation
    @Test
    void shouldThrowExceptionWhenWeightIsZero() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> WeightValidator.validate("0.0")
        );
        assertEquals("Peso deve ser maior que zero.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenWeightIsNegative() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> WeightValidator.validate("-5.5")
        );
        assertEquals("Peso deve ser maior que zero.", exception.getMessage());
    }
}
package br.edu.ufrgs.util;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class OrderIdValidatorTest {

    // Valid scenarios
    @Test
    void shouldAcceptValidOrderId() {
        Set<Integer> usedIds = new HashSet<>();
        int result = OrderIdValidator.validate("ORD-123", usedIds);
        assertEquals(123, result);
    }

    @Test
    void shouldAcceptValidOrderIdWithLeadingAndTrailingSpaces() {
        Set<Integer> usedIds = new HashSet<>();
        // Validates if the trim() is working properly before processing
        int result = OrderIdValidator.validate("   ORD-456   ", usedIds);
        assertEquals(456, result);
    }

    // Null input handling
    @Test
    void shouldThrowExceptionWhenOrderIdIsNull() {
        Set<Integer> usedIds = new HashSet<>();
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> OrderIdValidator.validate(null, usedIds)
        );
        assertEquals("ID do pedido não informado.", exception.getMessage());
    }

    // Empty or blank string handling
    @Test
    void shouldThrowExceptionWhenOrderIdIsEmptyString() {
        Set<Integer> usedIds = new HashSet<>();
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> OrderIdValidator.validate("", usedIds)
        );
        assertEquals("ID do pedido vazio.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenOrderIdIsBlankSpaces() {
        Set<Integer> usedIds = new HashSet<>();
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> OrderIdValidator.validate("   ", usedIds)
        );
        assertEquals("ID do pedido vazio.", exception.getMessage());
    }

    // Invalid format and prefix handling
    @Test
    void shouldThrowExceptionWhenOrderIdLacksPrefix() {
        Set<Integer> usedIds = new HashSet<>();
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> OrderIdValidator.validate("123", usedIds)
        );
        assertEquals("ID do pedido deve começar com ORD-.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenOrderIdHasInvalidNumericPart() {
        Set<Integer> usedIds = new HashSet<>();
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> OrderIdValidator.validate("ORD-12A", usedIds)
        );
        assertEquals("Parte numérica do ID do pedido inválida.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenOrderIdHasNoNumericPart() {
        Set<Integer> usedIds = new HashSet<>();
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> OrderIdValidator.validate("ORD-", usedIds)
        );
        assertEquals("Parte numérica do ID do pedido inválida.", exception.getMessage());
    }

    // Business logic boundary validation
    @Test
    void shouldThrowExceptionWhenOrderIdIsZero() {
        Set<Integer> usedIds = new HashSet<>();
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> OrderIdValidator.validate("ORD-0", usedIds)
        );
        assertEquals("ID do pedido deve ser maior que zero.", exception.getMessage());
    }

    // Duplicate tracking validation
    @Test
    void shouldThrowExceptionWhenOrderIdIsDuplicate() {
        Set<Integer> usedIds = new HashSet<>();
        usedIds.add(99); 
        
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> OrderIdValidator.validate("ORD-99", usedIds)
        );
        assertEquals("Pedido duplicado: ORD-99", exception.getMessage());
    }
}
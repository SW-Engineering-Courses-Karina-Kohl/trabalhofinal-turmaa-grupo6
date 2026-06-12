package br.edu.ufrgs.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class ClientValidatorTest {

    // Valid scenarios
    @Test
    void shouldAcceptValidClientName() {
        String result = ClientValidator.validate("Loja Tech");
        assertEquals("Loja Tech", result);
    }

    @Test
    void shouldTrimWhitespaceFromValidClientName() {
        String result = ClientValidator.validate("   Loja Tech   ");
        assertEquals("Loja Tech", result);
    }

    // Null input handling
    @Test
    void shouldThrowExceptionWhenClientIsNull() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> ClientValidator.validate(null)
        );
        assertEquals("Cliente não informado.", exception.getMessage());
    }

    // Empty or blank string handling
    @Test
    void shouldThrowExceptionWhenClientIsEmptyString() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> ClientValidator.validate("")
        );
        assertEquals("Nome do cliente vazio.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenClientIsBlankSpaces() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> ClientValidator.validate("   ")
        );
        assertEquals("Nome do cliente vazio.", exception.getMessage());
    }

    // Utility class constructor coverage 
    @Test
    void shouldInstantiateUtilityClassForCoverage() {
        ClientValidator validator = new ClientValidator();
        assertNotNull(validator);
    }
}
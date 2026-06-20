package br.edu.ufrgs.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

class CompanyValidatorTest {

    private static final double DOUBLE_DELTA = 0.0001;

    // Factor validation handling
    @Test
    void shouldAcceptPositiveFactor() {
        double result = CompanyValidator.validateFactor("multiplicador_expresso", 1.5);
        assertEquals(1.5, result, DOUBLE_DELTA);
    }

    @Test
    void shouldAcceptZeroFactor() {
        double result = CompanyValidator.validateFactor("desconto_dias", 0.0);
        assertEquals(0.0, result, DOUBLE_DELTA);
    }

    @Test
    void shouldThrowExceptionWhenFactorIsNegative() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> CompanyValidator.validateFactor("fator_distancia", -0.5)
        );
        assertEquals("Parâmetro fator_distancia não pode ser negativo.", exception.getMessage());
    }

    // Presence validation handling
    @Test
    void shouldAcceptPresentValue() {
        // Since the method returns void, execution without throwing an exception implies success
        CompanyValidator.validatePresence("nome_empresa", "Logística S/A");
    }

    @Test
    void shouldThrowExceptionWhenValueIsNull() {
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> CompanyValidator.validatePresence("prazo_base", null)
        );
        assertEquals("Parâmetro prazo_base não encontrado.", exception.getMessage());
    }

    // Utility class constructor coverage
    @Test
    void shouldInstantiateUtilityClassForCoverage() {
        CompanyValidator validator = new CompanyValidator();
        assertNotNull(validator);
    }
}
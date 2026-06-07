package br.edu.ufrgs.provider;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import br.edu.ufrgs.model.FreightCompany;

class CsvCompanyProviderTest {

    // 1. ERROR SCENARIOS: CORRUPTED / INVALID CSV FILES
    static Stream<List<String>> corruptedCasesProvider() {
        return Stream.of(
            Arrays.asList("parametro,valor", "fator_distancia_km,TEXTO", "fator_peso_kg,2.10", "multiplicador_expresso,1.5", "prazo_base_dias,2"),
            Arrays.asList("parametro,valor", "fator_distancia_km 0.05", "fator_peso_kg 2.10", "multiplicador_expresso 1.5", "prazo_base_dias 2"),
            Arrays.asList("parametro,valor", "coluna_errada,0.05", "fator_peso_kg,2.10", "multiplicador_expresso,1.5", "prazo_base_dias,2"),
            Arrays.asList("parametro,valor", "fator_distancia_km,-0.05", "fator_peso_kg,2.10", "multiplicador_expresso,1.5", "prazo_base_dias,-5")
        );
    }

    @ParameterizedTest
    @MethodSource("corruptedCasesProvider")
    void getCompanyReturnNullWhenCsvHasInvalidData(List<String> lines) {
        CsvCompanyProvider provider = new CsvCompanyProvider(lines);
        FreightCompany company = provider.getCompany();
        assertNull(company);
    }

    // 2. ERROR SCENARIOS: EMPTY FILES / MISSING PROPERTIES
    static Stream<List<String>> emptyCasesProvider() {
        return Stream.of(
            Arrays.asList("parametro,valor", "", "", "", ""),
            Arrays.asList("parametro,valor", ",", ",", ",", ","),
            Arrays.asList("parametro,valor", "fator_distancia_km,0.05", "fator_peso_kg,2.10", "", "")
        );
    }

    @ParameterizedTest
    @MethodSource("emptyCasesProvider")
    void getCompanyReturnNullWhenCsvIsEmptyOrMissingProperties(List<String> lines) {
        CsvCompanyProvider provider = new CsvCompanyProvider(lines);
        FreightCompany company = provider.getCompany();
        assertNull(company);
    }

    // 3. SUCCESS SCENARIOS: VALID CSV FILES
    static Stream<StreamData> successCasesProvider() {
        return Stream.of(
            new StreamData(
                Arrays.asList("parametro,valor", "fator_distancia_km,0.05", "fator_peso_kg,2.10", "multiplicador_expresso,1.5", "prazo_base_dias,2"),
                0.05, 2.10, 1.5, 2
            ),
            new StreamData(
                Arrays.asList("parametro,valor", "fator_distancia_km,0.01", "fator_peso_kg,1.00", "multiplicador_expresso,1.0", "prazo_base_dias,1"),
                0.01, 1.00, 1.0, 1
            ),
            new StreamData(
                Arrays.asList("parametro,valor", "fator_distancia_km,0.999", "fator_peso_kg,15.75", "multiplicador_expresso,3.5", "prazo_base_dias,10"),
                0.999, 15.75, 3.5, 10
            )
        );
    }

    @ParameterizedTest
    @MethodSource("successCasesProvider")
    void getCompanyReturnObjectWhenCsvIsValid(StreamData data) {
        CsvCompanyProvider provider = new CsvCompanyProvider(data.lines);
        FreightCompany company = provider.getCompany();

        assertNotNull(company);
        assertEquals(data.expectedDistance, company.getDistanceFactor());
        assertEquals(data.expectedWeight, company.getWeightFactor());
        assertEquals(data.expectedExpress, company.getExpressFactor());
        assertEquals(data.expectedDays, company.getBaseDayTime());
    }
    
    // DATA STRUCTURE HELPER (STATIC INNER CLASS)
    static class StreamData {
        List<String> lines;
        double expectedDistance;
        double expectedWeight;
        double expectedExpress;
        int expectedDays;

        StreamData(List<String> lines, double d, double w, double e, int days) {
            this.lines = lines;
            this.expectedDistance = d;
            this.expectedWeight = w;
            this.expectedExpress = e;
            this.expectedDays = days;
        }
    }
}
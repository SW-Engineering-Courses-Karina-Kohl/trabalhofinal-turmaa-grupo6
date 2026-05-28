package br.edu.ufrgs.provider;

import br.edu.ufrgs.model.FreightCompany;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class CsvCompanyProviderSuccessTest {

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
        assertEquals(data.expectedDistance, company.getDistance_factor());
        assertEquals(data.expectedWeight, company.getWeight_factor());
        assertEquals(data.expectedExpress, company.getExpress_factor());
        assertEquals(data.expectedDays, company.getBase_day_time());
    }

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
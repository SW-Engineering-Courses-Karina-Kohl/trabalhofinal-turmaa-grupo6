package br.edu.ufrgs.provider;

import br.edu.ufrgs.model.FreightCompany;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class CsvCompanyProviderEmptyTest {

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
}
package br.edu.ufrgs.provider;

import br.edu.ufrgs.model.FreightCompany;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

class CsvCompanyProviderCorruptedTest {

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
}
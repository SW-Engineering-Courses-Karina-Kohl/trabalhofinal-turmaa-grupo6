package br.edu.ufrgs.provider;

import java.io.StringReader;
import java.util.List;
import java.util.Locale;

import br.edu.ufrgs.model.FreightCompany;
import br.edu.ufrgs.util.CompanyValidator;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;
import tech.tablesaw.io.csv.CsvReadOptions;

public class CsvCompanyProvider implements CompanyProvider {

    private static final int REQUIRED_PARAMETERS = 5;

    private List<String> csvLines;

    public CsvCompanyProvider(List<String> csvLines){
        this.csvLines = csvLines;
    }

    @Override
    public FreightCompany getCompany(){
        try{
            if(csvLines == null || csvLines.isEmpty()) {
                throw new IllegalArgumentException(
                    "Arquivo de configuração vazio."
                );
            }

            CsvReadOptions options = CsvReadOptions.builder(new StringReader(String.join("\n", csvLines)))
                    .locale(Locale.US)
                    .build();
            Table data = Table.read().csv(options);

            if (!data.columnNames().contains("parametro") || !data.columnNames().contains("valor")) {
                throw new IllegalArgumentException(
                    "O arquivo de configuração deve conter as colunas parametro e valor."
                );
            }

            if(data.rowCount() < REQUIRED_PARAMETERS){
                throw new IllegalArgumentException(
                    "Arquivo de configuração incompleto."
                );
            }

            Double distanceFactor = null;
            Double weightFactor = null;
            Double expressFactor = null;
            Integer baseDayTime = null;
            Integer expressDiscountDays = null;

            for(Row row : data){
                String parameter = row.getString("parametro");
                double value = Double.parseDouble(String.valueOf(row.getObject("valor")));

                switch (parameter) {
                    case "fator_distancia_km":
                        distanceFactor = CompanyValidator.validateFactor(parameter, value);
                        break;
                    case "fator_peso_kg":
                        weightFactor = CompanyValidator.validateFactor(parameter, value);
                        break;
                    case "multiplicador_expresso":
                        expressFactor = CompanyValidator.validateFactor(parameter, value);
                        break;
                    case "prazo_base_dias":
                        baseDayTime = (int) CompanyValidator.validateFactor(parameter, value);
                        break;
                    case "desconto_expresso_dias":
                        expressDiscountDays = (int) CompanyValidator.validateFactor(parameter, value);
                        break;
                    default:
                    break;
                }   
            }
            
        CompanyValidator.validatePresence("fator_distancia_km", distanceFactor);
        CompanyValidator.validatePresence("fator_peso_kg", weightFactor);
        CompanyValidator.validatePresence("multiplicador_expresso", expressFactor);
        CompanyValidator.validatePresence("prazo_base_dias", baseDayTime);
        CompanyValidator.validatePresence("desconto_expresso_dias", expressDiscountDays);

            return new FreightCompany(distanceFactor, weightFactor, expressFactor, baseDayTime, expressDiscountDays);

        } catch (IllegalArgumentException e) {

             throw e;

        } catch (Exception e) {

            throw new IllegalArgumentException(
                "Arquivo de configuração inválido: "
                + e.getMessage(),
                e
            );
        }
    }
}
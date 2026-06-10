package br.edu.ufrgs.provider;

import java.io.StringReader;
import java.util.List;
import java.util.Locale;

import br.edu.ufrgs.model.FreightCompany;
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
                    "O arquivo deve conter as colunas parametro e valor."
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

                if (value < 0){
                    throw new IllegalArgumentException(
                        "Valor negativo encontrado no parâmetro: "
                    + parameter
                    );
                }

                switch (parameter) {
                    case "fator_distancia_km":
                        distanceFactor = value;
                        break;
                    case "fator_peso_kg":
                        weightFactor = value;
                        break;
                    case "multiplicador_expresso":
                        expressFactor = value;
                        break;
                    case "prazo_base_dias":
                        baseDayTime = (int) value;
                        break;
                    case "desconto_expresso_dias":
                        expressDiscountDays = (int) value;
                        break;
                    default:
                    break;
                }   
            }
            
            if (distanceFactor == null) {
                throw new IllegalArgumentException(
                    "Parâmetro fator_distancia_km não encontrado."
                );
            }
            if (weightFactor == null) {
                throw new IllegalArgumentException(
                    "Parâmetro fator_peso_kg não encontrado."
                );
            }
            if (expressFactor == null) {
                throw new IllegalArgumentException(
                    "Parâmetro multiplicador_expresso não encontrado."
                );
            }
            if (baseDayTime == null) {
                throw new IllegalArgumentException(
                    "Parâmetro prazo_base_dias não encontrado."
                );
            }
            if (expressDiscountDays == null) {
                throw new IllegalArgumentException(
                    "Parâmetro desconto_expresso_dias não encontrado."
                );
            }

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
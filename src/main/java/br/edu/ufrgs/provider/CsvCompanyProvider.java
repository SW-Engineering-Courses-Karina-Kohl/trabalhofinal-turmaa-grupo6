package br.edu.ufrgs.provider;

import java.io.StringReader;
import java.util.List;
import java.util.Locale;

import br.edu.ufrgs.model.FreightCompany;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;
import tech.tablesaw.io.csv.CsvReadOptions;

public class CsvCompanyProvider implements CompanyProvider {

    private static final int REQUIRED_PARAMETERS_COUNT = 4;
    
    private List<String> csvLines;

    public CsvCompanyProvider(List<String> csvLines){
        this.csvLines = csvLines;
    }

    @Override
    public FreightCompany getCompany(){
        try{
            if(csvLines == null || csvLines.isEmpty()) {
                return null;
            }

            CsvReadOptions options = CsvReadOptions.builder(new StringReader(String.join("\n", csvLines)))
                    .locale(Locale.US)
                    .build();
            Table data = Table.read().csv(options);


            if(data.rowCount() < REQUIRED_PARAMETERS_COUNT){
                return null;
            }

            Double distanceFactor = null;
            Double weightFactor = null;
            Double expressFactor = null;
            Integer baseDayTime = null;

            for(Row row : data){
                String parameter = row.getString("parametro");
                double value = Double.parseDouble(String.valueOf(row.getObject("valor")));

                if (value < 0){
                    return null;
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
                    default:
                        break;
                }
            }

            if(distanceFactor == null || weightFactor == null || expressFactor == null || baseDayTime == null) {
                return null;
            }

            return new FreightCompany(distanceFactor, weightFactor, expressFactor, baseDayTime);

        } catch(Exception e){
            System.err.println("O arquivo de configuração da transportadora não está no formato esperado.");
            return null;
        }
    }
}

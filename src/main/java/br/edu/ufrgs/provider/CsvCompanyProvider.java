package br.edu.ufrgs.provider;

import br.edu.ufrgs.model.FreightCompany;
import tech.tablesaw.api.Table;
import tech.tablesaw.api.Row;
import tech.tablesaw.io.csv.CsvReadOptions;
import java.io.StringReader;
import java.util.List;
import java.util.Locale;

public class CsvCompanyProvider implements CompanyProvider {

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


            if(data.rowCount() < 4){
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

                if(parameter.equals("fator_distancia_km")){
                    distanceFactor = value;
                } else if(parameter.equals("fator_peso_kg")){
                    weightFactor = value;
                } else if(parameter.equals("multiplicador_expresso")){
                    expressFactor = value;
                } else if(parameter.equals("prazo_base_dias")){
                    baseDayTime = (int) value;
                }
            }

            if(distanceFactor == null || weightFactor == null || expressFactor == null || baseDayTime == null) {
                return null;
            }

            return new FreightCompany(distanceFactor, weightFactor, expressFactor, baseDayTime);

        } catch(Exception e){
            System.err.println("Erro crítico: O arquivo de configuração da transportadora está com defeito.");
            return null;
        }
    }
}
package br.edu.ufrgs.exporter;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

import br.edu.ufrgs.model.Freight;

public class CsvFreightExporter implements FreightExporter {

    private final String destinationPath;

    public CsvFreightExporter(String destinationPath) {
        this.destinationPath = destinationPath;
    }

    @Override
    public void exportFreights(List<Freight> freights) {
        if (freights == null || freights.isEmpty()) {
            System.err.println("Nenhum frete encontrado para exportação.");
            return;
        }

        
        try (PrintWriter writer = new PrintWriter(new FileWriter(destinationPath))) {
           
            writer.println("pedido_id,valor_frete,data_estimada,status");

            for (Freight freight : freights) {
                
                String formattedOrderId = String.format("ORD-%03d", freight.getOrderId());
                
                writer.printf(Locale.US, "%s,%.2f,%s,%s%n",
                        formattedOrderId,
                        freight.getFreightValue(),
                        freight.getEstimatedDate().toString(),
                        freight.getPriority().name() 
                );
            }
            
            System.out.println("'logistica_finalizada.csv' gerado com sucesso em: " + destinationPath);

        } catch (Exception e) {
            System.err.println("Erro ao exportar os dados logísticos: " + e.getMessage());
        }
    }
}
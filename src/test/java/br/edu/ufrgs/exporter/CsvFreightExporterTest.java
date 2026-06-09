package br.edu.ufrgs.exporter;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import br.edu.ufrgs.model.Freight;
import br.edu.ufrgs.model.Priority;

public class CsvFreightExporterTest {
    @Test
    void shouldExportCsvFile(@TempDir Path tempDir) throws IOException {
        
        String fileName = "logistica_finalizada.csv";
        
        Path csvFile = tempDir.resolve(fileName);

        FreightExporter exporter =
            new CsvFreightExporter(
                csvFile.toString()
            );

        List<Freight> freights = List.of(
            new Freight(
                1,
                100,
                LocalDate.of(2025, 6, 15),
                Priority.NORMAL
            )
        );

        exporter.exportFreights(freights);

        assertTrue(Files.exists(csvFile));

        List<String> lines = Files.readAllLines(csvFile); // Must have IOException

        assertEquals(
            "pedido_id,valor_frete,data_estimada,status",
            lines.get(0)
        );

        assertEquals(
            "ORD-001,100.00,2025-06-15,NORMAL",
            lines.get(1)
        );
    }

    @Test
    void shouldNotCreateFileWhenFreightListIsEmpty(@TempDir Path tempDir) {

        Path csvFile =
            tempDir.resolve("logistica_finalizada.csv");

        FreightExporter exporter =
            new CsvFreightExporter(csvFile.toString());

        exporter.exportFreights(List.of());

        assertFalse(Files.exists(csvFile));
    }

    @Test
    void shouldNotCreateFileWhenFreightListIsNull(@TempDir Path tempDir) {

        Path csvFile =
            tempDir.resolve("logistica_finalizada.csv");

        FreightExporter exporter =
            new CsvFreightExporter(csvFile.toString());

        exporter.exportFreights(null);

        assertFalse(Files.exists(csvFile));
    }

    @Test
    void shouldHandleInvalidPath() {

        FreightExporter exporter =
            new CsvFreightExporter(
                "invalidPath/abc/file.csv"
            );

        assertDoesNotThrow(() -> 
            exporter.exportFreights(
                List.of(
                    new Freight(
                        1,
                        100,
                        LocalDate.now(),
                        Priority.NORMAL
                    )
                )
            )
        );
    }
}

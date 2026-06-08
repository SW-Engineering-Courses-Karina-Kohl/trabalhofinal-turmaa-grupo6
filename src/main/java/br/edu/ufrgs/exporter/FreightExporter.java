package br.edu.ufrgs.exporter;

import java.util.List;

import br.edu.ufrgs.model.Freight;

public interface FreightExporter {
    
    // 
    void exportFreights(List<Freight> freights);
    
}
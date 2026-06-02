package br.edu.ufrgs.services;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.edu.ufrgs.model.Freight;
import br.edu.ufrgs.model.Priority;

class FreightProcessorTest {
    @Test
    void shouldSortByPriorityThenDateAndKeepStableOrder() {
        List<Freight> freights = new ArrayList<>();

        freights.add(
            new Freight(
            1,
            100,
            LocalDate.of(2025, 6, 15),
            Priority.NORMAL
            )
        );

        freights.add(
            new Freight(
            2,
            100,
            LocalDate.of(2025, 6, 10),
            Priority.URGENT
            )
        );

        freights.add(
            new Freight(
            3,
            100,
            LocalDate.of(2025, 6, 10),
            Priority.URGENT
            )
        );

        freights.add(
            new Freight(
            4,
            100,
            LocalDate.of(2025, 6, 20),
            Priority.LONG_DISTANCE
            )
        );

        FreightProcessor processor = new FreightProcessor(null, null);

        processor.freightSort(freights);

        assertEquals(2, freights.get(0).getOrderId());
        assertEquals(3, freights.get(1).getOrderId());
        assertEquals(1, freights.get(2).getOrderId());
        assertEquals(4, freights.get(3).getOrderId());
    }

    @Test
    void shouldHandleEmptyList() {
        List<Freight> freights = new ArrayList<>();

        FreightProcessor processor =
            new FreightProcessor(null, null);

        List<Freight> result =
            processor.freightSort(freights);

        assertTrue(result.isEmpty());
    }
}
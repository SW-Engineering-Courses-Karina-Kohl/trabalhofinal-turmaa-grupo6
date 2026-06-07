package br.edu.ufrgs.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class FreightCompanyTest {
    
    @Test
    void shouldAssignHighPriority() {
        FreightCompany company =
            new FreightCompany(1.0, 1.0, 1.0, 1);

        Order order =
            new Order(
                1,
                "Cliente",
                100.0,
                10.0,
                "NORMAL",
                LocalDate.of(2025, 6, 1)
            );

        Freight freight = company.calculateFreight(order);

        // 1 + (100 / 200) = 1,5 -> URGENT ( <= 2 Days )
        assertEquals(Priority.URGENT, freight.getPriority());
    }

    @Test
    void shouldAssignNormalPriority() {
        FreightCompany company =
            new FreightCompany(1.0, 1.0, 1.0, 1);

        Order order =
            new Order(
                1,
                "Cliente",
                300.0,
                10.0,
                "NORMAL",
                LocalDate.of(2025, 6, 1)
            );

        Freight freight = company.calculateFreight(order);

        // 1 + (300 / 200) = 2.5 -> NORMAL ( > 2 Days <= 7 Days )
        assertEquals(Priority.NORMAL, freight.getPriority());
    }

    @Test
    void shouldAssignLongDistancePriority() {
        FreightCompany company =
            new FreightCompany(1.0, 1.0, 1.0, 1);

        Order order =
            new Order(
                1,
                "Cliente",
                1500.0,
                10.0,
                "NORMAL",
                LocalDate.of(2025, 6, 1)
            );

        Freight freight = company.calculateFreight(order);

        // 1 + (1500 / 200) = 8.5 -> LONG DISTANCE ( >= 7 Days )
        assertEquals(Priority.LONG_DISTANCE, freight.getPriority());
    }
}
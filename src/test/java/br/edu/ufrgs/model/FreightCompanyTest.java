package br.edu.ufrgs.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class FreightCompanyTest {
    
    @Test
    void shouldAssignHighPriority() {
        FreightCompany company =
            new FreightCompany(1.0, 1.0, 1.0, 1, 0);

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
            new FreightCompany(1.0, 1.0, 1.0, 1, 0);

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
            new FreightCompany(1.0, 1.0, 1.0, 1, 0);

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

    @Test
    void shouldAssignUrgentPriorityAtBoundary() {
        FreightCompany company =
            new FreightCompany(1.0, 1.0, 1.0, 1, 0);

        Order order =
            new Order(
                1,
                "Cliente",
                200.0,
                10.0,
                "NORMAL",
                LocalDate.of(2025, 6, 1)
            );

        Freight freight = company.calculateFreight(order);

        // 1 + (200 / 200) = 2.0 -> URGENT ( <= 2 Days )
        assertEquals(Priority.URGENT, freight.getPriority());
    }

    @Test
    void shouldAssignNormalPriorityAtBoundary() {
        FreightCompany company =
            new FreightCompany(1.0, 1.0, 1.0, 1, 0);

        Order order =
            new Order(
                1,
                "Cliente",
                1200.0,
                10.0,
                "NORMAL",
                LocalDate.of(2025, 6, 1)
            );

        Freight freight = company.calculateFreight(order);

        // 1 + (1200 / 200) = 7.0 -> NORMAL ( <= 7 Days )
        assertEquals(Priority.NORMAL, freight.getPriority());
    }

    @Test
    void shouldAssignLongDistancePriorityAboveBoundary() {
        FreightCompany company =
            new FreightCompany(1.0, 1.0, 1.0, 1, 0);

        Order order =
            new Order(
                1,
                "Cliente",
                1400.0,
                10.0,
                "NORMAL",
                LocalDate.of(2025, 6, 1)
            );

        Freight freight = company.calculateFreight(order);

        // 1 + (1400 / 200) = 8.0 -> LONG_DISTANCE ( > 7 Days )
        assertEquals(Priority.LONG_DISTANCE, freight.getPriority());
    }

    @Test
    void shouldCalculateDeliveryDate() {

        FreightCompany company =
            new FreightCompany(1.0, 1.0, 1.0, 1, 0);

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

        // 1 + (300 / 200) = 2.5 -> 3 Days -> Delivery Date: 2025-06-04
        assertEquals(LocalDate.of(2025, 6, 4),freight.getEstimatedDate());
    }

    @Test
    void shouldCalculateExpressPrice() {

        FreightCompany company =
            new FreightCompany(2.0, 3.0, 1.5, 1, 0);

        Order order =
            new Order(
                1,
                "Cliente",
                100.0,
                10.0,
                "EXPRESSO",
                LocalDate.now()
            );

        Freight freight = company.calculateFreight(order);

        // (100 * 2.0) + (10 * 3.0) -> 230 * 1.5 > 345.0
        assertEquals(345.0, freight.getFreightValue(), 0.001);
    }
    @Test
    void shouldCalculateNormalPrice() {

        FreightCompany company =
            new FreightCompany(2.0, 3.0, 1.5, 1, 0);

        Order order =
            new Order(
                1,
                "Cliente",
                100.0,
                10.0,
                "NORMAL",
                LocalDate.now()
            );

        Freight freight = company.calculateFreight(order);

        // (100 * 2.0) + (10 * 3.0) -> 230
        assertEquals(230.0, freight.getFreightValue(), 0.001);
    }

    @Test
    void shouldApplyExpressFactor() {
        FreightCompany company =
            new FreightCompany(0.05, 2.10, 1.5, 2, 0);

        Order order =
            new Order(
                2,
                "Cliente",
                120.0,
                0.8,
                "EXPRESSO",
                LocalDate.of(2026, 3, 23)
            );

        Freight freight = company.calculateFreight(order);

        // (120.0 * 0.05) + (0.8 * 2.10) = 6.00 + 1.68 = 7.68 * 1.5 = 11.52
        assertEquals(11.52, freight.getFreightValue());
    }

    @Test
    void shouldApplyExpressDiscountDays() {
        FreightCompany company =
            new FreightCompany(1.0, 1.0, 1.0, 3, 2);

        Order order =
            new Order(
                1,
                "Cliente",
                200.0,
                10.0,
                "EXPRESSO",
                LocalDate.of(2025, 6, 1)
            );

        Freight freight = company.calculateFreight(order);

        // 3 + (200 / 200) = 4.0 -> 4 - 2 = 2 -> Delivery Date: 2025-06-03
        assertEquals(LocalDate.of(2025, 6, 3), freight.getEstimatedDate());
    }

    @Test
    void shouldRespectMinDeliveryDays() {
        FreightCompany company =
            new FreightCompany(1.0, 1.0, 1.0, 1, 10);

        Order order =
            new Order(
                1,
                "Cliente",
                100.0,
                10.0,
                "EXPRESSO",
                LocalDate.of(2025, 6, 1)
            );

        Freight freight = company.calculateFreight(order);

        // 1 + (100 / 200) = 1.5 -> ceil = 2 -> 2 - 10 = -8 -> MIN = 1 -> Delivery Date: 2025-06-02
        assertEquals(LocalDate.of(2025, 6, 2), freight.getEstimatedDate());
    }

    @Test
    void shouldNotApplyExpressDiscountDays() {
        FreightCompany company =
            new FreightCompany(1.0, 1.0, 1.0, 1, 5);

        Order orderNormal =
            new Order(
                1,
                "Cliente",
                600.0,
                10.0,
                "NORMAL",
                LocalDate.of(2025, 6, 1)
            );

        Order orderExpresso =
            new Order(
                2,
                "Cliente",
                600.0,
                10.0,
                "EXPRESSO",
                LocalDate.of(2025, 6, 1)
            );

        Freight freightNormal = company.calculateFreight(orderNormal);
        Freight freightExpresso = company.calculateFreight(orderExpresso);

        // 1 + (600 / 200) = 4.0 -> NORMAL sem desconto = 4 dias
        assertEquals(LocalDate.of(2025, 6, 5), freightNormal.getEstimatedDate());

        // 1 + (600 / 200) = 4.0 -> EXPRESSO com desconto 5 -> MAX(1, -1) = 1 dia
        assertEquals(LocalDate.of(2025, 6, 2), freightExpresso.getEstimatedDate());
    }
}
package br.edu.ufrgs.services;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.edu.ufrgs.model.Freight;
import br.edu.ufrgs.model.Priority;
import br.edu.ufrgs.model.FreightCompany;
import br.edu.ufrgs.model.Order;
import br.edu.ufrgs.model.ServiceType;
import br.edu.ufrgs.provider.CompanyProvider;
import br.edu.ufrgs.provider.OrderProvider;

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

    @Test
    void shouldGenerateFreightsForAllOrders() {

        FreightCompany company =
            new FreightCompany(
                0.05,
                2.10,
                1.5,
                2,
                0
            );

        List<Order> orders = List.of(
            new Order(
                1,
                "Cliente A",
                100.0,
                10.0,
                ServiceType.NORMAL,
                LocalDate.of(2025, 6, 1)
            ),
            new Order(
                2,
                "Cliente B",
                200.0,
                5.0,
                ServiceType.EXPRESSO,
                LocalDate.of(2025, 6, 1)
            )
        );

        CompanyProvider companyProvider =
            () -> company;

        OrderProvider orderProvider =
            () -> orders;

        FreightProcessor processor =
            new FreightProcessor(
                orderProvider,
                companyProvider
            );

        List<Freight> freights =
            processor.freightProcess();

        assertEquals(2, freights.size());
    }

    @Test
    void shouldReturnEmptyListWhenThereAreNoOrders() {

        FreightCompany company =
            new FreightCompany(
                0.05,
                2.10,
                1.5,
                2,
                0
            );

        CompanyProvider companyProvider =
            () -> company;

        OrderProvider orderProvider =
            List::of;

        FreightProcessor processor =
            new FreightProcessor(
                orderProvider,
                companyProvider
            );

        List<Freight> freights =
            processor.freightProcess();

        assertTrue(freights.isEmpty());
    }

    @Test
    void shouldReturnFreightsSortedByPriority() {

        FreightCompany company =
            new FreightCompany(
                1.0,
                1.0,
                1.0,
                1,
                0
            );

        List<Order> orders = List.of(
            new Order(
                1,
                "Cliente",
                1500.0,
                10.0,
                ServiceType.NORMAL,
                LocalDate.of(2025, 6, 1)
            ),
            new Order(
                2,
                "Cliente",
                100.0,
                10.0,
                ServiceType.NORMAL,
                LocalDate.of(2025, 6, 1)
            )
        );

        CompanyProvider companyProvider =
            () -> company;

        OrderProvider orderProvider =
            () -> orders;

        FreightProcessor processor =
            new FreightProcessor(
                orderProvider,
                companyProvider
            );

        List<Freight> freights =
            processor.freightProcess();

        assertEquals(
            Priority.URGENT,
            freights.get(0).getPriority()
        );

        assertEquals(
            Priority.LONG_DISTANCE,
            freights.get(1).getPriority()
        );
    }
}
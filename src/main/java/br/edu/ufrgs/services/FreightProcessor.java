package br.edu.ufrgs.services;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import br.edu.ufrgs.model.Freight;
import br.edu.ufrgs.model.Order;
import br.edu.ufrgs.model.FreightCompany;
import br.edu.ufrgs.provider.CompanyProvider;
import br.edu.ufrgs.provider.OrderProvider;

public class FreightProcessor {
    private OrderProvider orderProvider;
    private CompanyProvider companyProvider;

    public FreightProcessor(OrderProvider orderProvider, CompanyProvider companyProvider) {
        this.orderProvider = orderProvider;
        this.companyProvider = companyProvider;
    }

    public List<Freight> freightProcess() {

        FreightCompany company =
                companyProvider.getCompany();

        List<Order> orders =
                orderProvider.getOrders();

        List<Freight> freights =
                new ArrayList<>();

        for (Order order : orders) {

            Freight freight =
                    company.calculateFreight(order);

            freights.add(freight);
        }

        return freightSort(freights);
    }

    public List<Freight> freightSort(List<Freight> freights) {
        Objects.requireNonNull(freights, "Freight list cannot be null");

        /* Sorting system comparison ordering:
            1. Priority
            2. Estimated Date
            3. Order ID
        */
        freights.sort(
            Comparator.comparingInt((Freight freight) -> {
                switch(freight.getPriority()){
                    case URGENT:   return 0;
                    case NORMAL: return 1;
                    case LONG_DISTANCE:    return 2;
                    default:     return 2; 
                }
            })
            .thenComparing(Freight::getEstimatedDate)
            .thenComparing(Freight::getOrderId)
        );

        return freights;
    }
}
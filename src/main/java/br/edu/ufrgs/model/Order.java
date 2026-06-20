package br.edu.ufrgs.model;

import java.time.LocalDate;

public class Order {
    private int orderId;
    private String client;
    private Double distance;
    private Double weight;
    private ServiceType serviceType;
    private LocalDate serviceDate;

    public Order(int orderId, String client, Double distance, Double weight, ServiceType serviceType, LocalDate serviceDate) {
        this.orderId = orderId;
        this.client = client;
        this.distance = distance;
        this.weight = weight;
        this.serviceType = serviceType;
        this.serviceDate = serviceDate;
    }

    public int getOrderId() {
        return orderId;
    }
    public String getClient() {
        return client;
    }
    public Double getDistance() {
        return distance;
    }
    public Double getWeight() {
        return weight;
    }
    public ServiceType getServiceType() {
        return serviceType;
    }
    public LocalDate getServiceDate() {
        return serviceDate;
    }

}
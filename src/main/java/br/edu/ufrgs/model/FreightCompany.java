package br.edu.ufrgs.model;

import java.time.LocalDate;

public class FreightCompany {
    private final int DAILY_DISTANCE_CAPACITY = 200;
    private Double distanceFactor;
    private Double weightFactor;
    private Double expressFactor;
    private Integer baseDayTime;

    public FreightCompany(Double distanceFactor, Double weightFactor, Double expressFactor, int baseDayTime) {
        this.distanceFactor = distanceFactor;
        this.weightFactor = weightFactor;
        this.expressFactor = expressFactor;
        this.baseDayTime = baseDayTime;
    }

    public Freight calculateFreight(Order order) {
        int deliveryTime = calculateDeliveryTime(order);

        return new Freight(
            order.getOrderId(),
            calculatePrice(order),
            calculateDeliveryDate(order, deliveryTime),
            calculatePriority(deliveryTime)
        );
    }
    
    private Double calculatePrice(Order order) {
        return 0.0;
    }

    private int calculateDeliveryTime(Order order) {
        Double deliveryTime = baseDayTime + order.getDistance()/DAILY_DISTANCE_CAPACITY;
        return (int) Math.ceil(deliveryTime);
    }

    private LocalDate calculateDeliveryDate(Order order, int deliveryTime) {
        return order.getServiceDate().plusDays(deliveryTime);
    }

    private Priority calculatePriority(int deliveryTime) {
        if(deliveryTime <= 2) {
            return Priority.URGENT;
        }

        if(deliveryTime <= 7) {
            return Priority.NORMAL;
        }

        return Priority.LONG_DISTANCE;
    }
    
    public Double getDistanceFactor() {
        return distanceFactor;
    }

    public Double getWeightFactor() {
        return weightFactor;
    }

    public Double getExpressFactor() {
        return expressFactor;
    }

    public Integer getBaseDayTime() {
        return baseDayTime;
    }
}

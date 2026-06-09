package br.edu.ufrgs.model;

import java.time.LocalDate;

public class FreightCompany {
    private static final int DAILY_DISTANCE_CAPACITY = 200;
    private static final int MIN_DELIVERY_DAYS = 1;
    private Double distanceFactor;
    private Double weightFactor;
    private Double expressFactor;
    private Integer baseDayTime;
    private Integer expressDiscountDays;

    public FreightCompany(Double distanceFactor, Double weightFactor, Double expressFactor, int baseDayTime, int expressDiscountDays) {
        this.distanceFactor = distanceFactor;
        this.weightFactor = weightFactor;
        this.expressFactor = expressFactor;
        this.baseDayTime = baseDayTime;
        this.expressDiscountDays = expressDiscountDays;
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
        Double basePrice = (order.getDistance() * distanceFactor)
                        + (order.getWeight() * weightFactor);

        if(order.getServiceType().equals("EXPRESSO")) {
            return basePrice * expressFactor;
        }

        return basePrice;
    }

    private int calculateDeliveryTime(Order order) {
        Double deliveryTime = baseDayTime + order.getDistance()/DAILY_DISTANCE_CAPACITY;

        if(order.getServiceType().equals("EXPRESSO")){
            return (int) Math.ceil(Math.max(MIN_DELIVERY_DAYS, (deliveryTime - expressDiscountDays)));
        }
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

    public Integer getExpressDiscountDays(){
        return expressDiscountDays;
    }
}

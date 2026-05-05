package br.edu.ufrgs.model;
import java.time.LocalDate;

public class Freight {
    private int orderId;
    private double freightValue;
    private LocalDate estimatedDate;
    private Priority priority;

    public Freight(int orderId, double freightValue, LocalDate estimatedDate, Priority priority){
        this.orderId = orderId;
        this.freightValue = freightValue;
        this.estimatedDate = estimatedDate;
        this.priority = priority;
    }

    public int getOrderId(){
        return orderId;
    };

    public double getFreightValue(){
        return freightValue;
    };

    public LocalDate getEstimatedDate(){
        return estimatedDate;
    };

    public Priority getPriority(){
        return priority;
    };
}

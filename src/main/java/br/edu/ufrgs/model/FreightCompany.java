package br.edu.ufrgs.model;

public class FreightCompany {
    private Double distance_factor;
    private Double weight_factor;
    private Double express_factor;
    private Integer base_day_time;

    public FreightCompany(Double distance_factor, Double weight_factor, Double express_factor, Integer base_day_time) {
        this.distance_factor = distance_factor;
        this.weight_factor = weight_factor;
        this.express_factor = express_factor;
        this.base_day_time = base_day_time;
    }

    public Double getDistance_factor() {
        return distance_factor;
    }

    public Double getWeight_factor() {
        return weight_factor;
    }

    public Double getExpress_factor() {
        return express_factor;
    }

    public Integer getBase_day_time() {
        return base_day_time;
    }
}

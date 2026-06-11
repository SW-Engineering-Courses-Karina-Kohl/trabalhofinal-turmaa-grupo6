package br.edu.ufrgs.util;

public class WeightValidator {
    
    public static double validate(String rawWeight){

        if (rawWeight == null) {
            throw new IllegalArgumentException(
                "Peso não informado."
            );
        }

        double weight;

        try {
            weight = Double.parseDouble(rawWeight);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                "Peso deve ser um número válido."
            );
        }

        if (Double.isNaN(weight)
                || Double.isInfinite(weight)
                || weight <= 0) {

            throw new IllegalArgumentException(
                "Peso deve ser maior que zero."
            );
        }

        return weight;
    }

}

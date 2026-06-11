package br.edu.ufrgs.util;

public class DistanceValidator {

    public static double validate(String rawDistance){

        if (rawDistance == null) {
            throw new IllegalArgumentException(
                "Distância não informada."
            );
        }

        double distance;

        try {
            distance = Double.parseDouble(rawDistance);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                "Distância deve ser um número válido."
            );
        }

        if (Double.isNaN(distance)
                || Double.isInfinite(distance)
                || distance <= 0) {

            throw new IllegalArgumentException(
                "Distância deve ser maior que zero."
            );
        }

        return distance;
    }
}

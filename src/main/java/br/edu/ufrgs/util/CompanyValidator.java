package br.edu.ufrgs.util;

public class CompanyValidator {
    
    public static double validateFactor(String parameter, double value){

        if (value < 0) {
            throw new IllegalArgumentException(
                "Parâmetro " + parameter + " não pode ser negativo."
            );
        }

    return value;

    }

    public static void validatePresence(String parameter, Object value){

        if (value == null) {
            throw new IllegalArgumentException(
                "Parâmetro " + parameter + " não encontrado."
            );
        }
    }

}


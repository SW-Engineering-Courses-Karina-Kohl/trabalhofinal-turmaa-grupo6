package br.edu.ufrgs.model;

public enum ServiceType {
    NORMAL, EXPRESSO;

    public static ServiceType normalize(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Tipo de serviço não informado.");
        }

        String normalized = value.strip().toUpperCase();

        switch (normalized) {
            case "NORMAL":
                return NORMAL;
            case "EXPRESSO":
            case "EXPRESS":
            case "SEDEX":
                return EXPRESSO;
            default:
                throw new IllegalArgumentException("Tipo de serviço inválido: " + value);
        }
    }
}
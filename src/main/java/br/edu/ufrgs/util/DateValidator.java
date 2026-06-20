package br.edu.ufrgs.util;

import java.time.LocalDate;

public class DateValidator {

    public static LocalDate validate(LocalDate date){

        if (date == null) {
            throw new IllegalArgumentException(
                "Data do pedido não informada."
            );
        }

        return date;
    }
}

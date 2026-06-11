package br.edu.ufrgs.util;

import java.util.Set;

public class OrderIdValidator {

    private static final String ORDER_PREFIX = "ORD-";

    public static int validate(
        String rawOrderId,
        Set<Integer> usedIds) {

        if (rawOrderId == null) {
            throw new IllegalArgumentException(
                "ID do pedido não informado."
            );
        }

        rawOrderId = rawOrderId.trim();

        if (rawOrderId.isEmpty()) {
            throw new IllegalArgumentException(
                "ID do pedido vazio."
            );
        }

        if (!rawOrderId.startsWith(ORDER_PREFIX)) {
            throw new IllegalArgumentException(
                "ID do pedido deve começar com ORD-."
            );
        }

        String numericPart = rawOrderId.substring(ORDER_PREFIX.length());

        if (!numericPart.matches("\\d+")) {
            throw new IllegalArgumentException(
                "Parte numérica do ID do pedido inválida."
            );        
        }

        int orderId = Integer.parseInt(numericPart);

        if (orderId <= 0) {
            throw new IllegalArgumentException(
                "ID do pedido deve ser maior que zero."
            );
        }

        if (!usedIds.add(orderId)) {
            throw new IllegalArgumentException(
                "Pedido duplicado: ORD-" + orderId
            );
        }

        return orderId;
    }
}

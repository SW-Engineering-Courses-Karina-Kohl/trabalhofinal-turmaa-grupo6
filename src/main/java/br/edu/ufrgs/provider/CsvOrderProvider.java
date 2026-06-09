package br.edu.ufrgs.provider;

import br.edu.ufrgs.model.Order;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;
import tech.tablesaw.io.csv.CsvReadOptions;
import java.io.StringReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class CsvOrderProvider implements OrderProvider {

    private static final int REQUIRED_PARAMETERS = 6;
    private static final String ORDER_PREFIX = "ORD-";

    private List<String> csvLines;

    public CsvOrderProvider(List<String> csvLines) {
        this.csvLines = csvLines;
    }

    @Override
    public List<Order> getOrders() {

        if (csvLines == null || csvLines.isEmpty()) {
            return null;
        }

        CsvReadOptions options = CsvReadOptions.builder(
                new StringReader(String.join("\n", csvLines)))
                .locale(Locale.US)
                .build();

        Table data = Table.read().csv(options);

        if (data.rowCount() == 0) {
            return null;
        }

        validateColumns(data);

        List<Order> orders = new ArrayList<>();
        Set<Integer> usedIds = new HashSet<>();

        for (Row row : data) {

            try {

                int orderId = parseOrderId(
                        row.getString("pedido_id"),
                        usedIds
                );

                String client = parseClient(
                        row.getString("cliente")
                );

                double distance = parseDistance(
                        String.valueOf(
                                row.getObject("distancia_km")
                        )
                );

                double weight = parseWeight(
                        String.valueOf(
                                row.getObject("peso_kg")
                        )
                );

                String serviceType = parseService(
                        row.getString("tipo_servico")
                );

                LocalDate orderDate = parseDate(
                        row.getDate("data_pedido")
                );

                orders.add(
                        new Order(
                                orderId,
                                client,
                                distance,
                                weight,
                                serviceType,
                                orderDate
                        )
                );

            } catch (Exception e) {

                throw new IllegalArgumentException(
                    "Arquivo de pedidos inválido. "
                    + "Linha "
                    + row.getRowNumber()
                    + ": "
                    + e.getMessage(),
                    e
                );
            }
        }

        return orders;
    }

    private void validateColumns(Table data) {

        if (data.columnCount() != REQUIRED_PARAMETERS) {
            throw new IllegalArgumentException(
                "Quantidade de colunas dos pedidos inválida."
            );
        }

        List<String> columns = data.columnNames();

        if (!columns.contains("pedido_id")
                || !columns.contains("cliente")
                || !columns.contains("distancia_km")
                || !columns.contains("peso_kg")
                || !columns.contains("tipo_servico")
                || !columns.contains("data_pedido")) {

            throw new IllegalArgumentException(
                "Colunas obrigatórias não encontradas."
            );
        }
    }

    private int parseOrderId(
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

    private String parseClient(String client) {

        if (client == null) {
            throw new IllegalArgumentException(
                "Cliente não informado."
            );
        }

        client = client.trim();

        if (client.isEmpty()) {
            throw new IllegalArgumentException(
                "Nome do cliente vazio."
            );
        }

        return client;
    }

    private double parseDistance(String rawDistance) {

        if (rawDistance == null) {
            throw new IllegalArgumentException(
                "Distância não informada."
            );
        }

        double distance = Double.parseDouble(rawDistance);

        if (Double.isNaN(distance)
                || Double.isInfinite(distance)
                || distance <= 0) {

            throw new IllegalArgumentException(
                "Distância deve ser maior que zero."
            );
        }

        try {
            distance = Double.parseDouble(rawDistance);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                "Distância inválida."
            );
        }

        return distance;
    }

    private double parseWeight(String rawWeight) {

        if (rawWeight == null) {
            throw new IllegalArgumentException(
                "Peso não informado."
            );
        }

        double weight = Double.parseDouble(rawWeight);

        if (Double.isNaN(weight)
                || Double.isInfinite(weight)
                || weight <= 0) {

            throw new IllegalArgumentException(
                "Peso deve ser maior que zero."
            );
        }

        return weight;
    }

    private String parseService(String serviceType) {

        if (serviceType == null) {
            throw new IllegalArgumentException(
                "Tipo de serviço não informado."
            );
        }

        serviceType = serviceType.trim();

        if (serviceType.isEmpty()) {
            throw new IllegalArgumentException(
                "Tipo de serviço vazio."
            );
        }

        String normalized = serviceType.toUpperCase();

        switch (normalized) {

            case "NORMAL":
                return "NORMAL";

            case "EXPRESSO":
                return "EXPRESSO";

            case "SEDEX":
                return "EXPRESSO";

            default:
                throw new IllegalArgumentException(
                    "Tipo de serviço inválido: " + serviceType
                );
        }
    }

    private LocalDate parseDate(LocalDate date) {

        if (date == null) {
            throw new IllegalArgumentException(
                "Data do pedido não informada."
            );
        }

        return date;
    }
}
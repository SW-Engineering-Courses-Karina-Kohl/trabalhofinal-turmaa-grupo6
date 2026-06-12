package br.edu.ufrgs.provider;

import br.edu.ufrgs.model.Order;
import br.edu.ufrgs.model.ServiceType;
import br.edu.ufrgs.util.ClientValidator;
import br.edu.ufrgs.util.DateValidator;
import br.edu.ufrgs.util.DistanceValidator;
import br.edu.ufrgs.util.OrderIdValidator;
import br.edu.ufrgs.util.WeightValidator;
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

                int orderId = OrderIdValidator.validate(
                        row.getString("pedido_id"),
                        usedIds
                );

                String client = ClientValidator.validate(
                        row.getString("cliente")
                );

                double distance = DistanceValidator.validate(
                        String.valueOf(
                                row.getObject("distancia_km")
                        )
                );

                double weight = WeightValidator.validate(
                        String.valueOf(
                                row.getObject("peso_kg")
                        )
                );

                ServiceType serviceType = ServiceType.normalize(
                    row.getString("tipo_servico")
                );

                LocalDate orderDate;
                
                try {
                    orderDate = DateValidator.validate(row.getDate("data_pedido"));

                } catch (Exception e) {

                    throw new IllegalArgumentException("Data do pedido deve ser uma data válida.");
                }

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
                "Colunas obrigatórias não encontradas em pedidos."
            );
        }
    }
}
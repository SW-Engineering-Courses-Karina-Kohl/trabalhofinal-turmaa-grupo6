package br.edu.ufrgs.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.edu.ufrgs.model.Order;

class CsvOrderProviderTest {

        @Test
        void shouldParseValidCsv() {

                List<String> csv = List.of(
                        "pedido_id,cliente,distancia_km,peso_kg,tipo_servico,data_pedido",
                        "ORD-1,Loja Tech,450.0,2.5,NORMAL,2026-03-23"
                );

                CsvOrderProvider provider =
                        new CsvOrderProvider(csv);

                List<Order> orders =
                        provider.getOrders();

                assertNotNull(orders);
                assertEquals(1, orders.size());

                Order order = orders.get(0);

                assertEquals(1, order.getOrderId());
                assertEquals("Loja Tech", order.getClient());
                assertEquals(450.0, order.getDistance());
                assertEquals(2.5, order.getWeight());
                assertEquals("NORMAL", order.getServiceType());
        }

        @Test
        void shouldTrimClientName() {

                List<String> csv = List.of(
                        "pedido_id,cliente,distancia_km,peso_kg,tipo_servico,data_pedido",
                        "ORD-1,   Jorge Silva   ,450.0,2.5,NORMAL,2026-03-23"
                );

                CsvOrderProvider provider =
                        new CsvOrderProvider(csv);

                List<Order> orders =
                        provider.getOrders();

                assertNotNull(orders);

                assertEquals("Jorge Silva", orders.get(0).getClient());
        }

        @Test
        void shouldConvertSedexToExpresso() {

                List<String> csv = List.of(
                        "pedido_id,cliente,distancia_km,peso_kg,tipo_servico,data_pedido",
                        "ORD-1,Loja,450.0,2.5,SEDEX,2026-03-23"
                );

                CsvOrderProvider provider = new CsvOrderProvider(csv);

                List<Order> orders = provider.getOrders();

                assertNotNull(orders);

                assertEquals("EXPRESSO",orders.get(0).getServiceType());
        }

        @Test
        void shouldAcceptLowerCaseServiceType() {

                List<String> csv = List.of(
                        "pedido_id,cliente,distancia_km,peso_kg,tipo_servico,data_pedido",
                        "ORD-1,Loja,450.0,2.5,normal,2026-03-23"
                );

                CsvOrderProvider provider = new CsvOrderProvider(csv);

                List<Order> orders = provider.getOrders();

                assertNotNull(orders);

                assertEquals("NORMAL",orders.get(0).getServiceType());
        }

        @Test
        void shouldRejectDuplicateOrderIds() {

                List<String> csv = List.of(
                        "pedido_id,cliente,distancia_km,peso_kg,tipo_servico,data_pedido",
                        "ORD-1,Loja A,450.0,2.5,NORMAL,2026-03-23",
                        "ORD-1,Loja B,300.0,1.0,EXPRESSO,2026-03-24"
                );

                CsvOrderProvider provider = new CsvOrderProvider(csv);

                assertNull(provider.getOrders());
        }

        @Test
        void shouldRejectInvalidPrefix() {

                List<String> csv = List.of(
                        "pedido_id,cliente,distancia_km,peso_kg,tipo_servico,data_pedido",
                        "PED-1,Loja,450.0,2.5,NORMAL,2026-03-23"
                );

                CsvOrderProvider provider = new CsvOrderProvider(csv);

                assertNull(provider.getOrders());
        }

        @Test
        void shouldRejectOrderIdZero() {

                List<String> csv = List.of(
                        "pedido_id,cliente,distancia_km,peso_kg,tipo_servico,data_pedido",
                        "ORD-0,Loja,450.0,2.5,NORMAL,2026-03-23"
                );

                CsvOrderProvider provider = new CsvOrderProvider(csv);

                assertNull(provider.getOrders());
        }

        @Test
        void shouldRejectNegativeDistance() {

                List<String> csv = List.of(
                        "pedido_id,cliente,distancia_km,peso_kg,tipo_servico,data_pedido",
                        "ORD-1,Loja,-10.0,2.5,NORMAL,2026-03-23"
                );

                CsvOrderProvider provider = new CsvOrderProvider(csv);

                assertNull(provider.getOrders());
        }

        @Test
        void shouldRejectNegativeWeight() {

                List<String> csv = List.of(
                        "pedido_id,cliente,distancia_km,peso_kg,tipo_servico,data_pedido",
                        "ORD-1,Loja,10.0,-2.5,NORMAL,2026-03-23"
                );

                CsvOrderProvider provider = new CsvOrderProvider(csv);

                assertNull(provider.getOrders());
        }

        @Test
        void shouldRejectInvalidServiceType() {

                List<String> csv = List.of(
                        "pedido_id,cliente,distancia_km,peso_kg,tipo_servico,data_pedido",
                        "ORD-1,Loja,10.0,2.5,PRIORITARIO,2026-03-23"
                );

                CsvOrderProvider provider = new CsvOrderProvider(csv);

                assertNull(provider.getOrders());
        }

        @Test
        void shouldRejectMissingColumn() {

                List<String> csv = List.of(
                        "pedido_id,cliente,distancia_km,peso_kg,data_pedido",
                        "ORD-1,Loja,10.0,2.5,2026-03-23"
                );

                CsvOrderProvider provider = new CsvOrderProvider(csv);

                assertNull(provider.getOrders());
        }

        @Test
        void shouldRejectEmptyClient() {

                List<String> csv = List.of(
                        "pedido_id,cliente,distancia_km,peso_kg,tipo_servico,data_pedido",
                        "ORD-1,   ,10.0,2.5,NORMAL,2026-03-23"
                );

                CsvOrderProvider provider = new CsvOrderProvider(csv);

                assertNull(provider.getOrders());
        }

        @Test
        void shouldConvertLowerCaseSedexToExpresso() {

                List<String> csv = List.of(
                        "pedido_id,cliente,distancia_km,peso_kg,tipo_servico,data_pedido",
                        "ORD-1,Loja,450.0,2.5,sedex,2026-03-23"
                );

                CsvOrderProvider provider = new CsvOrderProvider(csv);

                List<Order> orders = provider.getOrders();

                assertNotNull(orders);

                assertEquals("EXPRESSO",orders.get(0).getServiceType());
        }

        @Test
        void shouldParseMultipleOrders() {

                List<String> csv = List.of(
                "pedido_id,cliente,distancia_km,peso_kg,tipo_servico,data_pedido",
                "ORD-1,Loja Tech,450.0,2.5,NORMAL,2026-03-23",
                "ORD-2,Maria Silva,120.0,0.8,EXPRESSO,2026-03-23",
                "ORD-3,Construtora XYZ,1200.0,50.0,NORMAL,2026-03-22"
                );

                CsvOrderProvider provider = new CsvOrderProvider(csv);

                List<Order> orders = provider.getOrders();

                assertNotNull(orders);
                assertEquals(3, orders.size());

                assertEquals(1, orders.get(0).getOrderId());
                assertEquals(2, orders.get(1).getOrderId());
                assertEquals(3, orders.get(2).getOrderId());
        }

        @Test
        void shouldRejectExtraColumns() {

                List<String> csv = List.of(
                        "pedido_id,cliente,distancia_km,peso_kg,tipo_servico,data_pedido,coluna_extra",
                        "ORD-1,Loja,10.0,2.5,NORMAL,2026-03-23,valor"
                );

                CsvOrderProvider provider = new CsvOrderProvider(csv);

                assertNull(provider.getOrders());
        }

        @Test
        void shouldRejectEmptyCsv() {

                CsvOrderProvider provider = new CsvOrderProvider(List.of());

                assertNull(provider.getOrders());
        }

        @Test
        void shouldRejectNullCsv() {

                CsvOrderProvider provider = new CsvOrderProvider(null);

                assertNull(provider.getOrders());
        }

        @Test
        void shouldRejectNegativeOrderId() {

                List<String> csv = List.of(
                        "pedido_id,cliente,distancia_km,peso_kg,tipo_servico,data_pedido",
                        "ORD--1,Loja,10.0,2.5,NORMAL,2026-03-23"
                );

                CsvOrderProvider provider = new CsvOrderProvider(csv);

                assertNull(provider.getOrders());
        }

        @Test
        void shouldRejectMalformedDate() {

                List<String> csv = List.of(
                        "pedido_id,cliente,distancia_km,peso_kg,tipo_servico,data_pedido",
                        "ORD-1,Cliente,10,5,NORMAL,abc"
                );

                CsvOrderProvider provider = new CsvOrderProvider(csv);

                assertNull(provider.getOrders());
        }

        @Test
        void shouldRejectOrderIdWithoutNumericPart() {

                List<String> csv = List.of(
                        "pedido_id,cliente,distancia_km,peso_kg,tipo_servico,data_pedido",
                        "ORD-,Cliente,10,5,NORMAL,2026-05-01"
                );

                CsvOrderProvider provider = new CsvOrderProvider(csv);

                assertNull(provider.getOrders());
        }

        @Test
        void shouldRejectOrderIdContainingLetters() {

                List<String> csv = List.of(
                        "pedido_id,cliente,distancia_km,peso_kg,tipo_servico,data_pedido",
                        "ORD-ABC,Cliente,10,5,NORMAL,2026-05-01"
                );

                CsvOrderProvider provider = new CsvOrderProvider(csv);

                assertNull(provider.getOrders());
        }

        @Test
        void shouldRejectZeroDistance() {

                List<String> csv = List.of(
                        "pedido_id,cliente,distancia_km,peso_kg,tipo_servico,data_pedido",
                        "ORD-1,Cliente,0,5,NORMAL,2026-05-01"
                );

                CsvOrderProvider provider = new CsvOrderProvider(csv);

                assertNull(provider.getOrders());
        }

        @Test
        void shouldRejectZeroWeight() {

                List<String> csv = List.of(
                        "pedido_id,cliente,distancia_km,peso_kg,tipo_servico,data_pedido",
                        "ORD-1,Cliente,10,0,NORMAL,2026-05-01"
                );

                CsvOrderProvider provider = new CsvOrderProvider(csv);

                assertNull(provider.getOrders());
        }


}
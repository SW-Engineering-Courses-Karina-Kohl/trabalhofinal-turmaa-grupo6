package br.edu.ufrgs.provider;
import java.util.List;
import br.edu.ufrgs.model.Order;

public interface OrderProvider {
    List<Order> getOrders();
}

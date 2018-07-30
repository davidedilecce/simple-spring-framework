package hibernate;

import domain.Order;

import java.util.List;

public interface OrderDAO {
    void save(Order order);

    List<Order> find();
}

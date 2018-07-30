package services;

import domain.Order;

import java.util.List;

public interface OrderService {
    void save(Order order);

    List<Order> find();
}

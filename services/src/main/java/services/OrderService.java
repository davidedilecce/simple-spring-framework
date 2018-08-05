package services;

import domain.Order;
import hibernate.exception.EntityNullException;

import java.util.List;

public interface OrderService {

    void save(Order order) throws EntityNullException;
    List<Order> find();

}

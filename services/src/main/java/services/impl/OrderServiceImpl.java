package services.impl;

import domain.Order;
import hibernate.OrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.OrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDAO orderDAO;

    public void save(Order order) {
        orderDAO.save(order);
    }

    public List<Order> find() {
        return orderDAO.find();
    }


}

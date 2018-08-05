package services.impl;

import domain.Order;
import hibernate.HibernateDAO;
import hibernate.exception.EntityNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.OrderService;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private HibernateDAO hibernateDAO;


    public void save(Order order) throws EntityNullException {
        hibernateDAO.save(Optional.of(order));
    }

    public List<Order> find() {
        return (List<Order>) hibernateDAO.find(null, Order.class);
    }

}
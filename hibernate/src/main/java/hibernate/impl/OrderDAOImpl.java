package hibernate.impl;

import domain.Order;
import hibernate.HibernateUtils;
import hibernate.OrderDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {

    public void save(Order order) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.getTransaction().begin();
            session.saveOrUpdate(order);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public List<Order> find() {

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        List<Order> addresses = new ArrayList<Order>();
        try{

            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Order.class);
            addresses = cr.list();

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return addresses;

    }

}

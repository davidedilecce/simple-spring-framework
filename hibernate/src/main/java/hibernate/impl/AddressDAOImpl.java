package hibernate.impl;

import domain.Address;
import hibernate.AddressDAO;
import hibernate.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AddressDAOImpl implements AddressDAO {

    public void save(Address address) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.getTransaction().begin();
            session.saveOrUpdate(address);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public List<Address> find() {

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        List<Address> addresses = new ArrayList<Address>();
        try{

            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Address.class);
            addresses = cr.list();

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return addresses;

    }

}

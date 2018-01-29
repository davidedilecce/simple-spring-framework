package impl;

import base.LoadQuery;
import hibernate.HibernateUtils;
import model.Car;
import model.repositories.CarRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class CarHibernateRepository implements CarRepository {

    public void save(Car obj) {

    }

    public void delete(Car obj) {

    }

    public Car get(Object id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        StringBuilder query = new StringBuilder("from cars");
        query.append(" where id = " + id);
        Query result = session.createQuery(query.toString());
        return result.list().isEmpty() ? null : (Car) result.list().get(0);
    }

    public List<Car> find(LoadQuery q) {
        return null;
    }

}

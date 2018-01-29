package model.impls;

import base.Query;
import hibernate.HibernateRepository;
import model.Car;
import model.repositories.CarRepository;

import java.util.List;

public class CarHibernateRepository extends HibernateRepository<Car> implements CarRepository {

    public void save(Car obj) {

    }

    public void delete(Car obj) {

    }

    public Car get(Object id) {
        return null;
    }

    public List<Car> find(Query q) {
        return null;
    }

}

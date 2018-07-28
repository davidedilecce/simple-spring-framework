package hibernate.impl;

import domain.Person;
import hibernate.HibernateUtils;
import hibernate.PersonDAO;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


@Repository
public class PersonDAOImpl implements PersonDAO {

    public void save(Person person) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            session.getTransaction().begin();
            session.saveOrUpdate(person);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public List<Person> getAll(String name) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        List<Person> persons = new ArrayList<Person>();
        try{

            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Person.class);

            if (StringUtils.hasLength(name)) {
                cr.add(Restrictions.like("name", name, MatchMode.ANYWHERE));
            }

            persons = cr.list();

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return persons;
    }

    public void delete(Integer id) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            session.getTransaction().begin();
            Person person = getById(id);
            if (person != null) {
                session.delete(person);
            }
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public Person getById(Integer id) {

        Person p = null;

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            session.getTransaction().begin();
            p = (Person) session.get(Person.class, id);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return p;

    }

}

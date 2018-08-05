package hibernate.impl;

import domain.HibernateEntity;
import hibernate.HibernateDAO;
import hibernate.exception.EntityNullException;
import hibernate.exception.EntityRowNotPresentException;
import hibernate.utils.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import utils.query.Filter;
import utils.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by davidevincenzodilecce on 05/08/18.
 */
@Repository
public class HibernateDAOImpl implements HibernateDAO {


    @Override
    public void save(Optional<? extends HibernateEntity> entity) throws EntityNullException {

        if (!entity.isPresent())
            throw new EntityNullException();

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            session.getTransaction().begin();
            session.saveOrUpdate(entity.get());
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Override
    public List<? extends HibernateEntity> find(QueryBuilder queryBuilder, Class<? extends HibernateEntity> className) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        List<? extends HibernateEntity> list = new ArrayList<>();

        try {

            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(className);

            if (queryBuilder != null) {
                manageFilters(queryBuilder.getFilters(), criteria);
            }

            list = criteria.list();

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return list;

    }

    private void manageFilters(List<Filter> filters, Object parent) {

        for (Filter filter : filters) {

            switch (filter.getType()) {

                case Filter.LIKE_ANYWHERE:
                    addFilterToParent(Restrictions.like(filter.getProperty(), String.valueOf(filter.getValue()), MatchMode.ANYWHERE), parent);
                    break;
                case Filter.LIKE_START:
                    addFilterToParent(Restrictions.like(filter.getProperty(), String.valueOf(filter.getValue()), MatchMode.START), parent);
                    break;
                case Filter.LIKE_END:
                    addFilterToParent(Restrictions.like(filter.getProperty(), String.valueOf(filter.getValue()), MatchMode.END), parent);
                    break;
                case Filter.EQUALS:
                    addFilterToParent(Restrictions.eq(filter.getProperty(), filter.getValue()), parent);
                    break;
                case Filter.DISJUNCTION:

                    org.hibernate.criterion.Disjunction disjunction = Restrictions.disjunction();

                    manageFilters(((utils.query.Disjunction) filter.getValue()).getFilters(), disjunction);

                    addFilterToParent(disjunction, parent);

                    break;
                case Filter.CONJUNCTION:

                    org.hibernate.criterion.Conjunction conjunction = Restrictions.conjunction();

                    manageFilters(((utils.query.Conjunction) filter.getValue()).getFilters(), conjunction);

                    addFilterToParent(conjunction, parent);

                    break;

            }

        }

    }

    private void addFilterToParent(Criterion criterion, Object parent) {
        if (parent instanceof org.hibernate.criterion.Disjunction) {
            ((org.hibernate.criterion.Disjunction) parent).add(criterion);
        } else if (parent instanceof org.hibernate.Criteria) {
            ((org.hibernate.Criteria) parent).add(criterion);
        }
    }

    @Override
    public void delete(Integer id, Class<? extends HibernateEntity> className) throws EntityRowNotPresentException {

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        try {

            session.getTransaction().begin();
            Optional<? extends HibernateEntity> entityById = getById(id, className);
            if (entityById.isPresent()) {
                session.delete(entityById.get());
            } else {
                throw new EntityRowNotPresentException();
            }
            session.getTransaction().commit();

        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    @Override
    public Optional<? extends HibernateEntity> getById(Integer id, Class<? extends HibernateEntity> className) {

        HibernateEntity entity = null;

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            session.getTransaction().begin();
            entity = (HibernateEntity) session.get(className, id);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return Optional.of(entity);

    }


}
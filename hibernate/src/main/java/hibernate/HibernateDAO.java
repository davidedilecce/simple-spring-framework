package hibernate;

import domain.HibernateEntity;
import hibernate.exception.EntityNullException;
import hibernate.exception.EntityRowNotPresentException;
import utils.query.QueryBuilder;

import java.util.List;
import java.util.Optional;

/**
 * Created by davidevincenzodilecce on 05/08/18.
 */
public interface HibernateDAO {

    void save(Optional<? extends HibernateEntity> entity) throws EntityNullException;

    List<? extends HibernateEntity> find(QueryBuilder queryBuilder, Class<? extends HibernateEntity> className);

    void delete(Integer id, Class<? extends HibernateEntity> className) throws EntityRowNotPresentException;

    Optional<? extends HibernateEntity> getById(Integer id, Class<? extends HibernateEntity> className);

}
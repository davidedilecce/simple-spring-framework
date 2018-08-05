package services.impl;

import domain.Person;
import hibernate.HibernateDAO;
import hibernate.exception.EntityNullException;
import hibernate.exception.EntityRowNotPresentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import services.PersonService;
import utils.query.Filter;
import utils.query.QueryBuilder;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private HibernateDAO hibernateDAO;

    public void save(Person person) throws EntityNullException {
        hibernateDAO.save(Optional.of(person));
    }

    public List<Person> getAll(String name) {

        QueryBuilder queryBuilder = new QueryBuilder();

        if (StringUtils.hasLength(name)) {
            queryBuilder.getFilters().add(new Filter("name", name, Filter.LIKE_ANYWHERE));
        }

        return (List<Person>) hibernateDAO.find(queryBuilder, Person.class);

    }

    public Person getById(Integer id) {
        return (Person) hibernateDAO.getById(id, Person.class).orElse(null);
    }

    public void delete(Integer id) throws EntityRowNotPresentException {
        hibernateDAO.delete(id, Person.class);
    }

}

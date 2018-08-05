package services;

import domain.Person;
import hibernate.exception.EntityNullException;
import hibernate.exception.EntityRowNotPresentException;

import java.util.List;

public interface PersonService {

    void save(Person person) throws EntityNullException;

    List<Person> getAll(String name);

    void delete(Integer id) throws EntityRowNotPresentException;

    Person getById(Integer id);

}
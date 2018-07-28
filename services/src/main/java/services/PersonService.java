package services;

import domain.Person;

import java.util.List;

public interface PersonService {

    void save(Person person);

    List<Person> getAll(String name);

    void delete(Integer id);

    Person getById(Integer id);

}
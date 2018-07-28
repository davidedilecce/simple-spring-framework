package hibernate;

import domain.Person;

import java.util.List;

public interface PersonDAO {

    void save(Person person);

    List<Person> getAll(String name);

    void delete(Integer id);

    Person getById(Integer id);
}

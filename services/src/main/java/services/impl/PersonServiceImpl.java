package services.impl;

import domain.Person;
import hibernate.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.PersonService;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonDAO personDAO;

    public void save(Person person) {
        personDAO.save(person);
    }

    public List<Person> getAll(String name) {
        return personDAO.getAll(name);
    }

    public Person getById(Integer id) {
        return personDAO.getById(id);
    }

    public void delete(Integer id) {
        personDAO.delete(id);
    }

}

package fe.facade;

import domain.Person;
import fe.exceptions.NameNotSettedException;
import fe.exceptions.SurnameNotSettedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import services.PersonService;

import java.util.List;

@Component
public class MainFacade {

    @Autowired
    private PersonService personService;

    public Person savePerson(Person person) throws NameNotSettedException, SurnameNotSettedException {

        if (StringUtils.isEmpty(person.getName())) {
            throw new NameNotSettedException();
        }
        if (StringUtils.isEmpty(person.getSurname())) {
            throw new SurnameNotSettedException();
        }

        personService.save(person);

        return person;

    }

    public List<Person> getAllPersons(String name) {
        return personService.getAll(name);
    }

    public void deletePerson(Integer id) {
        personService.delete(id);
    }

}

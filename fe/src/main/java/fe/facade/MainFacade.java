package fe.facade;

import domain.Address;
import domain.Order;
import domain.Person;
import fe.exceptions.NameNotSettedException;
import fe.exceptions.SurnameNotSettedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import services.AddressService;
import services.OrderService;
import services.PersonService;

import java.util.Date;
import java.util.List;

@Component
public class MainFacade {

    @Autowired
    private PersonService personService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private OrderService orderService;





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

    public void generateAddress() {

        Address address = new Address();

        address.setCity("Matera");
        address.setNation("Italia");
        address.setPerson(personService.getAll(null).get(0));

        addressService.save(address);

    }

    public Address getAddress() {
        return addressService.find().get(0);
    }

    public void generateOrder() {

        Order order = new Order();

        order.setAddress(getAddress());
        order.setDate(new Date());
        order.setNotes("Niente da segnalare....");
        order.setTotal(1000.0);
        order.setPerson(personService.getAll(null).get(0));

        orderService.save(order);

    }

    public Order getOrder() {
        return orderService.find().get(0);
    }

}

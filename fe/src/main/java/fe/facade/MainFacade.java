package fe.facade;

import domain.Address;
import domain.Order;
import domain.Person;
import fe.exceptions.NameNotSettedException;
import fe.exceptions.SurnameNotSettedException;
import hibernate.exception.EntityNullException;
import hibernate.exception.EntityRowNotPresentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import services.AddressService;
import services.OrderService;
import services.PersonService;
import services.StudentService;
import testMongoDomain.Student;
import utils.query.Disjunction;
import utils.query.Filter;
import utils.query.QueryBuilder;

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

    @Autowired
    private StudentService studentService;


    public Person savePerson(Person person) throws NameNotSettedException, SurnameNotSettedException, EntityNullException {

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

    public void deletePerson(Integer id) throws EntityRowNotPresentException {
        personService.delete(id);
    }

    public void generateAddress() throws EntityNullException {

        Address address = new Address();

        address.setCity("Matera");
        address.setNation("Italia");
        address.setPerson(personService.getAll(null).get(0));

        addressService.save(address);

    }

    public Address getAddress() {
        return addressService.find().get(0);
    }

    public void generateOrder() throws EntityNullException {

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

    public Student getStudent(String id) {
        return studentService.getById(id);
    }

    public void saveStudent() {
        Student student = new Student();
        student.setSurname("DI LECCE");
        student.setName("DAVIDE");
        studentService.save(student);
    }

    public Student studentUpdate(String id) {
        Student student = studentService.getById(id);
        student.setName("ANNA");
        studentService.save(student);
        return student;
    }

    public List<Student> findStudents() {
        Disjunction disjunction = new Disjunction();
        disjunction.getFilters().add(new Filter("name", "Anna", Filter.LIKE_ANYWHERE));
        disjunction.getFilters().add(new Filter("name", "Davide", Filter.LIKE_ANYWHERE));
        return studentService.find(new QueryBuilder().addDisjunction(disjunction));
    }

    public void studentDelete() {
        studentService.delete("5bfb2a6777c804e292ab3ed0");
    }
}

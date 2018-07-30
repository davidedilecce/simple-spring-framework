package hibernate;

import domain.Address;

import java.util.List;

public interface AddressDAO {

    void save(Address address);

    List<Address> find();

}

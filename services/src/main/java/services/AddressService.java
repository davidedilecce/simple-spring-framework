package services;

import domain.Address;
import hibernate.exception.EntityNullException;

import java.util.List;

public interface AddressService {

    void save(Address address) throws EntityNullException;

    List<Address> find();

}

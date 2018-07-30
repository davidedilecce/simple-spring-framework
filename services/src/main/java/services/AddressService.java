package services;

import domain.Address;

import java.util.List;

public interface AddressService {

    void save(Address address);

    List<Address> find();

}

package services.impl;

import domain.Address;
import hibernate.AddressDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.AddressService;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDAO addressDao;

    public void save(Address address) {
        addressDao.save(address);
    }

    public List<Address> find() {
        return addressDao.find();
    }

}

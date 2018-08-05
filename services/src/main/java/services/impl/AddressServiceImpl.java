package services.impl;

import domain.Address;
import hibernate.HibernateDAO;
import hibernate.exception.EntityNullException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.AddressService;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private HibernateDAO hibernateDAO;

    public void save(Address address) throws EntityNullException {
        hibernateDAO.save(Optional.of(address));
    }

    public List<Address> find() {
        return (List<Address>) hibernateDAO.find(null, Address.class);
    }

}

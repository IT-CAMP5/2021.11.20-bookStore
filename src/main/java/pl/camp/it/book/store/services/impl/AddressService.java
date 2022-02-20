package pl.camp.it.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.book.store.database.IAddressDAO;
import pl.camp.it.book.store.model.Address;
import pl.camp.it.book.store.services.IAddressService;

import java.util.Optional;

@Service
public class AddressService implements IAddressService {

    @Autowired
    IAddressDAO addressDAO;

    @Override
    public Optional<Address> getAddressById(int id) {
        return this.addressDAO.getAddressById(id);
    }
}

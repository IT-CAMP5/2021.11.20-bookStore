package pl.camp.it.book.store.database;

import pl.camp.it.book.store.model.Address;

import java.util.Optional;

public interface IAddressDAO {
    void addAddress(Address address);
    Optional<Address> getAddressById(int id);
}

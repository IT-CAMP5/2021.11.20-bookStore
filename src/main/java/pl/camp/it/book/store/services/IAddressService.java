package pl.camp.it.book.store.services;

import pl.camp.it.book.store.model.Address;

import java.util.Optional;

public interface IAddressService {
    Optional<Address> getAddressById(int id);
}

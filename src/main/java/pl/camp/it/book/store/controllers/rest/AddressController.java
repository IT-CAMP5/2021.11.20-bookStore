package pl.camp.it.book.store.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.camp.it.book.store.database.IAddressDAO;
import pl.camp.it.book.store.model.Address;
import pl.camp.it.book.store.services.IAddressService;

import java.util.Optional;

@RestController
@RequestMapping(value = "/address")
public class AddressController {

    @Autowired
    IAddressService addressService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Address> getAddressById(@PathVariable int id) {
        Optional<Address> addressBox = this.addressService.getAddressById(id);
        if(addressBox.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(addressBox.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

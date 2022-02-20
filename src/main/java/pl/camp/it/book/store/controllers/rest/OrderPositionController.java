package pl.camp.it.book.store.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.camp.it.book.store.model.Book;
import pl.camp.it.book.store.model.OrderPosition;
import pl.camp.it.book.store.model.dto.OrderPositionDTO;
import pl.camp.it.book.store.services.IOrderPositionService;

import java.util.Optional;

@RestController
@RequestMapping(value = "/orderposition")
public class OrderPositionController {

    @Autowired
    IOrderPositionService orderPositionService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<OrderPositionDTO> getOrderPositionById(@PathVariable int id) {
        Optional<OrderPosition> orderPositionBox = this.orderPositionService.getOrderPositionById(id);
        if(orderPositionBox.isPresent()) {
            OrderPosition orderPosition = orderPositionBox.get();
            OrderPositionDTO orderPositionDTO = new OrderPositionDTO(orderPosition);
            return ResponseEntity.status(HttpStatus.OK).body(orderPositionDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

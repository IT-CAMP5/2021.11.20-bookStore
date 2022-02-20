package pl.camp.it.book.store.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.camp.it.book.store.model.Order;
import pl.camp.it.book.store.model.dto.OrderDTO;
import pl.camp.it.book.store.services.IOrderService;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/order")
public class RestOrderController {

    @Autowired
    IOrderService orderService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable int id) {
        Optional<Order> orderBox = this.orderService.getOrderById(id);
        if(orderBox.isPresent()) {
            OrderDTO orderDTO = new OrderDTO(orderBox.get());
            return ResponseEntity.status(HttpStatus.OK).body(orderDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}

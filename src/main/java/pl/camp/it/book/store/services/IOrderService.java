package pl.camp.it.book.store.services;

import pl.camp.it.book.store.model.Address;
import pl.camp.it.book.store.model.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    void confirmOrder(Address address);
    List<Order> getOrdersForCurrentUser();
    Optional<Order> getOrderById(int id);
}

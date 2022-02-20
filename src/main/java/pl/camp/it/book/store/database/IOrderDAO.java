package pl.camp.it.book.store.database;

import pl.camp.it.book.store.model.Order;

import java.util.List;
import java.util.Optional;

public interface IOrderDAO {
    void addOrder(Order order);
    List<Order> getOrdersByUserLogin(String login);
    Optional<Order> getOrderById(int id);
}

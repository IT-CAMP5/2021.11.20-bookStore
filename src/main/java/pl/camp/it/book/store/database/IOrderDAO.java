package pl.camp.it.book.store.database;

import pl.camp.it.book.store.model.Order;

import java.util.List;

public interface IOrderDAO {
    void addOrder(Order order);
    List<Order> getOrdersByUserLogin(String login);
}

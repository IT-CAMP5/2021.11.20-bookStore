package pl.camp.it.book.store.database.impl.memory;

import org.springframework.stereotype.Repository;
import pl.camp.it.book.store.database.IOrderDAO;
import pl.camp.it.book.store.model.Order;
import java.util.List;
import java.util.ArrayList;

public class OrderDatabase implements IOrderDAO {
    private final List<Order> orders = new ArrayList<>();

    @Override
    public void addOrder(Order order) {
        this.orders.add(order);
    }

    @Override
    public List<Order> getOrdersByUserLogin(String login) {
        List<Order> result = new ArrayList<>();
        for(Order order : this.orders) {
            if(order.getUser().getLogin().equals(login)) {
                result.add(order);
            }
        }
        return result;
    }
}

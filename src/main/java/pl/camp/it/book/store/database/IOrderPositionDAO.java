package pl.camp.it.book.store.database;

import pl.camp.it.book.store.model.OrderPosition;
import java.util.List;
import java.util.Optional;

public interface IOrderPositionDAO {
    void addOrderPosition(OrderPosition orderPosition, int orderId);
    List<OrderPosition> getOrderPositionsByOrderId(int orderId);
    Optional<OrderPosition> getOrderPositionById(int id);
}

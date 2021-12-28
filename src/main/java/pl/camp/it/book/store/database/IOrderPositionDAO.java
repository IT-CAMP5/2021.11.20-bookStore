package pl.camp.it.book.store.database;

import pl.camp.it.book.store.model.OrderPosition;
import java.util.List;

public interface IOrderPositionDAO {
    void addOrderPosition(OrderPosition orderPosition, int orderId);
    List<OrderPosition> getOrderPositionsByOrderId(int orderId);
}

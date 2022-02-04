package pl.camp.it.book.store.databse;

import pl.camp.it.book.store.database.IOrderDAO;
import pl.camp.it.book.store.model.Order;

import java.util.List;

public class OrderDAOStub implements IOrderDAO {

    private boolean addOrderFlag = false;

    @Override
    public void addOrder(Order order) {
        this.addOrderFlag = true;
    }

    @Override
    public List<Order> getOrdersByUserLogin(String login) {
        return null;
    }

    public boolean isAddOrderFlag() {
        return addOrderFlag;
    }

    public void setAddOrderFlag(boolean addOrderFlag) {
        this.addOrderFlag = addOrderFlag;
    }
}

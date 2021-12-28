package pl.camp.it.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.database.IOrderDAO;
import pl.camp.it.book.store.model.*;
import pl.camp.it.book.store.services.IOrderService;
import pl.camp.it.book.store.session.SessionObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

@Service
public class OrderService implements IOrderService {

    @Autowired
    IBookDAO bookDatabase;

    @Autowired
    IOrderDAO orderDatabase;

    @Resource
    SessionObject sessionObject;

    @Override
    public void confirmOrder(Address address) {
        Cart cart = this.sessionObject.getCart();
        List<OrderPosition> orderPositions = cart.getPositions();
        for(OrderPosition orderPosition : orderPositions) {
            Optional<Book> bookBox = this.bookDatabase.getBookByIsbn(orderPosition.getBook().getIsbn());
            if(!bookBox.isPresent()) {
                return;
            }
            if(orderPosition.getPositionQuantity() > bookBox.get().getQuantity()) {
                return;
            }
        }
        Order order = new Order();
        //order.setId(new Random().nextInt(1000000));
        order.setOrderPositions(orderPositions);
        order.setAddress(address);
        order.setUser(this.sessionObject.getUser());
        order.setStatus(Order.Status.NEW);
        this.orderDatabase.addOrder(order);
        for(OrderPosition orderPosition : orderPositions) {
            Optional<Book> bookBox = this.bookDatabase.getBookByIsbn(orderPosition.getBook().getIsbn());
            Book book = bookBox.get();
            book.setQuantity(book.getQuantity() - orderPosition.getPositionQuantity());
            this.bookDatabase.updateBook(book);
        }
        cart.setPositions(new ArrayList<>());
    }

    @Override
    public List<Order> getOrdersForCurrentUser() {
        return this.orderDatabase.getOrdersByUserLogin(this.sessionObject.getUser().getLogin());
    }
}

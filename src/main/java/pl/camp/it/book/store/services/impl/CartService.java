package pl.camp.it.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.model.Book;
import pl.camp.it.book.store.model.OrderPosition;
import pl.camp.it.book.store.services.ICartService;
import pl.camp.it.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired
    IBookDAO bookDatabase;

    @Resource
    SessionObject sessionObject;

    @Override
    public void addToCart(String isbn) {
        Optional<Book> bookBox = bookDatabase.getBookByIsbn(isbn);
        if(!bookBox.isPresent()) {
            return;
        }
        for(OrderPosition orderPosition : sessionObject.getCart().getPositions()) {
            if(orderPosition.getBook().getIsbn().equals(isbn)) {
                orderPosition.increaseQuantity();
                return;
            }
        }
        OrderPosition orderPosition = new OrderPosition();
        orderPosition.setBook(bookBox.get());
        orderPosition.setPositionQuantity(1);
        this.sessionObject.getCart().getPositions().add(orderPosition);
    }
}

package pl.camp.it.book.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.book.store.database.BookDatabase;
import pl.camp.it.book.store.model.Book;
import pl.camp.it.book.store.model.OrderPosition;
import pl.camp.it.book.store.session.SessionObject;

import javax.annotation.Resource;

@Service
public class CartService {

    @Autowired
    BookDatabase bookDatabase;

    @Resource
    SessionObject sessionObject;

    public void addToCart(String isbn) {
        Book book = bookDatabase.getBookByIsbn(isbn);

        if(book == null) {
            return;
        }

        for(OrderPosition orderPosition : sessionObject.getCart().getPositions()) {
            if(orderPosition.getBook().getIsbn().equals(isbn)) {
                orderPosition.increaseQuantity();
                return;
            }
        }

        OrderPosition orderPosition = new OrderPosition();
        orderPosition.setBook(book);
        orderPosition.setPositionQuantity(1);

        this.sessionObject.getCart().getPositions().add(orderPosition);
    }
}

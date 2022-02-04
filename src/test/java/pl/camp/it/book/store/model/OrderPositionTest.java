package pl.camp.it.book.store.model;

import org.junit.Assert;
import org.junit.Test;

public class OrderPositionTest {

    @Test
    public void increaseOrderPositionQuantity() {
        OrderPosition orderPosition = new OrderPosition();
        orderPosition.setPositionQuantity(3);
        orderPosition.setId(1);
        orderPosition.setBook(generateBook());
        int expectedQuantity = 4;
        int expectedQuantity2 = 6;

        orderPosition.increaseQuantity();

        Assert.assertEquals(expectedQuantity, orderPosition.getPositionQuantity());

        orderPosition.increaseQuantity();
        orderPosition.increaseQuantity();

        Assert.assertEquals(expectedQuantity2, orderPosition.getPositionQuantity());
    }

    private Book generateBook() {
        Book book = new Book();
        book.setId(1);
        book.setTitle("alksjdghflkahjgsdh");
        book.setAuthor("kasjhdgfkjahsd");
        book.setIsbn("67842-9834-7348-3465");
        book.setPrice(23.45);
        book.setQuantity(2345);

        return book;
    }
}

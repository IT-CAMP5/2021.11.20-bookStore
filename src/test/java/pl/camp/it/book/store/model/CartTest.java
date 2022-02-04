package pl.camp.it.book.store.model;

import org.junit.Assert;
import org.junit.Test;

public class CartTest {

    @Test
    public void standardCartSum() {
        Cart cart = new Cart();
        cart.getPositions().add(generateOrderPosition(1, 29.90));
        cart.getPositions().add(generateOrderPosition(2, 49.90));
        cart.getPositions().add(generateOrderPosition(34, 9.90));
        double expectedResult = 466.3;
        double result = cart.calculateSum();
        Assert.assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void emptyCartTest() {
        Cart cart = new Cart();
        double expectedResult = 0.0;
        double result = cart.calculateSum();
        Assert.assertEquals(expectedResult, result, 0.001);
    }

    private OrderPosition generateOrderPosition(int quantity, double price) {
        Book book = new Book();
        book.setId(1);
        book.setTitle("alksjdghflkahjgsdh");
        book.setAuthor("kasjhdgfkjahsd");
        book.setIsbn("67842-9834-7348-3465");
        book.setPrice(price);
        book.setQuantity(2345);

        OrderPosition orderPosition = new OrderPosition();
        orderPosition.setId(1);
        orderPosition.setPositionQuantity(quantity);
        orderPosition.setBook(book);

        return orderPosition;
    }
}

package pl.camp.it.book.store.model;

import org.junit.Assert;
import org.junit.Test;

public class OrderTest {

    @Test
    public void standardOrderTest() {
        Order order = new Order();
        order.setId(1);
        order.setStatus(Order.Status.NEW);
        order.setUser(generateUser());
        order.setAddress(generateAddress());
        order.getOrderPositions().add(generateOrderPosition(1, 24.90));
        order.getOrderPositions().add(generateOrderPosition(2, 20.90));
        order.getOrderPositions().add(generateOrderPosition(15, 15.40));
        double expectedResult = 297.7;

        double result = order.calculateSum();

        Assert.assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void emptyOrderTest() {
        Order order = new Order();
        order.setId(1);
        order.setStatus(Order.Status.NEW);
        order.setUser(generateUser());
        order.setAddress(generateAddress());
        double expectedResult = 0.0;

        double result = order.calculateSum();

        Assert.assertEquals(expectedResult, result, 0.001);
    }

    private User generateUser() {
        User user = new User();
        user.setLogin("admin");
        user.setPassword("asdf234sdf324sd5re");
        user.setMail("sdgf@dsfg.pl");
        user.setName("Janusz");
        user.setSurname("Kowalski");
        user.setId(1);

        return user;
    }

    private Address generateAddress() {
        Address address = new Address();
        address.setAddress("Ogrodowa 12/4");
        address.setPhone("123123123");
        address.setCity("Kraków");
        address.setPostalCode("12-123");
        address.setSurname("Kowalski");
        address.setName("Janusz");
        address.setId(1);

        return address;
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

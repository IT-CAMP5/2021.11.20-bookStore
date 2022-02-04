package pl.camp.it.book.store.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.camp.it.book.store.configuration.TestConfiguration;
import pl.camp.it.book.store.database.IOrderDAO;
import pl.camp.it.book.store.databse.BookDAOStub;
import pl.camp.it.book.store.databse.OrderDAOStub;
import pl.camp.it.book.store.model.Address;
import pl.camp.it.book.store.model.Book;
import pl.camp.it.book.store.model.OrderPosition;
import pl.camp.it.book.store.services.IOrderService;
import pl.camp.it.book.store.session.SessionObject;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestConfiguration.class})
public class OrderServiceTest {

    @Autowired
    IOrderService orderService;

    @Resource
    SessionObject sessionObject;

    @Autowired
    OrderDAOStub orderDAO;

    @Autowired
    BookDAOStub bookDAO;

    @Test
    public void confirmOrderWithNotExistingBookInCartTest() {
        this.orderDAO.setAddOrderFlag(false);
        this.sessionObject.getCart().getPositions().clear();
        Assert.assertEquals(0, this.sessionObject.getCart().getPositions().size());

        this.sessionObject.getCart().getPositions().add(generateOrderPosition("1234-1234-1234-1234", 10));

        this.orderService.confirmOrder(generateAddress());

        Assert.assertEquals(1, this.sessionObject.getCart().getPositions().size());
        Assert.assertFalse(this.orderDAO.isAddOrderFlag());
    }

    @Test
    public void confirmOrderWithTooBigQuantity() {
        this.orderDAO.setAddOrderFlag(false);
        this.sessionObject.getCart().getPositions().clear();
        Assert.assertEquals(0, this.sessionObject.getCart().getPositions().size());

        this.sessionObject.getCart().getPositions().add(generateOrderPosition("1234-1234-5674-1287", 100));

        this.orderService.confirmOrder(generateAddress());

        Assert.assertEquals(1, this.sessionObject.getCart().getPositions().size());
        Assert.assertFalse(this.orderDAO.isAddOrderFlag());
    }

    @Test
    public void confirmOrderTest() {
        this.orderDAO.setAddOrderFlag(false);
        this.bookDAO.setUpdateBookFlag(false);
        this.sessionObject.getCart().getPositions().clear();
        Assert.assertEquals(0, this.sessionObject.getCart().getPositions().size());

        this.sessionObject.getCart().getPositions().add(generateOrderPosition("1234-1234-5674-1287", 1));

        this.orderService.confirmOrder(generateAddress());

        Assert.assertEquals(0, this.sessionObject.getCart().getPositions().size());
        Assert.assertTrue(this.orderDAO.isAddOrderFlag());
        Assert.assertTrue(this.bookDAO.isUpdateBookFlag());
    }

    private OrderPosition generateOrderPosition(String isbn, int positionQuantity) {
        Book book = new Book();
        book.setId(1);
        book.setQuantity(10);
        book.setPrice(20.00);
        book.setIsbn(isbn);
        book.setAuthor("Janusz Kowalski");
        book.setTitle("Tytuł");

        OrderPosition orderPosition = new OrderPosition();
        orderPosition.setBook(book);
        orderPosition.setPositionQuantity(positionQuantity);

        return orderPosition;
    }

    private Address generateAddress() {
        Address address = new Address();
        address.setAddress("asdffasd");
        address.setName("asdfasdf");
        address.setSurname("asdfgasdf");
        address.setCity("sdafasdf");
        address.setPhone("123123123");
        address.setPostalCode("12-123");

        return address;
    }
}

package pl.camp.it.book.store.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.camp.it.book.store.configuration.TestConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.database.IOrderDAO;
import pl.camp.it.book.store.database.IUserDAO;
import pl.camp.it.book.store.databse.BookDAOStub;
import pl.camp.it.book.store.databse.OrderDAOStub;
import pl.camp.it.book.store.model.*;
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

    /*@Autowired
    OrderDAOStub orderDAO;

    @Autowired
    BookDAOStub bookDAO;*/

    @MockBean
    IUserDAO userDAO;

    @MockBean
    IBookDAO bookDAO;

    @MockBean
    IOrderDAO orderDAO;

    @Test
    public void confirmOrderWithNotExistingBookInCartTest() {
        Mockito.when(this.bookDAO.getBookByIsbn(Mockito.anyString())).thenReturn(Optional.empty());

        this.sessionObject.getCart().getPositions().clear();
        Assert.assertEquals(0, this.sessionObject.getCart().getPositions().size());
        this.sessionObject.getCart().getPositions().add(generateOrderPosition("1234-1234-1234-1234", 10));
        this.orderService.confirmOrder(generateAddress());

        Assert.assertEquals(1, this.sessionObject.getCart().getPositions().size());
        Mockito.verify(this.orderDAO, Mockito.never()).addOrder(Mockito.any());
    }

    @Test
    public void confirmOrderWithTooBigQuantity() {
        Book book = new Book();
        book.setId(1);
        book.setQuantity(10);
        book.setPrice(20.00);
        book.setIsbn("1234-1234-5674-1287");
        book.setAuthor("Janusz Kowalski");
        book.setTitle("Tytuł");
        Mockito.when(this.bookDAO.getBookByIsbn("1234-1234-5674-1287")).thenReturn(Optional.of(book));

        this.sessionObject.getCart().getPositions().clear();
        Assert.assertEquals(0, this.sessionObject.getCart().getPositions().size());

        this.sessionObject.getCart().getPositions().add(generateOrderPosition("1234-1234-5674-1287", 100));

        this.orderService.confirmOrder(generateAddress());

        Assert.assertEquals(1, this.sessionObject.getCart().getPositions().size());
        Mockito.verify(this.orderDAO, Mockito.never()).addOrder(Mockito.any());
    }

    @Test
    public void confirmOrderTest() {
        Book book = new Book();
        book.setId(1);
        book.setQuantity(10);
        book.setPrice(20.00);
        book.setIsbn("1234-1234-5674-1287");
        book.setAuthor("Janusz Kowalski");
        book.setTitle("Tytuł");
        Mockito.when(this.bookDAO.getBookByIsbn("1234-1234-5674-1287")).thenReturn(Optional.of(book));

        this.sessionObject.getCart().getPositions().clear();
        Assert.assertEquals(0, this.sessionObject.getCart().getPositions().size());

        this.sessionObject.getCart().getPositions().add(generateOrderPosition("1234-1234-5674-1287", 1));

        this.orderService.confirmOrder(generateAddress());

        Assert.assertEquals(0, this.sessionObject.getCart().getPositions().size());
        Mockito.verify(this.orderDAO, Mockito.times(1)).addOrder(Mockito.any());
        Mockito.verify(this.bookDAO, Mockito.times(1)).updateBook(Mockito.any());
    }

    @Test
    public void getOrdersForCurrentUserTest() {
        this.sessionObject.setUser(generateUser());
        int expectedOrdersCount = 2;
        String expectedOrderUserLogin = "admin";
        List<Order> orders = new ArrayList<>();
        orders.add(generateOrder(expectedOrderUserLogin));
        orders.add(generateOrder(expectedOrderUserLogin));
        Mockito.when(this.orderDAO.getOrdersByUserLogin("admin")).thenReturn(orders);

        List<Order> result = this.orderService.getOrdersForCurrentUser();
        Assert.assertEquals(expectedOrdersCount, result.size());
        Assert.assertEquals(expectedOrderUserLogin, result.get(0).getUser().getLogin());
        Assert.assertEquals(expectedOrderUserLogin, result.get(1).getUser().getLogin());
        Assert.assertEquals(orders, result);
    }

    private Order generateOrder(String login) {
        Order order = new Order();
        order.setId(1);
        order.getOrderPositions().add(generateOrderPosition("123-123-123-123", 2));
        order.getOrderPositions().add(generateOrderPosition("123-123-123-124", 1));

        User user = new User();
        user.setId(10);
        user.setLogin(login);
        user.setSurname("Jaskdfhjg");
        user.setName("Gasdfassdf");
        user.setMail("sdf@sdf.pl");
        user.setPassword("sdgf76sdfg76sdfgsd76sdf");

        order.setUser(user);
        order.setStatus(Order.Status.NEW);

        Address address = new Address();
        address.setPostalCode("12-123");
        address.setPhone("123123123");
        address.setCity("Gsdfasdf");
        address.setId(1);
        address.setAddress("Asdfgsd 3/4");
        address.setSurname("Fasdfasd");
        address.setName("Fsdfassd");

        order.setAddress(address);

        return order;
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

    private User generateUser() {
        User user = new User();
        user.setId(10);
        user.setLogin("admin");
        user.setSurname("Jaskdfhjg");
        user.setName("Gasdfassdf");
        user.setMail("sdf@sdf.pl");
        user.setPassword("sdgf76sdfg76sdfgsd76sdf");

        return user;
    }
}

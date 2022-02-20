package pl.camp.it.book.store.databse;

import pl.camp.it.book.store.database.IOrderDAO;
import pl.camp.it.book.store.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDAOStub implements IOrderDAO {

    private boolean addOrderFlag = false;

    @Override
    public void addOrder(Order order) {
        this.addOrderFlag = true;
    }

    @Override
    public List<Order> getOrdersByUserLogin(String login) {
        List<Order> orders = new ArrayList<>();

        orders.add(generateOrder(login));
        orders.add(generateOrder(login));

        return orders;
    }

    @Override
    public Optional<Order> getOrderById(int id) {
        return Optional.empty();
    }

    public boolean isAddOrderFlag() {
        return addOrderFlag;
    }

    public void setAddOrderFlag(boolean addOrderFlag) {
        this.addOrderFlag = addOrderFlag;
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
}

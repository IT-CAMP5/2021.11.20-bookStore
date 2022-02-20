package pl.camp.it.book.store.model.dto;

import pl.camp.it.book.store.model.Order;
import pl.camp.it.book.store.model.OrderPosition;

import java.util.HashSet;
import java.util.Set;

public class OrderDTO {
    private int id;
    private Set<String> orderPositions = new HashSet<>();
    private String address;
    private String user;
    private Order.Status status;

    public OrderDTO(int id, Set<String> orderPositions, String address, String user, Order.Status status) {
        this.id = id;
        this.orderPositions = orderPositions;
        this.address = address;
        this.user = user;
        this.status = status;
    }

    public OrderDTO(Order order) {
        this.id = order.getId();
        for(OrderPosition position : order.getOrderPositions()) {
            String link = "http://localhost:8080/orderposition/" + position.getId();
            this.orderPositions.add(link);
        }
        this.address = "http://localhost:8080/address/" + order.getAddress().getId();
        this.user = "http://localhost:8080/user/" + order.getUser().getId();
        this.status = order.getStatus();
    }

    public OrderDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<String> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(Set<String> orderPositions) {
        this.orderPositions = orderPositions;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Order.Status getStatus() {
        return status;
    }

    public void setStatus(Order.Status status) {
        this.status = status;
    }
}

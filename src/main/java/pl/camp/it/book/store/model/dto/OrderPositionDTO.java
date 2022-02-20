package pl.camp.it.book.store.model.dto;

import pl.camp.it.book.store.model.OrderPosition;

public class OrderPositionDTO {
    private int id;
    private String book;
    private int positionQuantity;

    public OrderPositionDTO(int id, String book, int positionQuantity) {
        this.id = id;
        this.book = book;
        this.positionQuantity = positionQuantity;
    }

    public OrderPositionDTO(OrderPosition orderPosition) {
        this.id = orderPosition.getId();
        this.positionQuantity = orderPosition.getPositionQuantity();
        this.book = "http://localhost:8080/book/" + orderPosition.getBook().getId();
    }

    public OrderPositionDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public int getPositionQuantity() {
        return positionQuantity;
    }

    public void setPositionQuantity(int positionQuantity) {
        this.positionQuantity = positionQuantity;
    }
}

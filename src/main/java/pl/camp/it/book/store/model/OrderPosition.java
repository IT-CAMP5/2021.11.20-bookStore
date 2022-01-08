package pl.camp.it.book.store.model;

import javax.persistence.*;

@Entity(name = "torderposition")
public class OrderPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Book book;
    private int positionQuantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getPositionQuantity() {
        return positionQuantity;
    }

    public void setPositionQuantity(int positionQuantity) {
        this.positionQuantity = positionQuantity;
    }

    public void increaseQuantity() {
        this.positionQuantity++;
    }
}

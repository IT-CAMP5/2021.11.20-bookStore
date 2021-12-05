package pl.camp.it.book.store.model;

public class OrderPosition {
    private Book book;
    private int positionQuantity;

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

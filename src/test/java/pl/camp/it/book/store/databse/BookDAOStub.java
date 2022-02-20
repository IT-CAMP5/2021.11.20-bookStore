package pl.camp.it.book.store.databse;

import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.model.Book;

import java.util.List;
import java.util.Optional;

public class BookDAOStub implements IBookDAO {

    private boolean updateBookFlag = false;
    @Override
    public List<Book> getBooks() {
        return null;
    }

    @Override
    public Optional<Book> getBookByIsbn(String isbn) {
        if(isbn.equals("1234-1234-1234-1234")) {
            return Optional.empty();
        } else {
            Book book = new Book();
            book.setId(1);
            book.setQuantity(10);
            book.setPrice(20.00);
            book.setIsbn(isbn);
            book.setAuthor("Janusz Kowalski");
            book.setTitle("Tytuł");

            return Optional.of(book);
        }
    }

    @Override
    public Optional<Book> getBookById(int bookId) {
        return Optional.empty();
    }

    @Override
    public void updateBook(Book book) {
        this.updateBookFlag = true;
    }

    @Override
    public void addBook(Book book) {

    }

    @Override
    public void deleteBook(int id) {

    }

    public boolean isUpdateBookFlag() {
        return updateBookFlag;
    }

    public void setUpdateBookFlag(boolean updateBookFlag) {
        this.updateBookFlag = updateBookFlag;
    }
}

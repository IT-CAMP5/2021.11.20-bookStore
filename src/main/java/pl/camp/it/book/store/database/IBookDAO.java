package pl.camp.it.book.store.database;

import pl.camp.it.book.store.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookDAO {
    List<Book> getBooks();
    Optional<Book> getBookByIsbn(String isbn);
}

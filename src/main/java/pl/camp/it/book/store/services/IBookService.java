package pl.camp.it.book.store.services;

import pl.camp.it.book.store.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<Book> getAllBooks();
    Optional<Book> getBookById(int id);
    Optional<Book> getBookByIsbn(String isbn);
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(int id);
}

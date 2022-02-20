package pl.camp.it.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.model.Book;
import pl.camp.it.book.store.services.IBookService;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    @Autowired
    IBookDAO bookDatabase;

    @Override
    public List<Book> getAllBooks() {
        return this.bookDatabase.getBooks();
    }

    @Override
    public Optional<Book> getBookById(int id) {
        return this.bookDatabase.getBookById(id);
    }

    @Override
    public Optional<Book> getBookByIsbn(String isbn) {
        return this.bookDatabase.getBookByIsbn(isbn);
    }

    @Override
    public void addBook(Book book) {
        this.bookDatabase.addBook(book);
    }

    @Override
    public void updateBook(Book book) {
        this.bookDatabase.updateBook(book);
    }

    @Override
    public void deleteBook(int id) {
        this.bookDatabase.deleteBook(id);
    }
}

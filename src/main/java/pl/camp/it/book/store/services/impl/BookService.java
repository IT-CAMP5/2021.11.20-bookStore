package pl.camp.it.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.model.Book;
import pl.camp.it.book.store.services.IBookService;

import java.util.List;

@Service
public class BookService implements IBookService {

    @Autowired
    IBookDAO bookDatabase;

    @Override
    public List<Book> getAllBooks() {
        return this.bookDatabase.getBooks();
    }
}

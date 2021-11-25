package pl.camp.it.book.store.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.book.store.database.BookDatabase;
import pl.camp.it.book.store.model.Book;
import java.util.List;

@Service
public class BookService {

    @Autowired
    BookDatabase bookDatabase;

    public List<Book> getAllBooks() {
        return this.bookDatabase.getBooks();
    }
}

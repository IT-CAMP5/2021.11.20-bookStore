package pl.camp.it.book.store.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.camp.it.book.store.model.Book;
import pl.camp.it.book.store.model.dto.GetAllBooksResponse;
import pl.camp.it.book.store.services.IBookService;
import java.util.List;

import java.util.Optional;

@RestController
@RequestMapping(value = "/book")
public class BookController {

    @Autowired
    IBookService bookService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Book> getBookById(@PathVariable int id) {
        Optional<Book> bookBox = this.bookService.getBookById(id);
        if(bookBox.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(bookBox.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public GetAllBooksResponse getAllBooks(@RequestParam(required = false) String isbn) {
        GetAllBooksResponse getAllBooksResponse = new GetAllBooksResponse();
        if(isbn == null) {
            /*** WSZYSTKIE KSIAZKI ***/
            List<Book> books = this.bookService.getAllBooks();
            getAllBooksResponse.setBooks(books);
        } else {
            /*** ksiażki pofiltrowane bo isbn ***/
            Optional<Book> bookBox = this.bookService.getBookByIsbn(isbn);
            bookBox.ifPresent(book -> getAllBooksResponse.getBooks().add(book));
        }

        return getAllBooksResponse;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Book addBook(@RequestBody Book book) {
        this.bookService.addBook(book);
        return book;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Book updateBook(@PathVariable int id, @RequestBody Book book) {
        book.setId(id);
        this.bookService.updateBook(book);
        return book;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteBook(@PathVariable int id) {
        this.bookService.deleteBook(id);
    }
}

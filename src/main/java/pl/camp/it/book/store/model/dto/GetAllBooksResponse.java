package pl.camp.it.book.store.model.dto;

import pl.camp.it.book.store.model.Book;

import java.util.ArrayList;
import java.util.List;

public class GetAllBooksResponse {
    List<Book> books = new ArrayList<>();

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}

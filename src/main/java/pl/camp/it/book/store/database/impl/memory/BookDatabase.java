package pl.camp.it.book.store.database.impl.memory;

import org.springframework.stereotype.Repository;
import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.model.Book;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDatabase implements IBookDAO {
    private final List<Book> books = new ArrayList<>();

    public BookDatabase() {
        this.books.add(
                new Book(1,
                        "Cyberbezpieczeństwo w bashu. Jak za pomocą wiersza " +
                        "poleceń prowadzić działania zaczepne i obronne",
                        "Paul Troncone, Carl Albing Ph. D.",
                        55.20,
                        "978-83-283-8196-4",
                        15));

        this.books.add(
                new Book(2,
                        "Python. Automatyzacja zadań. Jak efektywnie pracować z " +
                        "danymi, arkuszami Excela, raportami i e-mailami. Wydanie II",
                        "Jaime Buelta",
                        71.20,
                        "978-83-283-8322-7",
                        10));

        this.books.add(
                new Book(3,
                        "Python 3. Projekty dla początkujących i pasjonatów",
                        "Adam Jurkiewicz",
                        29.95,
                        "978-83-283-7455-3",
                        10));

        this.books.add(
                new Book(4,
                        "Stwórz grę w Unity, a nauczysz się programowania w " +
                        "C#! Pisanie kodu, które sprawia radość. Wydanie V",
                        "Harrison Ferrone",
                        34.50,
                        "978-83-283-8144-5",
                        10));
    }

    @Override
    public List<Book> getBooks() {
        return books;
    }

    @Override
    public Optional<Book> getBookByIsbn(String isbn) {
        for(Book book : this.books) {
            if(book.getIsbn().equals(isbn)) {
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Book> getBookById(int bookId) {
        throw new NotImplementedException();
    }

    @Override
    public void updateBook(Book book) {
        throw new NotImplementedException();
    }

    @Override
    public void addBook(Book book) {
        throw new NotImplementedException();
    }

    @Override
    public void deleteBook(int id) {
        throw new NotImplementedException();
    }
}

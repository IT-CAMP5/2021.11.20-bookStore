package pl.camp.it.book.store.database;

import org.springframework.stereotype.Repository;
import pl.camp.it.book.store.model.Book;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookDatabase {
    private final List<Book> books = new ArrayList<>();

    public BookDatabase() {
        this.books.add(
                new Book("Cyberbezpieczeństwo w bashu. Jak za pomocą wiersza " +
                        "poleceń prowadzić działania zaczepne i obronne",
                        "Paul Troncone, Carl Albing Ph. D.",
                        55.20,
                        "978-83-283-8196-4"));

        this.books.add(
                new Book("Python. Automatyzacja zadań. Jak efektywnie pracować z " +
                        "danymi, arkuszami Excela, raportami i e-mailami. Wydanie II",
                        "Jaime Buelta",
                        71.20,
                        "978-83-283-8322-7"));

        this.books.add(
                new Book("Python 3. Projekty dla początkujących i pasjonatów",
                        "Adam Jurkiewicz",
                        29.95,
                        "978-83-283-7455-3"));

        this.books.add(
                new Book("Stwórz grę w Unity, a nauczysz się programowania w " +
                        "C#! Pisanie kodu, które sprawia radość. Wydanie V",
                        "Harrison Ferrone",
                        34.50,
                        "978-83-283-8144-5"));
    }

    public List<Book> getBooks() {
        return books;
    }
}

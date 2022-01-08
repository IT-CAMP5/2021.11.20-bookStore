package pl.camp.it.book.store.database.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.model.Book;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDAOImpl implements IBookDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Book> getBooks() {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery("FROM pl.camp.it.book.store.model.Book");
        List<Book> books = query.getResultList();
        session.close();
        return books;
    }

    @Override
    public Optional<Book> getBookByIsbn(String isbn) {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery("FROM pl.camp.it.book.store.model.Book WHERE isbn = :isbn");
        query.setParameter("isbn", isbn);
        try {
            Book book = query.getSingleResult();
            session.close();
            return Optional.of(book);
        } catch (NoResultException e) {
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public Optional<Book> getBookById(int bookId) {
        Session session = this.sessionFactory.openSession();
        Query<Book> query = session.createQuery("FROM pl.camp.it.book.store.model.Book WHERE id = :id");
        query.setParameter("id", bookId);
        try {
            Book book = query.getSingleResult();
            session.close();
            return Optional.of(book);
        } catch (NoResultException e) {
            session.close();
            return Optional.empty();
        }
    }

    @Override
    public void updateBook(Book book) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(book);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }
}

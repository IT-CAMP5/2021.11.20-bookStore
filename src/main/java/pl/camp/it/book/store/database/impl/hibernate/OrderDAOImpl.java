package pl.camp.it.book.store.database.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.book.store.database.IOrderDAO;
import pl.camp.it.book.store.database.IUserDAO;
import pl.camp.it.book.store.model.Order;
import pl.camp.it.book.store.model.User;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderDAOImpl implements IOrderDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    IUserDAO userDAO;

    @Override
    public void addOrder(Order order) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(order);
            tx.commit();
        } catch (Exception e) {
            if(tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public List<Order> getOrdersByUserLogin(String login) {
        Optional<User> userBox = this.userDAO.getUserByLogin(login);
        if(!userBox.isPresent()) {
            return new ArrayList<>();
        }

        Session session = this.sessionFactory.openSession();
        Query<Order> query = session.createQuery("FROM pl.camp.it.book.store.model.Order WHERE user_id = :user_id");
        query.setParameter("user_id", userBox.get().getId());
        List<Order> orders = query.getResultList();
        session.close();
        return orders;
    }

    @Override
    public Optional<Order> getOrderById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Order> query = session.createQuery("FROM pl.camp.it.book.store.model.Order WHERE id = :id");
        query.setParameter("id", id);
        try {
            Order order = query.getSingleResult();
            session.close();
            return Optional.of(order);
        } catch (NoResultException e) {
            session.close();
            return Optional.empty();
        }
    }
}

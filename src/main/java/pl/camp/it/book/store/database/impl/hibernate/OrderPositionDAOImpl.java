package pl.camp.it.book.store.database.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.book.store.database.IOrderPositionDAO;
import pl.camp.it.book.store.model.OrderPosition;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderPositionDAOImpl implements IOrderPositionDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addOrderPosition(OrderPosition orderPosition, int orderId) {
        throw new NotImplementedException();
    }

    @Override
    public List<OrderPosition> getOrderPositionsByOrderId(int orderId) {
        throw new NotImplementedException();
    }

    @Override
    public Optional<OrderPosition> getOrderPositionById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<OrderPosition> query = session.createQuery("FROM pl.camp.it.book.store.model.OrderPosition WHERE id = :id");
        query.setParameter("id", id);
        try {
            OrderPosition orderPosition = query.getSingleResult();
            session.close();
            return Optional.of(orderPosition);
        } catch (NoResultException e) {
            session.close();
            return Optional.empty();
        }
    }
}

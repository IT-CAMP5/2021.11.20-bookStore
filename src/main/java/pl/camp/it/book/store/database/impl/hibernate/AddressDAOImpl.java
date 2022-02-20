package pl.camp.it.book.store.database.impl.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.camp.it.book.store.database.IAddressDAO;
import pl.camp.it.book.store.model.Address;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.NoResultException;
import java.util.Optional;

@Repository
public class AddressDAOImpl implements IAddressDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addAddress(Address address) {
        throw new NotImplementedException();
    }

    @Override
    public Optional<Address> getAddressById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Address> query = session.createQuery("FROM pl.camp.it.book.store.model.Address WHERE id = :id");
        query.setParameter("id", id);
        try {
            Address address = query.getSingleResult();
            session.close();
            return Optional.of(address);
        } catch (NoResultException e) {
            session.close();
            return Optional.empty();
        }
    }
}

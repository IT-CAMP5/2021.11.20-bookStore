package pl.camp.it.book.store.databse;

import pl.camp.it.book.store.database.IUserDAO;
import pl.camp.it.book.store.model.User;

import java.util.Optional;

public class UserDAOStub implements IUserDAO {
    @Override
    public Optional<User> getUserByLogin(String login) {
        return Optional.empty();
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void removeUser(User user) {

    }

    @Override
    public Optional<User> getUserById(int id) {
        return Optional.empty();
    }
}

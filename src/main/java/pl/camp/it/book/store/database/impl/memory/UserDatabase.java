package pl.camp.it.book.store.database.impl.memory;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Repository;
import pl.camp.it.book.store.database.IUserDAO;
import pl.camp.it.book.store.model.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDatabase implements IUserDAO {
    private final List<User> users = new ArrayList<>();

    public UserDatabase() {
        this.users.add(new User(
                        1,
                        "Karol",
                        "Kowalski",
                        "admin",
                        DigestUtils.md5Hex("admin"),
                        "kkowalski@gmail.com"));
        this.users.add(new User(
                        2,
                        "Janusz",
                        "Malinowski",
                        "janusz",
                        DigestUtils.md5Hex("janusz"),
                        "jmalinowski@gmail.com"));
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        for(User user : this.users) {
            if(user.getLogin().equals(login)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public void addUser(User user) {
        this.users.add(user);
    }

    @Override
    public void removeUser(User user) {
        throw new NotImplementedException();
    }

    @Override
    public Optional<User> getUserById(int id) {
        throw new NotImplementedException();
    }
}
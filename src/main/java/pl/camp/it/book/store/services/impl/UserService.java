package pl.camp.it.book.store.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.book.store.database.IUserDAO;
import pl.camp.it.book.store.model.User;
import pl.camp.it.book.store.services.IUserService;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    IUserDAO userDAO;

    @Override
    public Optional<User> getUserById(int id) {
        return this.userDAO.getUserById(id);
    }
}

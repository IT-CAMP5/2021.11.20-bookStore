package pl.camp.it.book.store.services;

import pl.camp.it.book.store.model.User;

import java.util.Optional;

public interface IUserService {
    Optional<User> getUserById(int id);
}

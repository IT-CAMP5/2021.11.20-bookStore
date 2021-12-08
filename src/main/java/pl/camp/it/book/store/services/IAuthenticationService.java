package pl.camp.it.book.store.services;

import pl.camp.it.book.store.model.view.RegisterUser;

public interface IAuthenticationService {
    void authenticate(String login, String password);
    void logout();
    boolean register(RegisterUser registerUser);
}

package pl.camp.it.book.store.services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import pl.camp.it.book.store.database.IUserDAO;
import pl.camp.it.book.store.model.User;
import pl.camp.it.book.store.model.view.RegisterUser;
import pl.camp.it.book.store.services.IAuthenticationService;
import pl.camp.it.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

public class AuthenticationService2 implements IAuthenticationService {

    @Autowired
    IUserDAO userDatabase;

    @Resource
    SessionObject sessionObject;

    @Override
    public void authenticate(String login, String password) {
        Optional<User> userBox = this.userDatabase.getUserByLogin(login);
        if(userBox.isPresent() && userBox.get().getPassword().equals(DigestUtils.md5Hex(password))) {
            this.sessionObject.setUser(userBox.get());
        }
    }

    @Override
    public void logout() {
        this.sessionObject.setUser(null);
    }

    @Override
    public boolean register(RegisterUser registerUser) {
        if(!this.userDatabase.getUserByLogin(registerUser.getLogin()).isPresent()) {
            registerUser.setPassword(DigestUtils.md5Hex(registerUser.getPassword()));
            this.userDatabase.addUser(registerUser);
            return true;
        }
        return false;
    }
}

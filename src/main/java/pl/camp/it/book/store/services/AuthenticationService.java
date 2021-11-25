package pl.camp.it.book.store.services;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.book.store.database.UserDatabase;
import pl.camp.it.book.store.model.User;
import pl.camp.it.book.store.model.view.RegisterUser;
import pl.camp.it.book.store.session.SessionObject;

import javax.annotation.Resource;

@Service
public class AuthenticationService {

    @Autowired
    UserDatabase userDatabase;

    @Resource
    SessionObject sessionObject;

    public void authenticate(String login, String password) {
        User user = this.userDatabase.getUserByLogin(login);
        if(user != null && user.getPassword().equals(DigestUtils.md5Hex(password))) {
            this.sessionObject.setLogged(true);
        }
    }

    public void logout() {
        this.sessionObject.setLogged(false);
    }

    public boolean register(RegisterUser registerUser) {
        if(this.userDatabase.getUserByLogin(registerUser.getLogin()) == null) {
            registerUser.setPassword(DigestUtils.md5Hex(registerUser.getPassword()));
            this.userDatabase.addUser(registerUser);
            return true;
        }

        return false;
    }
}

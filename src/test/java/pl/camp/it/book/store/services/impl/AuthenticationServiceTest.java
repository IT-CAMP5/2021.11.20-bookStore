package pl.camp.it.book.store.services.impl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.camp.it.book.store.configuration.AppConfiguration;
import pl.camp.it.book.store.database.IUserDAO;
import pl.camp.it.book.store.model.User;
import pl.camp.it.book.store.model.view.RegisterUser;
import pl.camp.it.book.store.services.IAuthenticationService;
import pl.camp.it.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfiguration.class})
public class AuthenticationServiceTest {

    @Autowired
    IAuthenticationService authenticationService;

    @Resource
    SessionObject sessionObject;

    @Autowired
    IUserDAO userDAO;

    @After
    public void cleanDatabase() {
        Optional<User> userBox = this.userDAO.getUserByLogin("janusz33");
        userBox.ifPresent(user -> this.userDAO.removeUser(user));
    }

    @Test
    public void correctAuthenticationTest() {
        String login = "admin";
        String password = "admin";

        this.authenticationService.authenticate(login, password);

        Assert.assertTrue(this.sessionObject.isLogged());
    }

    @Test
    public void incorrectAuthenticationTest() {
        String login = "admin";
        String password = "admin123";

        this.authenticationService.authenticate(login, password);

        Assert.assertFalse(this.sessionObject.isLogged());
    }

    @Test
    public void logoutUserTest() {
        correctAuthenticationTest();

        this.authenticationService.logout();

        Assert.assertFalse(this.sessionObject.isLogged());
    }

    @Test
    public void notLoggedUserLogoutTest() {
        this.sessionObject.setUser(null);
        this.authenticationService.logout();
        Assert.assertFalse(this.sessionObject.isLogged());
    }

    @Test
    public void registerUniqueUserTest() {
        RegisterUser registerUser = generateRegisterUser("janusz33");
        boolean result = this.authenticationService.register(registerUser);
        Assert.assertTrue(result);
    }

    @Test
    public void registerNotUniqueUserTest() {
        RegisterUser registerUser = generateRegisterUser("admin");
        boolean result = this.authenticationService.register(registerUser);
        Assert.assertFalse(result);
    }

    private RegisterUser generateRegisterUser(String login) {
        RegisterUser registerUser = new RegisterUser();
        registerUser.setLogin(login);
        registerUser.setPassword("asd");
        registerUser.setPassword2("asd");
        registerUser.setMail("asdf@asdf.pl");
        registerUser.setName("Janusz");
        registerUser.setSurname("Kowalski");

        return registerUser;
    }
}

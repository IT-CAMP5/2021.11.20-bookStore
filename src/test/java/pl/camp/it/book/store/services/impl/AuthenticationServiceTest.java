package pl.camp.it.book.store.services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.camp.it.book.store.configuration.AppConfiguration;
import pl.camp.it.book.store.configuration.TestConfiguration;
import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.database.IOrderDAO;
import pl.camp.it.book.store.database.IUserDAO;
import pl.camp.it.book.store.model.User;
import pl.camp.it.book.store.model.view.RegisterUser;
import pl.camp.it.book.store.services.IAuthenticationService;
import pl.camp.it.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestConfiguration.class})
public class AuthenticationServiceTest {

    @Autowired
    IAuthenticationService authenticationService;

    @Resource
    SessionObject sessionObject;

    /*@Autowired
    IUserDAO userDAO;*/

    @MockBean
    IUserDAO userDAO;

    @MockBean
    IBookDAO bookDAO;

    @MockBean
    IOrderDAO orderDAO;

    /*@After
    public void cleanDatabase() {
        Optional<User> userBox = this.userDAO.getUserByLogin("janusz33");
        userBox.ifPresent(user -> this.userDAO.removeUser(user));
    }*/

    @Test
    public void correctAuthenticationTest() {
        String login = "admin";
        String password = "admin";

        Mockito.when(this.userDAO.getUserByLogin("admin")).thenReturn(generateUser(login, password));

        this.authenticationService.authenticate(login, password);

        Assert.assertTrue(this.sessionObject.isLogged());
    }

    @Test
    public void incorrectAuthenticationTest() {
        String login = "admin";
        String password = "admin";
        Mockito.when(this.userDAO.getUserByLogin("admin")).thenReturn(generateUser(login, "jakiesinnehaslo"));

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

        Mockito.when(this.userDAO.getUserByLogin("janusz33")).thenReturn(Optional.empty());

        boolean result = this.authenticationService.register(registerUser);
        Assert.assertTrue(result);
        Mockito.verify(this.userDAO, Mockito.times(1)).addUser(Mockito.any());
    }

    @Test
    public void registerNotUniqueUserTest() {
        RegisterUser registerUser = generateRegisterUser("admin");
        Mockito.when(this.userDAO.getUserByLogin("admin")).thenReturn(Optional.of(new User()));

        boolean result = this.authenticationService.register(registerUser);
        Assert.assertFalse(result);
        Mockito.verify(this.userDAO, Mockito.never()).addUser(Mockito.any());
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

    private Optional<User> generateUser(String login, String pass) {
        User user = new User();
        user.setId(1);
        user.setMail("sdf@xdf.pl");
        user.setName("Fdfsgsdf");
        user.setSurname("Fsadfgsdrf");
        user.setLogin(login);
        user.setPassword(DigestUtils.md5Hex(pass));

        return Optional.of(user);
    }
}

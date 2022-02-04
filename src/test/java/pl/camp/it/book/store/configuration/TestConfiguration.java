package pl.camp.it.book.store.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pl.camp.it.book.store.database.IBookDAO;
import pl.camp.it.book.store.database.IOrderDAO;
import pl.camp.it.book.store.database.IUserDAO;
import pl.camp.it.book.store.databse.BookDAOStub;
import pl.camp.it.book.store.databse.OrderDAOStub;
import pl.camp.it.book.store.databse.UserDAOStub;

@Configuration
@ComponentScan(basePackages = {
        "pl.camp.it.book.store.controllers",
        "pl.camp.it.book.store.services",
        "pl.camp.it.book.store.session"
})
public class TestConfiguration {
    @Bean
    public IBookDAO bookDAO() {
        return new BookDAOStub();
    }

    @Bean
    public IUserDAO userDAO() {
        return new UserDAOStub();
    }

    @Bean
    public IOrderDAO orderDAO() {
        return new OrderDAOStub();
    }
}

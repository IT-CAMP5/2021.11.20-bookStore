package pl.camp.it.book.store.services.impl;

import org.junit.Assert;
import org.junit.Test;
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
import pl.camp.it.book.store.model.Book;
import pl.camp.it.book.store.services.ICartService;
import pl.camp.it.book.store.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestConfiguration.class})
public class CartServiceTest {

    @Autowired
    ICartService cartService;

    @Resource
    SessionObject sessionObject;

    @MockBean
    IUserDAO userDAO;

    @MockBean
    IOrderDAO orderDAO;

    @MockBean
    IBookDAO bookDAO;

    @Test
    public void addToCartIncorrectIsbnTest() {
        String isbn = "97543-8345-2345-85345-345";
        Mockito.when(bookDAO.getBookByIsbn(isbn)).thenReturn(Optional.empty());

        this.sessionObject.getCart().getPositions().clear();

        Assert.assertEquals(0, this.sessionObject.getCart().getPositions().size());

        this.cartService.addToCart(isbn);

        Assert.assertEquals(0, this.sessionObject.getCart().getPositions().size());
    }

    @Test
    public void addBookToCart() {
        String isbn = "978-83-283-8196-4";
        Mockito.when(this.bookDAO.getBookByIsbn(isbn)).thenReturn(generateBook(isbn));

        this.sessionObject.getCart().getPositions().clear();

        Assert.assertEquals(0, this.sessionObject.getCart().getPositions().size());

        this.cartService.addToCart(isbn);

        Assert.assertEquals(1, this.sessionObject.getCart().getPositions().size());

        this.cartService.addToCart(isbn);

        Assert.assertEquals(1, this.sessionObject.getCart().getPositions().size());

        int positionQuantity = this.sessionObject.getCart().getPositions().get(0).getPositionQuantity();
        Assert.assertEquals(2, positionQuantity);
    }

    private Optional<Book> generateBook(String isbn) {
        Book book = new Book();
        book.setId(1);
        book.setTitle("Sfsdgsdfg");
        book.setAuthor("Ssdfg Dasdfa");
        book.setIsbn(isbn);
        book.setQuantity(10);
        book.setPrice(23.23);

        return Optional.of(book);
    }
}

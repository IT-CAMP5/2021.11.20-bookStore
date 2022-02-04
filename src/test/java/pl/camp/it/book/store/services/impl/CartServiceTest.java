package pl.camp.it.book.store.services.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.camp.it.book.store.configuration.AppConfiguration;
import pl.camp.it.book.store.services.ICartService;
import pl.camp.it.book.store.session.SessionObject;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfiguration.class})
public class CartServiceTest {

    @Autowired
    ICartService cartService;

    @Resource
    SessionObject sessionObject;

    @Test
    public void addToCartIncorrectIsbnTest() {
        String isbn = "97543-8345-2345-85345-345";

        this.sessionObject.getCart().getPositions().clear();

        Assert.assertEquals(0, this.sessionObject.getCart().getPositions().size());

        this.cartService.addToCart(isbn);

        Assert.assertEquals(0, this.sessionObject.getCart().getPositions().size());
    }

    @Test
    public void addBookToCart() {
        String isbn = "978-83-283-8196-4";

        this.sessionObject.getCart().getPositions().clear();

        Assert.assertEquals(0, this.sessionObject.getCart().getPositions().size());

        this.cartService.addToCart(isbn);

        Assert.assertEquals(1, this.sessionObject.getCart().getPositions().size());

        this.cartService.addToCart(isbn);

        Assert.assertEquals(1, this.sessionObject.getCart().getPositions().size());

        int positionQuantity = this.sessionObject.getCart().getPositions().get(0).getPositionQuantity();
        Assert.assertEquals(2, positionQuantity);
    }
}

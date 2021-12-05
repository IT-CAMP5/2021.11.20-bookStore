package pl.camp.it.book.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.camp.it.book.store.services.CartService;
import pl.camp.it.book.store.session.SessionObject;

import javax.annotation.Resource;

@Controller
public class CartController {

    @Autowired
    CartService cartService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/cart/add/{isbn}", method = RequestMethod.GET)
    public String addToCart(@PathVariable String isbn) {
        if(!this.sessionObject.isLogged()) {
            return "redirect:/main";
        }
        this.cartService.addToCart(isbn);
        return "redirect:/main";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String cart(Model model) {
        if(!this.sessionObject.isLogged()) {
            return "redirect:/main";
        }
        model.addAttribute("logged", this.sessionObject.isLogged());
        model.addAttribute("cart", this.sessionObject.getCart());
        model.addAttribute("sum", this.sessionObject.getCart().calculateSum());
        return "cart";
    }
}

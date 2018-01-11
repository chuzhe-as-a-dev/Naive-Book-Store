package me.chuzhe.bookstore.web.customer;

import me.chuzhe.bookstore.domain.entity.CartItem;
import me.chuzhe.bookstore.service.CartService;
import me.chuzhe.bookstore.web.dto.CartItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Created by tang on 2017/5/24.
 */
@RestController
@RequestMapping("/user/cart")
public class UserCartRestController {
    private final CartService cartService;

    @Autowired
    public UserCartRestController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<CartItem> getAllItems(Principal principal) {
        return cartService.getAllItems(principal.getName());
    }

    @GetMapping("/size")
    public int getCartSize(Principal principal) {
        return cartService.getCartSize(principal.getName());
    }

    @PostMapping
    public boolean addItem(@Valid CartItemDto item, BindingResult result, Principal principal) {
        return !result.hasErrors() && cartService.addItem(item, principal.getName());
    }

    @PutMapping
    public boolean updateItem(@Valid CartItemDto item, BindingResult result, Principal principal) {
        return !result.hasErrors() && cartService.updateItem(item, principal.getName());
    }

    @DeleteMapping("/{cartItemId}")
    public boolean deleteItem(@PathVariable int cartItemId, Principal principal) {
        return cartService.deleteItem(cartItemId, principal.getName());
    }
}

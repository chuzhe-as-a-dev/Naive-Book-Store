package me.chuzhe.bookstore.service;

import me.chuzhe.bookstore.domain.entity.CartItem;
import me.chuzhe.bookstore.web.dto.CartItemDto;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tang on 2017/5/22.
 */
public interface CartService {

    List<CartItem> getAllItems(String username);

    int getCartSize(String username);

    boolean addItem(CartItemDto cartItemDto, String issuedBy);

    boolean updateItem(CartItemDto cartItemDto, String issuedBy);

    boolean deleteItem(int cartItemId, String issuedBy);
}

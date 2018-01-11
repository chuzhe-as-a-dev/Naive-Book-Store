package me.chuzhe.bookstore.service.impl;

import me.chuzhe.bookstore.domain.entity.Book;
import me.chuzhe.bookstore.domain.entity.CartItem;
import me.chuzhe.bookstore.domain.dao.BookRepository;
import me.chuzhe.bookstore.domain.dao.CartItemRepository;
import me.chuzhe.bookstore.domain.dao.UserRepository;
import me.chuzhe.bookstore.service.CartService;
import me.chuzhe.bookstore.web.dto.CartItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tang on 2017/6/23.
 */
@Service
public class CartServiceImpl implements CartService {
    private final UserRepository userRepository;

    private final CartItemRepository cartItemRepository;

    private final BookRepository bookRepository;

    @Autowired
    public CartServiceImpl(UserRepository userRepository, CartItemRepository cartItemRepository, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.cartItemRepository = cartItemRepository;
        this.bookRepository = bookRepository;
    }

    public List<CartItem> getAllItems(String username) {
        int userId = userRepository.findByUsername(username).getUserId();
        return cartItemRepository.findAllByUserId(userId);
    }

    public int getCartSize(String username) {
        int userId = userRepository.findByUsername(username).getUserId();
        return cartItemRepository.countByUserId(userId);
    }

    public boolean addItem(CartItemDto cartItemDto, String issuedBy) {
        // get user id
        int userId = userRepository.findByUsername(issuedBy).getUserId();

        // load book
        Book book = bookRepository.findOne(cartItemDto.getBookId());
        if (book == null) {
            return false;
        }

        // check if this book is already in cart
        CartItem cartItem = cartItemRepository.findByUserIdAndBookByBookId(userId, book);
        if (cartItem == null) {
            // add item to shopping cart
            cartItem = new CartItem();
            cartItem.setBookByBookId(book);
            cartItem.setQuantity(cartItemDto.getQuantity());
            cartItem.setUserId(userId);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + cartItemDto.getQuantity());
        }

        cartItemRepository.save(cartItem);

        return true;
    }

    public boolean updateItem(CartItemDto cartItemDto, String issuedBy) {
        // get user id
        int userId = userRepository.findByUsername(issuedBy).getUserId();

        // load book
        Book book = bookRepository.findOne(cartItemDto.getBookId());
        if (book == null) {
            return false;
        }

        // assume user will not try to change non-existing cart item
        CartItem cartItem = cartItemRepository.findByUserIdAndBookByBookId(userId, book);
        cartItem.setQuantity(cartItemDto.getQuantity());
        cartItemRepository.save(cartItem);

        return true;
    }


    public boolean deleteItem(int cartItemId, String issuedBy) {
        // get user id
        int userId = userRepository.findByUsername(issuedBy).getUserId();

        // check if that cart item already exists
        CartItem cartItem = cartItemRepository.findByCartItemIdAndUserId(cartItemId, userId);
        if (cartItem == null) {
            return false;
        }
        cartItemRepository.delete(cartItem);

        return true;
    }
}

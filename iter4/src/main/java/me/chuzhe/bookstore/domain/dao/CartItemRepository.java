package me.chuzhe.bookstore.domain.dao;

import me.chuzhe.bookstore.domain.entity.Book;
import me.chuzhe.bookstore.domain.entity.CartItem;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by tang on 2017/5/21.
 */
public interface CartItemRepository extends CrudRepository<CartItem, Integer> {
    List<CartItem> findAllByUserId(int userId);

    CartItem findByUserIdAndBookByBookId(int userId, Book book);

    CartItem findByCartItemIdAndUserId(int cartItemId, int userId);

    int countByUserId(int userId);

    void deleteByUserId(int userId);
}

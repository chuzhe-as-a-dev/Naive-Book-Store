package me.chuzhe.bookstore.model.dao;

/**
 * Created by tang on 2017/5/20.
 */

import java.util.List;

import me.chuzhe.bookstore.model.entity.Book;

public interface BookDao {

    Integer save(Book book);

    void delete(Book book);

    void update(Book book);

    Book getBookById(int id);

    List<Book> getAllBooks();

}

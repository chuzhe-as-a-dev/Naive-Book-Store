package me.chuzhe.bookstore.service;

/**
 * Created by tang on 2017/5/20.
 */

import me.chuzhe.bookstore.model.entity.Book;

import java.util.List;

public interface ManagementService {
    List<Book> getAllBooks();
}

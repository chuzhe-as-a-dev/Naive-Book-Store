package me.chuzhe.bookstore.service.impl;

import me.chuzhe.bookstore.model.dao.BookDao;
import me.chuzhe.bookstore.model.entity.Book;
import me.chuzhe.bookstore.service.ManagementService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by tang on 2017/5/20.
 */
public class ManagementServiceImpl implements ManagementService {
    @Autowired
    private BookDao bookDao;

    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}

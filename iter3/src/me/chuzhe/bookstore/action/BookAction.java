package me.chuzhe.bookstore.action;

import com.opensymphony.xwork2.ActionSupport;
import me.chuzhe.bookstore.model.dao.BookDao;
import me.chuzhe.bookstore.model.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by tang on 2017/5/20.
 */
@Component
public class BookAction extends ActionSupport {
    @Autowired
    private BookDao bookDao;

    private List<Book> books;

    public String listAll() throws Exception {
        books = bookDao.getAllBooks();
        return SUCCESS;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }
}

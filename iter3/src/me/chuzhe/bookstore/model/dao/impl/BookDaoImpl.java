package me.chuzhe.bookstore.model.dao.impl;

import me.chuzhe.bookstore.model.dao.BookDao;
import me.chuzhe.bookstore.model.entity.Book;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by tang on 2017/5/20.
 */
@Component
public class BookDaoImpl implements BookDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Integer save(Book book) {
        return (Integer) sessionFactory.getCurrentSession().save(book);
    }

    public void delete(Book book) {
        sessionFactory.getCurrentSession().delete(book);
    }

    public void update(Book book) {
        sessionFactory.getCurrentSession().merge(book);
    }

    public Book getBookById(int id) {
        return sessionFactory.getCurrentSession().load(Book.class, id);
    }

    public List<Book> getAllBooks() {
        @SuppressWarnings("unchecked")
        List<Book> books = (List<Book>) sessionFactory.getCurrentSession().createQuery("from Book").list();
        return books;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}

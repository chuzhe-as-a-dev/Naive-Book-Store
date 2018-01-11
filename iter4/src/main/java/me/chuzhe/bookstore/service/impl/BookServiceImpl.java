package me.chuzhe.bookstore.service.impl;

import me.chuzhe.bookstore.domain.entity.Book;
import me.chuzhe.bookstore.domain.entity.BookComment;
import me.chuzhe.bookstore.domain.entity.User;
import me.chuzhe.bookstore.domain.dao.BookCommentRepository;
import me.chuzhe.bookstore.domain.dao.BookRepository;
import me.chuzhe.bookstore.domain.dao.UserRepository;
import me.chuzhe.bookstore.service.BookService;
import me.chuzhe.bookstore.web.dto.BookCommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * Created by tang on 2017/6/23.
 */
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    private final BookCommentRepository bookCommentRepository;

    private final UserRepository userRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookCommentRepository bookCommentRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.bookCommentRepository = bookCommentRepository;
        this.userRepository = userRepository;
    }

    // about books
    public Book getBookById(int book_id) {
        return bookRepository.findOne(book_id);
    }

    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Page<Book> getSellingBooks(int page, int pageSize) {
        return bookRepository.findAllByForSaleIsTrue(new PageRequest(page, pageSize, new Sort("bookId")));
    }

    public Page<Book> getSearchResult(String keyword, int page, int pageSize) {
        return bookRepository.findByBookTitleContaining(keyword, new PageRequest(page, pageSize, new Sort("bookId")));
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }

    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBook(int book_id) {
        bookRepository.delete(book_id);
    }

    // about book comments
    public List<BookComment> getCommentsByBookId(int bookId) {
        return bookCommentRepository.findAllByBookId(bookId, new Sort("bookCommentId"));
    }

    public List<BookComment> getCommentsByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return bookCommentRepository.findAllByUserByUserId(user);
    }

    public BookComment getCommentByBookIdAndUsername(int bookId, String username) {
        User user = userRepository.findByUsername(username);
        return bookCommentRepository.findByUserByUserIdAndBookId(user, bookId);
    }

    public boolean updateBookCommentByUser(BookCommentDto bookCommentDto, String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }

        BookComment bookComment = bookCommentRepository.findByUserByUserIdAndBookId(user, bookCommentDto.getBookId());

        if (bookComment == null) {
            bookComment = new BookComment();
            bookComment.setBookId(bookCommentDto.getBookId());
            bookComment.setTitle(bookCommentDto.getTitle());
            bookComment.setUserByUserId(user);
            bookComment.setContent(bookCommentDto.getContent());
            bookComment.setCommentTime(new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        } else {
            bookComment.setTitle(bookCommentDto.getTitle());
            bookComment.setContent(bookCommentDto.getContent());
        }

        bookCommentRepository.save(bookComment);

        return true;
    }

    public boolean deleteBookCommentByUser(String username, int bookId) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }

        BookComment bookComment = bookCommentRepository.findByUserByUserIdAndBookId(user, bookId);
        if (bookComment == null) {
            return false;
        }

        bookCommentRepository.delete(bookComment);
        return true;
    }
}

package me.chuzhe.bookstore.service;

import me.chuzhe.bookstore.domain.entity.Book;
import me.chuzhe.bookstore.domain.entity.BookComment;
import me.chuzhe.bookstore.web.dto.BookCommentDto;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by tang on 2017/5/21.
 */
public interface BookService {

    // about books
    Book getBookById(int book_id);

    Iterable<Book> getAllBooks();

    Page<Book> getSellingBooks(int page, int pageSize);

    Page<Book> getSearchResult(String keyword, int page, int pageSize);

    void addBook(Book book);

    void updateBook(Book book);

    void deleteBook(int book_id);

    // about book comments
    List<BookComment> getCommentsByBookId(int bookId);

    List<BookComment> getCommentsByUsername(String username);

    BookComment getCommentByBookIdAndUsername(int bookId, String username);

    boolean updateBookCommentByUser(BookCommentDto bookCommentDto, String username);

    boolean deleteBookCommentByUser(String username, int bookId);
}


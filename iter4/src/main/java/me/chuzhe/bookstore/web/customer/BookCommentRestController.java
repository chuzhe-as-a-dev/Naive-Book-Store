package me.chuzhe.bookstore.web.customer;

import com.fasterxml.jackson.annotation.JsonView;
import me.chuzhe.bookstore.domain.JacksonView;
import me.chuzhe.bookstore.domain.entity.BookComment;
import me.chuzhe.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tang on 2017/5/24.
 */
@RestController
@RequestMapping("/book/comment")
public class BookCommentRestController {

    final private BookService bookService;

    @Autowired
    public BookCommentRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @JsonView(JacksonView.Comment.class)
    @GetMapping("/{bookId}")
    public List<BookComment> getCommentByBookId(@PathVariable int bookId) {
        return bookService.getCommentsByBookId(bookId);
    }
}

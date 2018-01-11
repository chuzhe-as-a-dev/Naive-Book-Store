package me.chuzhe.bookstore.web.customer;

import com.fasterxml.jackson.annotation.JsonView;
import me.chuzhe.bookstore.domain.JacksonView;
import me.chuzhe.bookstore.domain.entity.BookComment;
import me.chuzhe.bookstore.service.BookService;
import me.chuzhe.bookstore.web.dto.BookCommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

/**
 * Created by tang on 2017/6/2.
 */
@RestController
@RequestMapping("/user/comment")
public class UserCommentRestController {
    final private BookService bookService;

    @Autowired
    public UserCommentRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @JsonView(JacksonView.Comment.class)
    @GetMapping()
    public List<BookComment> getCommentByUserId(Principal principal) {
        return bookService.getCommentsByUsername(principal.getName());
    }

    @JsonView(JacksonView.Comment.class)
    @GetMapping("/{bookId}")
    public BookComment getCommentByUserIdAndBookId(@PathVariable int bookId, Principal principal) {
        return bookService.getCommentByBookIdAndUsername(bookId, principal.getName());
    }

    @PutMapping()
    public boolean updateComment(@Valid BookCommentDto comment, BindingResult result, Principal principal) {
        return !result.hasErrors() && bookService.updateBookCommentByUser(comment, principal.getName());
    }

    @DeleteMapping("/{bookId}")
    boolean deleteComment(@PathVariable int bookId, Principal principal) {
        return bookService.deleteBookCommentByUser(principal.getName(), bookId);
    }
}

package me.chuzhe.bookstore.web.customer;

import me.chuzhe.bookstore.domain.entity.Book;
import me.chuzhe.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Created by tang on 2017/5/24.
 */
@RestController
@RequestMapping("/book")
public class BookRestController {

    private final BookService bookService;

    @Autowired
    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public Page<Book> listAllBooks(@RequestParam int page, @RequestParam(defaultValue = "4") int pageSize,
                                   @RequestParam(required = false) String q) {

        if (q == null) {
            return bookService.getSellingBooks(page, pageSize);
        } else {
            return bookService.getSearchResult(q, page, pageSize);
        }
    }

    @GetMapping("/{bookId}")
    public Book getBookDetail(@PathVariable int bookId) {
        return bookService.getBookById(bookId);
    }
}

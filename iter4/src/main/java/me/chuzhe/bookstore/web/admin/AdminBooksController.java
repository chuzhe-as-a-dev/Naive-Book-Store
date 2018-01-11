package me.chuzhe.bookstore.web.admin;

import me.chuzhe.bookstore.domain.entity.Book;
import me.chuzhe.bookstore.service.BookService;
import me.chuzhe.bookstore.web.dto.admin.BookCreateDto;
import me.chuzhe.bookstore.web.dto.admin.BookUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by tang on 2017/5/21.
 */
@Controller
@RequestMapping(path = "/admin/books")
public class AdminBooksController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ModelAndView listAllBooks() {
        Iterable<Book> books = bookService.getAllBooks();

        return new ModelAndView("admin/books", "books", books);
    }

    @PostMapping(path = "/add")
    public String addBook(@Valid BookCreateDto bookCreateDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/admin/books";
        }

        Book book = new Book();

        book.setIsbn(bookCreateDto.getIsbn());
        book.setBookTitle(bookCreateDto.getBookTitle());
        book.setAuthor(bookCreateDto.getAuthor());
        book.setPrice(bookCreateDto.getPrice());
        book.setStock(bookCreateDto.getStock());
        book.setCoverFilename(bookCreateDto.getCoverFilename());
        book.setDescription(bookCreateDto.getDescription());
        book.setForSale(bookCreateDto.isForSale());
        book.setCategory(bookCreateDto.getCategory());

        bookService.addBook(book);

        return "redirect:/admin/books";
    }

    @PostMapping(path = "/update")
    public String updateBook(@Valid BookUpdateDto bookUpdateDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/admin/books";
        }

        Book book = bookService.getBookById(bookUpdateDto.getBookId());

        book.setIsbn(bookUpdateDto.getIsbn());
        book.setBookTitle(bookUpdateDto.getBookTitle());
        book.setAuthor(bookUpdateDto.getAuthor());
        book.setPrice(bookUpdateDto.getPrice());
        book.setStock(bookUpdateDto.getStock());
        book.setCoverFilename(bookUpdateDto.getCoverFilename());
        book.setDescription(bookUpdateDto.getDescription());
        book.setForSale(bookUpdateDto.isForSale());
        book.setCategory(bookUpdateDto.getCategory());

        bookService.updateBook(book);

        return "redirect:/admin/books";
    }

    @PostMapping(path = "/delete")
    public String deleteBook(@RequestParam int bookId) {
        bookService.deleteBook(bookId);

        return "redirect:/admin/books";
    }
}

package bookstore.book;

import bookstore.Helper;
import bookstore.database.BookEntity;
import bookstore.database.DatabaseAccess;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tang on 2017/4/10.
 */
@WebServlet("/submit_new_book")
public class CreateBookServlet extends HttpServlet {
    public void init() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookName = request.getParameter("bookName");
        String author = request.getParameter("author");
        String isbn = request.getParameter("isbn");
        String price = request.getParameter("price");
        String stock = request.getParameter("stock");
        String description = request.getParameter("description");

        Map<String, Boolean> json_obj = new HashMap<>();
        Session hibernate_session = DatabaseAccess.getSession();
        try {
            hibernate_session.beginTransaction();
            BookEntity book = new BookEntity();
            book.setBookName(bookName);
            book.setAuthor(author);
            book.setIsbn(isbn);
            book.setPrice(new BigDecimal(Float.parseFloat(price)));
            book.setStock(Integer.parseInt(stock));
            book.setCoverFilename("NFJNFInedan.jpg");
            book.setDescription(description);
            hibernate_session.save(book);
            hibernate_session.getTransaction().commit();
            json_obj.put("status", true);
        } catch (Exception e) {
            Helper.goError(response);
            json_obj.put("status", false);
        } finally {
            hibernate_session.close();
        }

        Helper.responseJson(json_obj, response);
    }
}

package bookstore.book;

import bookstore.Helper;
import bookstore.database.BookEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tang on 2017/4/10.
 */
@WebServlet("/remove_book")
public class RemoveBookServlet extends HttpServlet {
    public void init() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // parse book id
        int bookId;
        try {
            bookId = Integer.parseInt(request.getParameter("bookId"));
        } catch (Exception e) {
            Helper.goError(response);
            return;
        }

        // remove book
        if (!Helper.removeBook(bookId)) {
            Helper.goError(response);
        }
    }
}

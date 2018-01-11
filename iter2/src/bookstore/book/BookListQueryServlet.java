package bookstore.book;

import bookstore.Helper;
import bookstore.database.DatabaseAccess;
import bookstore.database.HibernateProxyTypeAdapter;
import com.google.gson.GsonBuilder;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tang on 2017/4/9.
 */
@WebServlet("/book_list")
public class BookListQueryServlet extends HttpServlet {
    public void init() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session hibernate_session = DatabaseAccess.getSession();
        String hql = "from BookEntity where bookId != 0";
        Query query = hibernate_session.createQuery(hql);
        Helper.responseJson(query.list(), response);
        hibernate_session.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

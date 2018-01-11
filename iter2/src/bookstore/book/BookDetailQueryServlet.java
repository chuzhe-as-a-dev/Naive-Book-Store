package bookstore.book;

import bookstore.database.BookEntity;
import bookstore.database.CommentEntity;
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
import java.util.List;
import java.util.Set;

/**
 * Created by tang on 2017/4/9.
 */
@WebServlet("/book_detail")
public class BookDetailQueryServlet extends HttpServlet {
    class BookDetailJsonRespoce {
        public boolean status;
        public BookEntity book;
        public Set<CommentEntity> comments;
        public BookDetailJsonRespoce() {
            status = false;
        }
    }

    public void init() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int book_id = Integer.parseInt(request.getParameter("book_id"));

        // find the book
        Session hibernate_session = DatabaseAccess.getSession();
        //language=HQL
        String hql = "from BookEntity where bookId = ?";
        Query query = hibernate_session.createQuery(hql);
        query.setParameter(0, book_id);

        BookDetailJsonRespoce detail = new BookDetailJsonRespoce();
        List result = query.list();

        if (result.size() == 1) {
            detail.status = true;
            detail.book = (BookEntity) result.get(0);
            detail.comments = detail.book.getComments();
        }

        String json = new GsonBuilder()
                .registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
                .create().toJson(detail);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
        hibernate_session.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

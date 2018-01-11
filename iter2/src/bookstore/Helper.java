package bookstore;

import bookstore.database.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hibernate.Session;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Created by tang on 2017/4/9.
 */
public class Helper {
    private static Gson gson = new GsonBuilder()
            .registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY)
            .setDateFormat("yyyy-MM-dd")
            .create();

    public static void responseJson(Object obj, HttpServletResponse response) throws IOException {
        String json = gson.toJson(obj);
        System.out.println(json);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public static void goError(ServletResponse response) throws IOException {
        ((HttpServletResponse) response).sendRedirect("/error.html");
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static boolean isFloat(String s) {
        try {
            Float.parseFloat(s);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static BookEntity getBook(int bookId) {
        Session hibernate_session = DatabaseAccess.getSession();
        BookEntity book = hibernate_session.load(BookEntity.class, bookId);
        hibernate_session.close();
        return book;
    }

    public static boolean removeBook(int bookId) {
        // get book
        Session hibernate_session = DatabaseAccess.getSession();
        hibernate_session.beginTransaction();
        BookEntity book = hibernate_session.load(BookEntity.class, bookId);
        hibernate_session.delete(book);
        hibernate_session.getTransaction().commit();
        hibernate_session.close();
        return true;
    }

    public static boolean removeUser(int userId) {
        // get user
        Session hibernate_session = DatabaseAccess.getSession();
        hibernate_session.beginTransaction();
        UserEntity user = hibernate_session.load(UserEntity.class, userId);
        hibernate_session.delete(user);
        hibernate_session.getTransaction().commit();
        hibernate_session.close();
        return true;
    }

    public static boolean removeOrder(int bookId) {
        // get order
        Session hibernate_session = DatabaseAccess.getSession();
        hibernate_session.beginTransaction();
        OrderEntity order = hibernate_session.load(OrderEntity.class, bookId);
        hibernate_session.delete(order);
        hibernate_session.getTransaction().commit();
        hibernate_session.close();
        return true;
    }
}

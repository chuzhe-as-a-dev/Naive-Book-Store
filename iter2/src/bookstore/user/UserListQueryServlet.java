package bookstore.user;

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
 * Created by tang on 2017/4/11.
 */
@WebServlet("/user_list")
public class UserListQueryServlet extends HttpServlet {
    public void init() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Session hibernate_session = DatabaseAccess.getSession();
        String hql = "from UserEntity";
        Query query = hibernate_session.createQuery(hql);
        Helper.responseJson(query.list(), response);
        hibernate_session.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

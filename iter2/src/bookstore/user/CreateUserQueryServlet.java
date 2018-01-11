package bookstore.user;

import bookstore.Helper;
import bookstore.database.BookEntity;
import bookstore.database.DatabaseAccess;
import bookstore.database.UserEntity;
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
 * Created by tang on 2017/4/11.
 */
@WebServlet("/submit_new_user")
public class CreateUserQueryServlet extends HttpServlet {
    public void init() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String credit = request.getParameter("credit");
        String password = request.getParameter("password");

        Map<String, Boolean> json_obj = new HashMap<>();
        Session hibernate_session = DatabaseAccess.getSession();
        try {
            hibernate_session.beginTransaction();
            UserEntity user = new UserEntity();
            user.setUsername(username);
            user.setEmail(email);
            user.setCredit(new BigDecimal(Float.parseFloat(credit)));
            user.setPassword(password);
            user.setIconFilename("default.jpg");
            hibernate_session.save(user);
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

package bookstore.logging;

import bookstore.database.DatabaseAccess;
import bookstore.database.UserEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 * Created by tang on 2017/4/8.
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.sendRedirect("/");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Session hibernate_session = DatabaseAccess.getSession();
        String hql;
        if (request.getAttribute("by-email") != null) {
            hql = "from UserEntity where email = ?";
        } else {
            hql = "from UserEntity where username = ?";
        }

        // access database
        final Query query = hibernate_session.createQuery(hql);
        query.setParameter(0, request.getParameter("login-identifier"));
        List result = query.list();
        hibernate_session.close();
        if (result.size() != 1) {
            response.sendRedirect("/error.html");  // todo: prompt login error
            return;
        }

        UserEntity user = (UserEntity) result.get(0);
        if (!user.getPassword().equals(request.getParameter("login-password"))) {
            response.sendRedirect("/error.html");  // todo: prompt login error
            return;
        }

        // create cookie and session
        Cookie username = new Cookie("username",user.getUsername());
        username.setMaxAge(24 * 60 * 60);
        response.addCookie(username);

        HttpSession http_session = request.getSession(true);
        http_session.setAttribute("username", user.getUsername());
        http_session.setMaxInactiveInterval(1 * 60 * 60);

        response.sendRedirect("/index.html");
    }
}

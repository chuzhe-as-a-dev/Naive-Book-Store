package bookstore.user;

import bookstore.Helper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by tang on 2017/4/12.
 */
@WebServlet("/remove_user")
public class RemoveUserServlet extends HttpServlet{
    public void init() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // parse book id
        int userId;
        try {
            userId = Integer.parseInt(request.getParameter("userId"));
        } catch (Exception e) {
            Helper.goError(response);
            return;
        }

        // remove book
        if (!Helper.removeUser(userId)) {
            Helper.goError(response);
        }
    }
}

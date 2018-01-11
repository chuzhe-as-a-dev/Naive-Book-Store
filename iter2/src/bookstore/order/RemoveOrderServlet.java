package bookstore.order;

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
@WebServlet("/remove_order")
public class RemoveOrderServlet extends HttpServlet {
    public void init() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // parse book id
        int orderId;
        try {
            orderId = Integer.parseInt(request.getParameter("orderId"));
        } catch (Exception e) {
            Helper.goError(response);
            return;
        }

        // remove book
        if (!Helper.removeOrder(orderId)) {
            Helper.goError(response);
        }
    }
}

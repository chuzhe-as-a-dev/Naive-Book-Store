package bookstore.order;

import bookstore.Helper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.sql.Date;

/**
 * Created by tang on 2017/4/11.
 */
@WebFilter(urlPatterns = {"/submit_change_order", "/submit_new_order"})
public class ValidateOrderFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws java.io.IOException, ServletException {
        String orderId = request.getParameter("orderId");
        if (orderId != null && !Helper.isInteger(orderId)) {
            Helper.goError(response);
            return;
        }

        String userId = request.getParameter("userId");
        if (userId == null || !Helper.isInteger(userId)) {
            Helper.goError(response);
            return;
        }

        String orderDate = request.getParameter("orderDate");
        if (orderDate == null || orderDate.length() == 0) {
            Helper.goError(response);
            return;
        }

        String totalPrice = request.getParameter("totalPrice");
        if (totalPrice == null || !Helper.isFloat(totalPrice)) {
            Helper.goError(response);
            return;
        }

        String deliverTo = request.getParameter("deliverTo");
        if (deliverTo == null || deliverTo.length() == 0) {
            Helper.goError(response);
            return;
        }

        chain.doFilter(request, response);
    }

    public void destroy(){}
}

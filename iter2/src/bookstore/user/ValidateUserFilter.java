package bookstore.user;

import bookstore.Helper;
import bookstore.Helper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

/**
 * Created by tang on 2017/4/11.
 */
@WebFilter(urlPatterns = {"/submit_new_user", "/submit_change_user"})
public class ValidateUserFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws java.io.IOException, ServletException {
        String userId = request.getParameter("userId");
        if (userId != null && !Helper.isInteger(userId)) {
            Helper.goError(response);
            return;
        }

        String username = request.getParameter("username");
        if (username == null || username.length() == 0 || username.length() > 32) {
            Helper.goError(response);
            return;
        }

        String email = request.getParameter("email");
        if (email == null || email.length() == 0) {
            Helper.goError(response);
            return;
        }

        String credit = request.getParameter("credit");
        if (credit == null || !Helper.isFloat(credit)) {
            Helper.goError(response);
            return;
        }

        String password = request.getParameter("password");
        if (password == null || password.length() == 0 || password.length() > 255) {
            Helper.goError(response);
            return;
        }

        chain.doFilter(request, response);
    }

    public void destroy(){}
}

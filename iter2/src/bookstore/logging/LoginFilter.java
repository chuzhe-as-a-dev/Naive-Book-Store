package bookstore.logging;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by tang on 2017/4/8.
 */
@WebFilter("/login")
public class LoginFilter implements Filter{
    public void  init(FilterConfig config) throws ServletException {}

    public void  doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws java.io.IOException, ServletException {
        boolean pre_process_ok = true;
        if (request.getParameter("login-identifier").length() == 0) pre_process_ok = false;
        if (request.getParameter("login-password").length() == 0) pre_process_ok = false;
        if (!validateIdentifier(request.getParameter("login-identifier"))) pre_process_ok = false;

        if (pre_process_ok) {
            if (request.getParameter("login-identifier").contains("@")) {
                request.setAttribute("by-email", true);
            }
            chain.doFilter(request,response);
        } else {
            HttpServletResponse res = (HttpServletResponse) response;
            // todo: prompt login error
            res.sendRedirect("/error.html");
        }
    }

    public void destroy( ){}

    private boolean validateIdentifier(String identifier) {
        if (identifier.contains("@")) {
            if (identifier.indexOf("@") == 0) return false;
            if (!identifier.contains(".")) return false;
            if (identifier.indexOf(".") <= identifier.indexOf("@")) return false;
        } else {
            if (identifier.length() > 32) return false;
            for (int i = 0, n = identifier.length(); i != n; ++i) {
                char c = identifier.charAt(i);
                if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '1' || c > '9')) return false;
            }
        }

        return true;
    }
}

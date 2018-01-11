package bookstore.book;

import bookstore.Helper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;


/**
 * Created by tang on 2017/4/9.
 */
@WebFilter(urlPatterns = {"/submit_new_book", "/submit_change_book"})
public class ValidateBookFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws java.io.IOException, ServletException {
        String bookId = request.getParameter("bookId");
        if (bookId != null) {
            try {
                Integer.parseInt(bookId);
            } catch (Exception e) {
                Helper.goError(response);
                return;
            }
        }

        String bookName = request.getParameter("bookName");
        if (bookName == null || bookName.length() == 0) {
            Helper.goError(response);
            return;
        }

        String author = request.getParameter("author");
        if (author == null || author.length() == 0) {
            Helper.goError(response);
            return;
        }

        String isbn = request.getParameter("isbn");
        if (isbn == null || (isbn.length() != 10 && isbn.length() != 13)) {
            Helper.goError(response);
            return;
        }

        String price = request.getParameter("price");
        if (price == null || price.length() == 0) {
            Helper.goError(response);
            return;
        }
        try {
            Float.parseFloat(price);
        } catch (Exception e) {
            Helper.goError(response);
            return;
        }

        String stock = request.getParameter("stock");
        if (stock == null || stock.length() == 0) {
            Helper.goError(response);
            return;
        }
        try {
            Integer.parseInt(stock);
        } catch (Exception e) {
            Helper.goError(response);
            return;
        }

        String description = request.getParameter("description");
        if (description == null || description.length() == 0) {
            Helper.goError(response);
            return;
        }

        chain.doFilter(request, response);
    }

    public void destroy(){}
}

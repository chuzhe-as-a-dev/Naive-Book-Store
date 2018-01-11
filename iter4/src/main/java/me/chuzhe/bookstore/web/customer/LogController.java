package me.chuzhe.bookstore.web.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * Created by tang on 2017/5/24.
 */
@Controller
public class LogController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/logged_in")
    @ResponseBody
    public boolean checkStatus(Principal principal) {
        return principal != null;
    }
}

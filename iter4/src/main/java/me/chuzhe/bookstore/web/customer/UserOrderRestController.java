package me.chuzhe.bookstore.web.customer;

import com.fasterxml.jackson.annotation.JsonView;
import me.chuzhe.bookstore.domain.JacksonView;
import me.chuzhe.bookstore.domain.entity.Order;
import me.chuzhe.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by tang on 2017/5/24.
 */
@RestController
@RequestMapping("/user/order")
public class UserOrderRestController {

    private final OrderService orderService;

    @Autowired
    public UserOrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    @JsonView(JacksonView.OrderView.class)
    public List<Order> getAllOrders(Principal principal) {
        return orderService.getOrderByUsername(principal.getName());
    }

    @PostMapping
    public boolean addOrderFromCart(@RequestParam String deliverTo, Principal principal) {
        if (deliverTo.length() == 0) {
            return false;
        }

        try {
            return orderService.execute(deliverTo, principal.getName());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}

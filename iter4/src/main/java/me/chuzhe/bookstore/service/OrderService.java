package me.chuzhe.bookstore.service;

import me.chuzhe.bookstore.domain.entity.Order;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tang on 2017/5/24.
 */
public interface OrderService {

    List<Order> getOrderByUsername(String username);

    boolean execute(String deliverTo, String username);
}

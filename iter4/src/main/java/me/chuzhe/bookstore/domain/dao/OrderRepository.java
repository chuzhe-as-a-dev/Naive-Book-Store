package me.chuzhe.bookstore.domain.dao;

import me.chuzhe.bookstore.domain.entity.Order;
import me.chuzhe.bookstore.domain.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by tang on 2017/5/21.
 */
public interface OrderRepository extends CrudRepository<Order, Integer> {
    List<Order> findByUserByUserId(User user, Sort sort);

    @Query(value = "SELECT sum(total_price) FROM `Order`", nativeQuery = true)
    int getSumOfTotalPrice();
}

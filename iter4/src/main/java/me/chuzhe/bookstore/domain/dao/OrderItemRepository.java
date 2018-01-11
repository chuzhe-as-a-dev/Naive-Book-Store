package me.chuzhe.bookstore.domain.dao;

import me.chuzhe.bookstore.domain.entity.OrderItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by tang on 2017/5/21.
 */
public interface OrderItemRepository extends CrudRepository<OrderItem, Integer> {

    @Query(value = "SELECT sum(quantity) FROM OrderItem", nativeQuery = true)
    int getSumOfQuantity();

    @Query(value = "SELECT sum(quantity * OrderItem.original_unit_price) FROM OrderItem", nativeQuery = true)
    int getSumOfQuantityTimesOriginalUnitPrice();
}

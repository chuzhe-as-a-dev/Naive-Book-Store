package me.chuzhe.bookstore.domain.dao;

import me.chuzhe.bookstore.domain.dao.OrderItemRepository;
import me.chuzhe.bookstore.domain.dao.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.Map;

/**
 * Created by tang on 2017/6/7.
 */
@Component
public class StatisticsDao {
    private int valueOutput;

    private int quantityOutput;

    private int totalValue;

    private int totalQuantity;
    private int totalQuantityTimesOriginalPrice;

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    private final DataSource dataSource;

    @Autowired
    public StatisticsDao(OrderRepository orderRepository, OrderItemRepository orderItemRepository, @Qualifier("mysqlDataSource") DataSource dataSource) {
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.dataSource = dataSource;
    }

    public void run(String username, String isbn, String category, Timestamp start_time, Timestamp end_time) {
        SimpleJdbcCall proc = new SimpleJdbcCall(dataSource).withProcedureName("statistics");
        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("username", username)
                .addValue("isbn", isbn)
                .addValue("category_in", category)
                .addValue("start_time", start_time)
                .addValue("end_time", end_time);

        Map out = proc.execute(in);

        valueOutput = (int) out.get("value_output");
        quantityOutput = (int) out.get("quantity_output");

        totalValue = orderRepository.getSumOfTotalPrice();
        totalQuantity = orderItemRepository.getSumOfQuantity();
        totalQuantityTimesOriginalPrice = orderItemRepository.getSumOfQuantityTimesOriginalUnitPrice();
    }

    public int getValueOutput() {
        return valueOutput;
    }

    public void setValueOutput(int valueOutput) {
        this.valueOutput = valueOutput;
    }

    public int getQuantityOutput() {
        return quantityOutput;
    }

    public void setQuantityOutput(int quantityOutput) {
        this.quantityOutput = quantityOutput;
    }

    public int getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getTotalQuantityTimesOriginalPrice() {
        return totalQuantityTimesOriginalPrice;
    }

    public void setTotalQuantityTimesOriginalPrice(int totalQuantityTimesOriginalPrice) {
        this.totalQuantityTimesOriginalPrice = totalQuantityTimesOriginalPrice;
    }
}

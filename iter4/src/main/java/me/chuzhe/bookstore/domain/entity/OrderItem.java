package me.chuzhe.bookstore.domain.entity;

import com.fasterxml.jackson.annotation.JsonView;
import me.chuzhe.bookstore.domain.JacksonView;

import javax.persistence.*;

/**
 * Created by tang on 2017/5/20.
 */
@Entity
@Table(name = "OrderItem")
public class OrderItem {

    @JsonView(JacksonView.OrderView.class)
    private int orderItemId;

    @JsonView(JacksonView.OrderView.class)
    private int originalUnitPrice;

    @JsonView(JacksonView.OrderView.class)
    private int quantity;


    private Order orderByOrderId;

    @JsonView(JacksonView.OrderView.class)
    private Book bookByBookId;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "order_item_id", nullable = false)
    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    @Basic
    @Column(name = "original_unit_price", nullable = false)
    public int getOriginalUnitPrice() {
        return originalUnitPrice;
    }

    public void setOriginalUnitPrice(int originalUnitPrice) {
        this.originalUnitPrice = originalUnitPrice;
    }

    @Basic
    @Column(name = "quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        if (orderItemId != orderItem.orderItemId) return false;
        if (originalUnitPrice != orderItem.originalUnitPrice) return false;
        if (quantity != orderItem.quantity) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderItemId;
        result = 31 * result + originalUnitPrice;
        result = 31 * result + quantity;
        return result;
    }

    @Override
    public String toString() {
        return "Order Item: " + getOrderItemId() + ", " + getOrderByOrderId().getOrderId();
    }

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false)
    public Order getOrderByOrderId() {
        return orderByOrderId;
    }

    public void setOrderByOrderId(Order orderByOrderId) {
        this.orderByOrderId = orderByOrderId;
    }

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id", nullable = false)
    public Book getBookByBookId() {
        return bookByBookId;
    }

    public void setBookByBookId(Book bookByBookId) {
        this.bookByBookId = bookByBookId;
    }
}

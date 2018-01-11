package me.chuzhe.bookstore.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by tang on 2017/5/20.
 */
@Entity
@Table(name = "`Order`")
public class Order {
    private int orderId;
    private Timestamp placeTime;
    private int totalPrice;
    private String deliverTo;
    private User userByUserId;
    private Collection<OrderItem> orderItemsByOrderId;

    @Id
    @Column(name = "order_id", nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "place_time", nullable = false)
    public Timestamp getPlaceTime() {
        return placeTime;
    }

    public void setPlaceTime(Timestamp placeTime) {
        this.placeTime = placeTime;
    }

    @Basic
    @Column(name = "total_price", nullable = false)
    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Basic
    @Column(name = "deliver_to", nullable = false, length = -1)
    public String getDeliverTo() {
        return deliverTo;
    }

    public void setDeliverTo(String deliverTo) {
        this.deliverTo = deliverTo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (orderId != order.orderId) return false;
        if (totalPrice != order.totalPrice) return false;
        if (placeTime != null ? !placeTime.equals(order.placeTime) : order.placeTime != null) return false;
        if (deliverTo != null ? !deliverTo.equals(order.deliverTo) : order.deliverTo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (placeTime != null ? placeTime.hashCode() : 0);
        result = 31 * result + totalPrice;
        result = 31 * result + (deliverTo != null ? deliverTo.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @OneToMany(mappedBy = "orderByOrderId")
    public Collection<OrderItem> getOrderItemsByOrderId() {
        return orderItemsByOrderId;
    }

    public void setOrderItemsByOrderId(Collection<OrderItem> orderItemsByOrderId) {
        this.orderItemsByOrderId = orderItemsByOrderId;
    }
}

package bookstore.database;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tang on 2017/4/7.
 */
@Entity
@Table(name = "`Order`", schema = "bookstore")
public class OrderEntity {
    private int orderId;
    private UserEntity user;
    private Date orderDate;
    private BigDecimal totalPrice;
    private String deliverTo;
    private Set<BookInOrderEntity> bookInOrders = new HashSet<>(0);

    public OrderEntity() {}

    public OrderEntity(int orderId, UserEntity user, Date orderDate, BigDecimal totalPrice, String deliverTo) {
        this.orderId = orderId;
        this.user = user;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.deliverTo = deliverTo;
    }

    public OrderEntity(int orderId, UserEntity user, Date orderDate, BigDecimal totalPrice, String deliverTo, Set<BookInOrderEntity> book_in_orders) {
        this.orderId = orderId;
        this.user = user;
        this.orderDate = orderDate;
        this.totalPrice = totalPrice;
        this.deliverTo = deliverTo;
        this.bookInOrders = book_in_orders;
    }

    @Id
    @Column(name = "order_id", nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Basic
    @Column(name = "order_date", nullable = false)
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Basic
    @Column(name = "total_price", nullable = false, precision = 2)
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    public Set<BookInOrderEntity> getBookInOrders() {
        return this.bookInOrders;
    }

    public void setBookInOrders(Set<BookInOrderEntity> book_in_orders) {
        this.bookInOrders = book_in_orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (orderId != that.orderId) return false;
        if (user != that.user) return false;
        if (orderDate != null ? !orderDate.equals(that.orderDate) : that.orderDate != null) return false;
        if (totalPrice != null ? !totalPrice.equals(that.totalPrice) : that.totalPrice != null) return false;
        if (deliverTo != null ? !deliverTo.equals(that.deliverTo) : that.deliverTo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = orderId;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (totalPrice != null ? totalPrice.hashCode() : 0);
        result = 31 * result + (deliverTo != null ? deliverTo.hashCode() : 0);
        return result;
    }
}

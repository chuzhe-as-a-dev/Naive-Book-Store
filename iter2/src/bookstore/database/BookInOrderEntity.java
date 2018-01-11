package bookstore.database;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by tang on 2017/4/11.
 */
@Entity
@Table(name = "BookInOrder", schema = "bookstore")
public class BookInOrderEntity {
    private int bookInOrderId;
    private transient OrderEntity order;
    private BookEntity book;
    private BigDecimal originalUnitPrice;
    private int quantity;

    public BookInOrderEntity() {}

    @Id
    @Column(name = "book_in_order_id", nullable = false)
    public int getBookInOrderId() {
        return bookInOrderId;
    }

    public void setBookInOrderId(int book_in_order_id) {
        this.bookInOrderId = book_in_order_id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    @Basic
    @Column(name = "original_unit_price", nullable = false)
    public BigDecimal getOriginalUnitPrice() {
        return originalUnitPrice;
    }

    public void setOriginalUnitPrice(BigDecimal original_unit_price) {
        this.originalUnitPrice = original_unit_price;
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

        BookInOrderEntity that = (BookInOrderEntity) o;

        if (bookInOrderId != that.bookInOrderId) return false;
        if (order != null ? !order.equals(that.order) : that.order != null) return false;
        if (book != null ? !book.equals(that.book) : that.book != null) return false;
        if (originalUnitPrice != null ? !originalUnitPrice.equals(that.originalUnitPrice) : that.originalUnitPrice != null) return false;
        if (quantity != that.quantity) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookInOrderId;
        result = 31 * result + (order != null ? order.hashCode() : 0);
        result = 31 * result + (book != null ? book.hashCode() : 0);
        result = 31 * result + (originalUnitPrice != null ? originalUnitPrice.hashCode() : 0);
        result = 31 * result + quantity;
        return result;
    }
}

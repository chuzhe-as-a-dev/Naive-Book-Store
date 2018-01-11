package me.chuzhe.bookstore.domain.entity;

import javax.persistence.*;

/**
 * Created by tang on 2017/5/20.
 */
@Entity
@Table(name = "CartItem")
public class CartItem {
    private int quantity;
    private int cartItemId;
    private int userId;
    private Book bookByBookId;

    @Basic
    @Column(name = "quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "cart_item_id", nullable = false)
    public int getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(int cartItemId) {
        this.cartItemId = cartItemId;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Shopping Cart Item: " + getCartItemId() + ", " + getUserId() + ", " + getBookByBookId().getBookTitle();
    }

    @OneToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id", nullable = false)
    public Book getBookByBookId() {
        return bookByBookId;
    }

    public void setBookByBookId(Book bookByBookId) {
        this.bookByBookId = bookByBookId;
    }
}

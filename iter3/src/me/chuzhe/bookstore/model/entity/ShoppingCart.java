package me.chuzhe.bookstore.model.entity;

import javax.persistence.*;

/**
 * Created by tang on 2017/5/20.
 */
@Entity
public class ShoppingCart {
    private int cartId;
    private User userByUserId;

    @Id
    @Column(name = "cart_id", nullable = false)
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShoppingCart that = (ShoppingCart) o;

        if (cartId != that.cartId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return cartId;
    }

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}

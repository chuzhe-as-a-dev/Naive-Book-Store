package me.chuzhe.bookstore.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**I
 * Created by tang on 2017/5/24.
 */
public class CartItemDto {
    @NotNull
    @Min(1)
    private int bookId;

    @NotNull
    @Min(1)
    private int quantity;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

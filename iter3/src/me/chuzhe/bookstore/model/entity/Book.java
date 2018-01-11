package me.chuzhe.bookstore.model.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by tang on 2017/5/20.
 */
@Entity
public class Book {
    private int bookId;
    private String isbn;
    private String bookTitle;
    private String author;
    private int price;
    private int stock;
    private String coverFilename;
    private String description;
    private byte forSale;
    private Collection<BookComment> bookCommentsByBookId;
    private Collection<OrderItem> orderItemsByBookId;

    @Id
    @Column(name = "book_id", nullable = false)
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "isbn", nullable = true, length = 13)
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "book_title", nullable = false, length = 255)
    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    @Basic
    @Column(name = "author", nullable = false, length = 255)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "price", nullable = false)
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Basic
    @Column(name = "stock", nullable = false)
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Basic
    @Column(name = "cover_filename", nullable = false, length = -1)
    public String getCoverFilename() {
        return coverFilename;
    }

    public void setCoverFilename(String coverFilename) {
        this.coverFilename = coverFilename;
    }

    @Basic
    @Column(name = "description", nullable = false, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "for_sale", nullable = false)
    public byte getForSale() {
        return forSale;
    }

    public void setForSale(byte forSale) {
        this.forSale = forSale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (bookId != book.bookId) return false;
        if (price != book.price) return false;
        if (stock != book.stock) return false;
        if (forSale != book.forSale) return false;
        if (isbn != null ? !isbn.equals(book.isbn) : book.isbn != null) return false;
        if (bookTitle != null ? !bookTitle.equals(book.bookTitle) : book.bookTitle != null) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        if (coverFilename != null ? !coverFilename.equals(book.coverFilename) : book.coverFilename != null)
            return false;
        if (description != null ? !description.equals(book.description) : book.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookId;
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (bookTitle != null ? bookTitle.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + stock;
        result = 31 * result + (coverFilename != null ? coverFilename.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (int) forSale;
        return result;
    }

    @OneToMany(mappedBy = "bookByBookId")
    public Collection<BookComment> getBookCommentsByBookId() {
        return bookCommentsByBookId;
    }

    public void setBookCommentsByBookId(Collection<BookComment> bookCommentsByBookId) {
        this.bookCommentsByBookId = bookCommentsByBookId;
    }

    @OneToMany(mappedBy = "bookByBookId")
    public Collection<OrderItem> getOrderItemsByBookId() {
        return orderItemsByBookId;
    }

    public void setOrderItemsByBookId(Collection<OrderItem> orderItemsByBookId) {
        this.orderItemsByBookId = orderItemsByBookId;
    }
}

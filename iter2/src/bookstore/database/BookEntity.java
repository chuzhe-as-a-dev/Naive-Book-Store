package bookstore.database;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by tang on 2017/4/7.
 */
@Entity
@Table(name = "Book", schema = "bookstore")
public class BookEntity {
    private int bookId;
    private String isbn;
    private String bookName;
    private String author;
    private BigDecimal price;
    private int stock;
    private String description;
    private String coverFilename;
    private transient Set<BookInOrderEntity> book_in_orders = new HashSet<>(0);
    private transient Set<CommentEntity> comments = new HashSet<>(0);

    public BookEntity() {}

    public BookEntity(int bookId, String isbn, String bookName, String author, BigDecimal price, int stock, String description, String coverFilename) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.bookName = bookName;
        this.author = author;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.coverFilename = coverFilename;
    }

    public BookEntity(int bookId, String isbn, String bookName, String author, BigDecimal price, int stock, String description, String coverFilename, Set<BookInOrderEntity> book_in_orders, Set<CommentEntity> comments) {
        this.bookId = bookId;
        this.isbn = isbn;
        this.bookName = bookName;
        this.author = author;
        this.price = price;
        this.stock = stock;
        this.description = description;
        this.coverFilename = coverFilename;
        this.book_in_orders = book_in_orders;
        this.comments = comments;
    }

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
    @Column(name = "book_name", nullable = false, length = -1)
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Basic
    @Column(name = "author", nullable = false, length = -1)
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 2)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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
    @Column(name = "description", nullable = false, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "cover_filename", nullable = false, length = -1)
    public String getCoverFilename() {
        return coverFilename;
    }

    public void setCoverFilename(String coverFilename) {
        this.coverFilename = coverFilename;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    public Set<BookInOrderEntity> getBookInOrders() {
        return this.book_in_orders;
    }

    public void setBookInOrders(Set<BookInOrderEntity> book_in_orders) {
        this.book_in_orders = book_in_orders;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    public Set<CommentEntity> getComments() {
        return this.comments;
    }

    public void setComments(Set<CommentEntity> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntity that = (BookEntity) o;

        if (bookId != that.bookId) return false;
        if (isbn != null ? !isbn.equals(that.isbn) : that.isbn != null) return false;
        if (bookName != null ? !bookName.equals(that.bookName) : that.bookName != null) return false;
        if (author != null ? !author.equals(that.author) : that.author != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (stock != that.stock) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (coverFilename != null ? !coverFilename.equals(that.coverFilename) : that.coverFilename != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookId;
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (bookName != null ? bookName.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + stock;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (coverFilename != null ? coverFilename.hashCode() : 0);
        return result;
    }
}

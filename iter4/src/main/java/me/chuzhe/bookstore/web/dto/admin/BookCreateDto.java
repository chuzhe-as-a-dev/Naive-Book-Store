package me.chuzhe.bookstore.web.dto.admin;


import javax.validation.constraints.*;

/**
 * Created by tang on 2017/5/22.
 */
public class BookCreateDto {
    @NotNull
    @Size(max = 13)
    private String isbn;

    @NotNull
    @Size(max = 255)
    private String bookTitle;

    @NotNull
    @Size(max = 255)
    private String author;

    @NotNull
    @Min(1)
    private int price;

    @NotNull
    @Min(1)
    private int stock;

    @NotNull
    @Size(max = 255)
    private String coverFilename;

    @NotNull
    @Size(min = 1)
    private String description;

    private String forSale;

    @NotNull
    @Size(min = 1, max = 64)
    private String category;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCoverFilename() {
        return this.coverFilename = coverFilename.length() > 0 ? coverFilename : "default.jpg";
    }

    public void setCoverFilename(String coverFilename) {
        this.coverFilename = coverFilename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getForSale() {
        return forSale;
    }

    public void setForSale(String forSale) {
        this.forSale = forSale;
    }

    public boolean isForSale() {
        return forSale != null;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

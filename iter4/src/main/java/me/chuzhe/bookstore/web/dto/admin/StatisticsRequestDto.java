package me.chuzhe.bookstore.web.dto.admin;

import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * Created by tang on 2017/6/7.
 */
public class StatisticsRequestDto {

    @Size(max = 255)
    private String username;

    @Size(max = 13, min = 10)
    private String isbn;

    @Size(max = 64)
    private String category;

    private String startTime;

    private String endTime;

    public String getUsername() {
        return username != null && username.length() > 0 ? username : null;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIsbn() {
        return isbn != null && isbn.length() > 0 ? isbn : null;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCategory() {
        return category != null && category.length() > 0 ? category : null;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Timestamp getStartTime() {
        try {
            return Timestamp.valueOf(startTime);
        } catch (Exception e) {
            return null;
        }
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        try {
            return Timestamp.valueOf(endTime);
        } catch (Exception e) {
            return null;
        }
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}

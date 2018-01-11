package me.chuzhe.bookstore.domain.entity;

import com.fasterxml.jackson.annotation.JsonView;
import me.chuzhe.bookstore.domain.JacksonView;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by tang on 2017/5/20.
 */
@Entity
@Table(name = "BookComment")
public class BookComment {
    private int bookCommentId;

    @JsonView(JacksonView.Comment.class)
    private String title;

    @JsonView(JacksonView.Comment.class)
    private String content;

    @JsonView(JacksonView.Comment.class)
    private Timestamp commentTime;

    @JsonView(JacksonView.Comment.class)
    private int bookId;

    @JsonView(JacksonView.Comment.class)
    private User userByUserId;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "book_comment_id", nullable = false)
    public int getBookCommentId() {
        return bookCommentId;
    }

    public void setBookCommentId(int bookCommentId) {
        this.bookCommentId = bookCommentId;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "content", nullable = false, length = -1)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "comment_time", nullable = false)
    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    @Basic
    @Column(name = "book_id", nullable = false)
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookComment that = (BookComment) o;

        if (bookCommentId != that.bookCommentId) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (commentTime != null ? !commentTime.equals(that.commentTime) : that.commentTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookCommentId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (commentTime != null ? commentTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book Comment: " + getBookCommentId() + ", " + getBookId() + ", " + getUserByUserId().getUsername();
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }
}

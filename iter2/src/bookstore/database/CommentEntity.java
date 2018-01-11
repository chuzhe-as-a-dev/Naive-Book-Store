package bookstore.database;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by tang on 2017/4/7.
 */
@Entity
@Table(name = "Comment", schema = "bookstore")
public class CommentEntity {
    private int commentId;
    private transient BookEntity book;
    private UserEntity user;
    private String title;
    private String comment;
    private Date commentDate;

    public CommentEntity() {}

    public CommentEntity(int commentId, UserEntity user, String title, String comment, Date commentDate) {
        this.commentId = commentId;
        this.user = user;
        this.title = title;
        this.comment = comment;
        this.commentDate = commentDate;
    }

    public CommentEntity(int commentId, BookEntity book, UserEntity user, String title, String comment, Date commentDate) {
        this.commentId = commentId;
        this.book = book;
        this.user = user;
        this.title = title;
        this.comment = comment;
        this.commentDate = commentDate;
    }


    @Id
    @Column(name = "comment_id", nullable = false)
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Basic
    @Column(name = "title", nullable = false, length = -1)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "comment", nullable = false, length = -1)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "comment_date", nullable = false)
    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    public BookEntity getBook() {
        return this.book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    public UserEntity getUser() {
        return this.user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentEntity that = (CommentEntity) o;

        if (commentId != that.commentId) return false;
        if (book != null ? !book.equals(that.book) : that.book != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (commentDate != null ? !commentDate.equals(that.commentDate) : that.commentDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commentId;
        result = 31 * result + book.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (commentDate != null ? commentDate.hashCode() : 0);
        return result;
    }
}

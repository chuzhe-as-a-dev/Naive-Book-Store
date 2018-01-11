package me.chuzhe.bookstore.domain.entity;

import com.fasterxml.jackson.annotation.JsonView;
import me.chuzhe.bookstore.domain.JacksonView;

import javax.persistence.*;

/**
 * Created by tang on 2017/5/21.
 */
@Entity
@Table(name = "User")
public class User {

    private int userId;

    @JsonView(JacksonView.Comment.class)
    private String username;

    private String email;

    private int credit;

    private String password;

    private boolean deleted;

    private boolean isAdmin;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 32)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "credit", nullable = false)
    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "deleted", nullable = false, columnDefinition = "TINYINT(1)")
    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Basic
    @Column(name = "is_admin", nullable = false, columnDefinition = "TINYINT(1)")
    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (credit != user.credit) return false;
        if (deleted != user.deleted) return false;
        if (isAdmin != user.isAdmin) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + credit;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (deleted ? 1 : 0);
        result = 31 * result + (isAdmin ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User: " + getUserId() + ", " + getUsername();
    }
}

package bookstore.database;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

/**
 * Created by tang on 2017/4/7.
 */
@Entity
@Table(name = "User", schema = "bookstore")
public class UserEntity {
    private int userId;
    private String username;
    private String email;
    private BigDecimal credit;
    private String password;
    private String iconFilename;
    private transient Set<CommentEntity> comments;
    private transient Set<OrderEntity> orders;

    public UserEntity() {}

    public UserEntity(int userId, String username, String email, BigDecimal credit, String password, String iconFilename) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.credit = credit;
        this.password = password;
        this.iconFilename = iconFilename;
    }

    @Id
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
    @Column(name = "email", nullable = false, length = -1)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "credit", nullable = false, precision = 2)
    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
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
    @Column(name = "icon_filename", nullable = false, length = -1)
    public String getIconFilename() {
        return iconFilename;
    }

    public void setIconFilename(String iconFilename) {
        this.iconFilename = iconFilename;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<CommentEntity> getComments() {
        return this.comments;
    }

    public void setComments(Set<CommentEntity> comments) {
        this.comments = comments;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    public Set<OrderEntity> getOrders() {
        return this.orders;
    }

    public void setOrders(Set<OrderEntity> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userId != that.userId) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (credit != null ? !credit.equals(that.credit) : that.credit != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (iconFilename != null ? !iconFilename.equals(that.iconFilename) : that.iconFilename != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (credit != null ? credit.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (iconFilename != null ? iconFilename.hashCode() : 0);
        return result;
    }
}

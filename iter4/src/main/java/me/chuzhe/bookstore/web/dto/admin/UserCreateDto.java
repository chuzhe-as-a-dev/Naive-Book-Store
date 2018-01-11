package me.chuzhe.bookstore.web.dto.admin;

import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by tang on 2017/5/22.
 */
public class UserCreateDto {
    @NotNull
    @Size(max = 32)
    String username;

    @NotNull
    @Size(max = 255)
    String email;

    @NotNull
    @Min(1)
    int credit;

    @NotNull
    @Size(max = 255)
    String password;

    String deleted;

    String isAdmin;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean isDeleted() {
        return deleted != null;
    }

    public boolean isAdmin() {
        return isAdmin != null;
    }
}

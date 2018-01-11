package me.chuzhe.bookstore.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by tang on 2017/5/28.
 */
public class UserDto {
    @NotNull
    @Size(min = 1, max = 32)
    String username;

    @NotNull
    @Size(min = 1, max = 255)
    String email;

    @NotNull
    @Size(min = 1, max = 255)
    String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

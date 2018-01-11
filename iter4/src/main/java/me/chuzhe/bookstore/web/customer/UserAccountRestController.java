package me.chuzhe.bookstore.web.customer;

import me.chuzhe.bookstore.domain.entity.User;
import me.chuzhe.bookstore.service.UserService;
import me.chuzhe.bookstore.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

/**
 * Created by tang on 2017/5/24.
 */
@RestController
@RequestMapping("/user/account")
public class UserAccountRestController {

    private final UserService userService;

    @Autowired
    public UserAccountRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public User getAccount(Principal principal) {
        return userService.getUserByUsername(principal.getName());
    }

    @GetMapping("/credit")
    public int getCredit(Principal principal) {
        return userService.getUserCreditByUsername(principal.getName());
    }

    @PostMapping
    public boolean addAccount(@Valid UserDto userDto, BindingResult result) {
        return !result.hasErrors() && userService.addNormalUser(userDto);
    }

    @PutMapping
    public boolean updateAccount(@Valid UserDto userDto, BindingResult result, Principal principal) {
        return !result.hasErrors() && userService.updateUser(userDto, principal.getName());
    }

    @PutMapping("/password")
    public boolean updatePassword(@RequestParam String oldPassword, @RequestParam String newPassword, Principal principal) {
        return !(oldPassword.length() == 0 || newPassword.length() == 0) && userService.updatePassword(oldPassword, newPassword, principal.getName());

    }

    @PutMapping("/username")
    public boolean updateUsername(@RequestParam String username, Principal principal) {
        return username.length() != 0 && userService.updateUsername(principal.getName(), username);

    }

    @PostMapping("/delete")
    public boolean deleteAccount(@RequestParam String username, @RequestParam String password, Principal principal) {
        return username.equals(principal.getName()) && userService.deleteUserByUsernameAndPassword(username, password);

    }
}


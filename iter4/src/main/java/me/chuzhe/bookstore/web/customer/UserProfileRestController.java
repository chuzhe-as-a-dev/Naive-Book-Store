package me.chuzhe.bookstore.web.customer;

import me.chuzhe.bookstore.domain.document.UserProfile;
import me.chuzhe.bookstore.domain.entity.User;
import me.chuzhe.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * Created by tang on 2017/6/2.
 */
@RestController
@RequestMapping("/user/profile")
public class UserProfileRestController {

    private final UserService userService;

    @Autowired
    public UserProfileRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserProfile getProfile(Principal principal) {
        return userService.getProfileByUsername(principal.getName());
    }

//    @GetMapping("/reset")
//    public boolean resetAllProfiles() {
//        return userService.resetAllProfiles();
//    }

    // addresses
    @GetMapping("/address")
    public List<String> getAddresses(Principal principal) {
        return userService.getProfileByUsername(principal.getName()).getAddresses();
    }

    @PostMapping("/address")
    public boolean addAddress(@RequestParam String address, Principal principal) {
        return userService.addAddress(address, principal.getName());
    }

    @PutMapping("/address")
    public boolean updateAddress(@RequestParam int index, @RequestParam String address, Principal principal) {
        return userService.updateAddress(index, address, principal.getName());
    }

    @DeleteMapping("/address/{index}")
    public boolean removeAddress(@PathVariable int index, Principal principal) {
        return userService.removeAddress(index, principal.getName());
    }

    // private profile
    @PutMapping("/profile")
    public boolean updateProfile(@RequestParam String bio, @RequestParam String location, Principal principal) {
        return userService.updateProfilePart(bio, location, principal.getName());
    }

    // subscription
    @PutMapping("/subscription")
    public boolean updateSubscription(@RequestParam boolean newsletters, @RequestParam boolean autoRecharge, Principal principal) {
        return userService.updateSubscriptionPart(newsletters, autoRecharge, principal.getName());
    }
}

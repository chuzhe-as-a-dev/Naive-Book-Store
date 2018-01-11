package me.chuzhe.bookstore.service.impl;

import me.chuzhe.bookstore.domain.entity.User;
import me.chuzhe.bookstore.domain.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tang on 2017/5/24.
 */
@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }

        if (user.getDeleted()) {
            throw new UsernameNotFoundException("User " + username + " has deleted his/her account");
        }

        List<String> roles = new ArrayList<>();
        roles.add("User");
        if (user.getIsAdmin()) {
            roles.add("Admin");
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword().toLowerCase(), getAuthorities(roles));
    }

    private static List<GrantedAuthority> getAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
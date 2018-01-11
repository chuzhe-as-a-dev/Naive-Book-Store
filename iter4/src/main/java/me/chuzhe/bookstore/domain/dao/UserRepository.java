package me.chuzhe.bookstore.domain.dao;

/**
 * Created by tang on 2017/5/21.
 */

import me.chuzhe.bookstore.domain.entity.User;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);

    void deleteByUsername(String username);
}
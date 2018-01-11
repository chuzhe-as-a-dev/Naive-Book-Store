package me.chuzhe.bookstore.domain.dao;

/**
 * Created by tang on 2017/5/31.
 */

import me.chuzhe.bookstore.domain.document.UserProfile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserProfileRepository extends MongoRepository<UserProfile, String> {
    void deleteByUserId(int userId);

    UserProfile findByUserId(int userId);

    UserProfile findByUsername(String username);
}
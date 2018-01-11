package me.chuzhe.bookstore.domain.document;

/**
 * Created by tang on 2017/5/31.
 */

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class UserProfile {

    @Id
    public String id;

    private int userId;

    private String username;

    private String avatarFilename;

    private String location;

    private String Bio;

    private boolean newsletters;

    private boolean autoRecharge;

    private List<String> addresses;

    public UserProfile() {
    }

    @Override
    public String toString() {
        return "UserProfile (" + id + "), userId: " + userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarFilename() {
        return avatarFilename;
    }

    public void setAvatarFilename(String avatarFilename) {
        this.avatarFilename = avatarFilename;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBio() {
        return Bio;
    }

    public void setBio(String bio) {
        Bio = bio;
    }

    public boolean isNewsletters() {
        return newsletters;
    }

    public void setNewsletters(boolean newsletters) {
        this.newsletters = newsletters;
    }

    public boolean isAutoRecharge() {
        return autoRecharge;
    }

    public void setAutoRecharge(boolean autoRecharge) {
        this.autoRecharge = autoRecharge;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }
}

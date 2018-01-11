package me.chuzhe.bookstore.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("exception")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String location = "/Users/tang/Documents/Courses/SE228 Web Application Development/NaiveBookStore-Spring Boot/files";

    private String avatarRelativeLocation = "img/avatar";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvatarRelativeLocation() {
        return avatarRelativeLocation;
    }

    public void setAvatarRelativeLocation(String avatarRelativeLocation) {
        this.avatarRelativeLocation = avatarRelativeLocation;
    }
}

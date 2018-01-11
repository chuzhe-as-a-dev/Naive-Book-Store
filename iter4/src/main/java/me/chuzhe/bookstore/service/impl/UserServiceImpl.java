package me.chuzhe.bookstore.service.impl;

import me.chuzhe.bookstore.domain.document.UserProfile;
import me.chuzhe.bookstore.domain.entity.User;
import me.chuzhe.bookstore.domain.dao.UserProfileRepository;
import me.chuzhe.bookstore.domain.dao.UserRepository;
import me.chuzhe.bookstore.service.UserService;
import me.chuzhe.bookstore.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by tang on 2017/6/23.
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserProfileRepository userProfileRepository) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
    }

    // about account
    public boolean saveUser(User user) {
        //noinspection Duplicates
        try {
            userRepository.save(user);
            return true;
        } catch (DataAccessException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public User getUserById(int userId) {
        return userRepository.findOne(userId);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public int getUserCreditByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return 0;
        }

        return user.getCredit();
    }

    public Iterable<User> getAllUser() {
        return userRepository.findAll();
    }

    public boolean addUser(UserDto userDto) {
        // add User
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setCredit(20000);
        user.setPassword(userDto.getPassword());
        user.setDeleted(false);
        user.setIsAdmin(false);

        // add UserProfile
        return saveUser(user) && createDefaultProfile(userDto.getUsername()) != null;

    }

    public boolean addNormalUser(UserDto userDto) {
        if (!addUser(userDto)) {
            return false;
        }

        User user = getUserByUsername(userDto.getUsername());
        user.setCredit(10000);
        user.setDeleted(false);
        user.setIsAdmin(false);

        return saveUser(user);
    }

    public boolean updateUser(UserDto userDto, String username) {
        if (!Objects.equals(username, userDto.getUsername())) {
            // make sure new username does not exists
            User user = userRepository.findByUsername(userDto.getUsername());
            if (user != null) {
                return false;
            }
        }

        User user = userRepository.findByUsername(username);
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        return saveUser(user);
    }

    public boolean updateUser(User user) {
        return saveUser(user);
    }

    public boolean updatePassword(String oldPassword, String newPassword, String username) {
        User user = userRepository.findByUsername(username);
        if (!user.getPassword().equals(oldPassword)) {
            return false;
        }

        user.setPassword(newPassword);
        return saveUser(user);
    }

    public boolean updateUsername(String oldUsername, String newUsername) {
        User user = userRepository.findByUsername(newUsername);
        UserProfile userProfile = userProfileRepository.findByUsername(newUsername);
        if (user != null || userProfile != null) {
            return false;
        }

        user = userRepository.findByUsername(oldUsername);
        if (user == null) {
            return false;
        }

        userProfile = userProfileRepository.findByUsername(oldUsername);
        if (userProfile == null) {
            return false;
        }

        user.setUsername(newUsername);
        userProfile.setUsername(newUsername);
        return saveUser(user) && saveProfile(userProfile);
    }

    public boolean deleteUserByUserId(int userId) {
        try {
            userProfileRepository.deleteByUserId(userId);
            userRepository.delete(userId);
            return true;
        } catch (DataAccessException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public boolean deleteUserByUsernameAndPassword(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            return false;
        }

        user.setDeleted(true);
        return saveUser(user);
    }

    // about profile
    private boolean saveProfile(UserProfile userProfile) {
        //noinspection Duplicates
        try {
            userProfileRepository.save(userProfile);
            return true;
        } catch (DataAccessException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public UserProfile getProfileByUsername(String username) {
        return userProfileRepository.findByUsername(username);
    }

    public String getAvatarFilenameByUsername(String username) {
        return userProfileRepository.findByUsername(username).getAvatarFilename();
    }

    public boolean resetAllProfiles() {
        userProfileRepository.deleteAll();

        for (User user : userRepository.findAll()) {
            UserProfile userProfile = new UserProfile();
            userProfile.setUserId(user.getUserId());
            userProfile.setUsername(user.getUsername());
            userProfile.setAvatarFilename("default.jpg");
            userProfile.setBio("");
            userProfile.setLocation("");
            userProfile.setNewsletters(false);
            userProfile.setAutoRecharge(false);
            userProfile.setAddresses(new ArrayList<>());

            if (!saveProfile(userProfile)) {
                return false;
            }
        }

        return true;
    }

    private UserProfile createDefaultProfile(String username) {
        UserProfile userProfile = userProfileRepository.findByUsername(username);
        if (userProfile != null) {
            return null;
        }

        User user = userRepository.findByUsername(username);
        if (user == null) {
            return null;
        }

        userProfile = new UserProfile();
        userProfile.setUserId(user.getUserId());
        userProfile.setUsername(user.getUsername());
        userProfile.setAvatarFilename("default.jpg");
        userProfile.setBio("");
        userProfile.setLocation("");
        userProfile.setNewsletters(false);
        userProfile.setAutoRecharge(false);
        userProfile.setAddresses(new ArrayList<>());

        if (saveProfile(userProfile)) {
            return userProfile;
        } else {
            return null;
        }
    }

    public boolean addAddress(String newAddress, String username) {
        UserProfile userProfile = userProfileRepository.findByUsername(username);
        userProfile.getAddresses().add(newAddress);
        return saveProfile(userProfile);
    }

    public boolean updateAddress(int index, String address, String username) {
        UserProfile userProfile = userProfileRepository.findByUsername(username);
        try {
            userProfile.getAddresses().set(index, address);
        } catch (Exception e) {
            return false;
        }
        return saveProfile(userProfile);
    }

    public boolean removeAddress(int index, String username) {
        UserProfile userProfile = userProfileRepository.findByUsername(username);
        userProfile.getAddresses().remove(index);
        return saveProfile(userProfile);
    }

    public boolean updateProfilePart(String bio, String location, String username) {
        UserProfile userProfile = userProfileRepository.findByUsername(username);

        userProfile.setBio(bio);
        userProfile.setLocation(location);
        return saveProfile(userProfile);
    }

    public boolean updateSubscriptionPart(boolean newsletters, boolean autoRecharge, String username) {
        UserProfile userProfile = userProfileRepository.findByUsername(username);

        userProfile.setNewsletters(newsletters);
        userProfile.setAutoRecharge(autoRecharge);
        return saveProfile(userProfile);
    }

    public boolean updateAvatarFilename(String avatarFilename, String username) {
        UserProfile userProfile = userProfileRepository.findByUsername(username);

        userProfile.setAvatarFilename(avatarFilename);
        return saveProfile(userProfile);
    }
}

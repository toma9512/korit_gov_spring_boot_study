package com.korit.korit_gov_spring_boot_study.repository;

import com.korit.korit_gov_spring_boot_study.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private static UserRepository instance;
    private List<User> userList;

    private UserRepository() {
        userList = new ArrayList<>();
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public Optional<User> findUserByUsername(String username) {
        User foundUser = userList.stream()
                .filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
        if (foundUser == null) {
            return Optional.empty();
        }
        return Optional.of(foundUser);
    }

    public void addUser(User user) {
        user.setUserId(userList.size() + 1);
        userList.add(user);
    }

    public List<User> getUserAll() {
        return userList;
    }
}

package org.example.services;

import org.example.entities.User;

import java.util.List;

public interface UserService {
    void saveUser(User user);
    User getUserById(int id);
    List<User> getAllUsers();
    void updateUser(User user);
    void deleteUserById(int id);
}

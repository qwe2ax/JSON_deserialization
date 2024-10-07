package org.example.dao;

import org.example.entities.User;

import java.util.List;

public interface UserDAO {
    void save(User user);
    User findById(int id);
    List<User> findAll();
    void update(User user);
    void deleteById(int id);
}

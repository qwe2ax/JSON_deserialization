package org.example.services;

import lombok.RequiredArgsConstructor;
import org.example.dao.UserDAO;
import org.example.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    @Transactional
    public void saveUser(User user) {
        userDAO.save(user);
    }

    @Transactional(readOnly = true)
    public User getUserById(int id) {
        User user = userDAO.findById(id);
        if (user == null) {
            throw new RuntimeException("User not found with id: " + id);
        }
        return user;
    }

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    @Transactional
    public void updateUser(User user) {
        userDAO.update(user);
    }

    @Transactional
    public void deleteUserById(int id) {
        userDAO.deleteById(id);
    }
}

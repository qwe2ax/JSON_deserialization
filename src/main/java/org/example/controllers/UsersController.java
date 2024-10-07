package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dao.UserDAO;
import org.example.entities.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserDAO userDAO;

    @GetMapping()
    public List<User> index() {
        return userDAO.findAll();
    }

    @GetMapping("/{id}")
    public User show(@PathVariable("id") int id) {
        return userDAO.findById(id);
    }
}

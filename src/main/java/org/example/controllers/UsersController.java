package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.entities.User;
import org.example.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    @GetMapping()
    public List<User> read() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User readById(@PathVariable("id") int id) {
        return userService.getUserById(id);
    }

    @PostMapping()
    public void create(@RequestBody User user) {
        userService.saveUser(user);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") int id, @RequestBody User user) {
        user.setId(id);
        userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        userService.deleteUserById(id);
    }
}

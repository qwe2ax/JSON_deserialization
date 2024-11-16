package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.entities.User;
import org.example.services.interfaces.UserService;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/{userId}/departments")
    public ResponseEntity<String> assignDepartmentToUser(@PathVariable int userId, @RequestParam(required = false) Integer departmentId, @RequestParam(required = false) String departmentName) {
        if (departmentId != null) {
            userService.assignDepartmentToUser(userId, departmentId);
        } else if (departmentName != null) {
            userService.assignDepartmentToUser(userId, departmentName);
        } else {
            return ResponseEntity.badRequest().body("Either department id or department name must be provided");
        }
        return ResponseEntity.ok("Department assigned to user successfully");
    }

//    @PutMapping("/{userId}/departments/{departmentName}")
//    public ResponseEntity<String> assignDepartmentToUser(@PathVariable int userId, @PathVariable String departmentName) {
//        userService.assignDepartmentToUser(userId, departmentName);
//        return ResponseEntity.ok("Department assigned to user successfully");
//    }

}

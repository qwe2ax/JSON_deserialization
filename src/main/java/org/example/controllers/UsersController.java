package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dto.UserDTO;
import org.example.entities.User;
import org.example.services.EntityConverterService;
import org.example.services.interfaces.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UsersController {

    private final EntityConverterService entityConverterService;
    private final UserService userService;

    @GetMapping()
    public List<UserDTO> read() {
        return userService.getAllUsers()
                .stream()
                .map(entityConverterService::convertUserToDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> readById(@PathVariable("id") int id) {
        return ResponseEntity.ok(
                entityConverterService.convertUserToDTO(userService.getUserById(id)));
    }

    @PostMapping()
    public ResponseEntity<UserDTO> create(@RequestBody UserDTO userDTO) {
        User savedUser = userService.saveUser(
                entityConverterService.convertToUser(userDTO));
        return new ResponseEntity<>(entityConverterService.convertUserToDTO(savedUser), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> update(@PathVariable("id") int id, @RequestBody UserDTO userDTO) {
        userDTO.setId(id);
        User updatedUser = userService.saveUser(entityConverterService.convertToUser(userDTO));
        return ResponseEntity.ok(entityConverterService.convertUserToDTO(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{userId}/departments")
    public ResponseEntity<String> assignDepartmentToUser(
            @PathVariable int userId,
            @RequestParam(required = false) Integer departmentId,
            @RequestParam(required = false) String departmentName) {
        if (departmentId != null) {
            userService.assignDepartmentToUser(userId, departmentId);
        } else if (departmentName != null) {
            userService.assignDepartmentToUser(userId, departmentName);
        } else {
            return ResponseEntity.badRequest().body("Either department id or department name must be provided");
        }
        return ResponseEntity.ok("Department assigned to user successfully");
    }

    @PutMapping("/{userId}/roles/{roleId}")
    public ResponseEntity<UserDTO> assignRoleToUser(@PathVariable int userId, @PathVariable int roleId) {
        User updatedUser = userService.assignRoleToUser(userId, roleId);
        return ResponseEntity.ok(entityConverterService.convertUserToDTO(updatedUser));
    }

//    @PutMapping("/{userId}/departments/{departmentName}")
//    public ResponseEntity<String> assignDepartmentToUser(@PathVariable int userId, @PathVariable String departmentName) {
//        userService.assignDepartmentToUser(userId, departmentName);
//        return ResponseEntity.ok("Department assigned to user successfully");
//    }

}

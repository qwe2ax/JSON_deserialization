package org.example.controllers;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.example.dto.UserDTO;
import org.example.entities.User;
import org.example.services.AuthService;
import org.example.services.EntityConverterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final EntityConverterService entityConverterService;

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser() {
        return ResponseEntity.ok(entityConverterService.convertUserToDTO(authService.getCurrentAuthenticatedUser()));
    }
}
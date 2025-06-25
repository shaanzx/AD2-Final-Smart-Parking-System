package com.spms.user.controller;

import com.spms.user.dto.UserDTO;
import com.spms.user.entity.User;
import com.spms.user.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userServiceImpl.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return userServiceImpl.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username) {
        return userServiceImpl.getUserByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email) {
        return userServiceImpl.getUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/type/{userType}")
    public ResponseEntity<List<UserDTO>> getUsersByType(@PathVariable User.UserType userType) {
        return ResponseEntity.ok(userServiceImpl.getUsersByType(userType));
    }

    @GetMapping("/active")
    public ResponseEntity<List<UserDTO>> getActiveUsers() {
        return ResponseEntity.ok(userServiceImpl.getActiveUsers());
    }

    @GetMapping("/active/type/{userType}")
    public ResponseEntity<List<UserDTO>> getActiveUsersByType(@PathVariable User.UserType userType) {
        return ResponseEntity.ok(userServiceImpl.getActiveUsersByType(userType));
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> searchUsersByName(@RequestParam String name) {
        return ResponseEntity.ok(userServiceImpl.searchUsersByName(name));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO) {
        try {
            UserDTO registeredUser = userServiceImpl.registerUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/authenticate")
    public ResponseEntity<UserDTO> authenticateUser(@RequestBody Map<String, String> credentials) {
        try {
            String usernameOrEmail = credentials.get("usernameOrEmail");
            String password = credentials.get("password");

            if (usernameOrEmail == null || password == null) {
                return ResponseEntity.badRequest().build();
            }

            UserDTO authenticatedUser = userServiceImpl.authenticateUser(usernameOrEmail, password);
            return ResponseEntity.ok(authenticatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO) {
        try {
            UserDTO updatedUser = userServiceImpl.updateUser(id, userDTO);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/change-password")
    public ResponseEntity<UserDTO> changePassword(@PathVariable Long id, @RequestBody Map<String, String> passwords) {
        try {
            String currentPassword = passwords.get("currentPassword");
            String newPassword = passwords.get("newPassword");

            if (currentPassword == null || newPassword == null) {
                return ResponseEntity.badRequest().build();
            }

            UserDTO updatedUser = userServiceImpl.changePassword(id, currentPassword, newPassword);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<UserDTO> deactivateUser(@PathVariable Long id) {
        try {
            UserDTO deactivatedUser = userServiceImpl.deactivateUser(id);
            return ResponseEntity.ok(deactivatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/activate")
    public ResponseEntity<UserDTO> activateUser(@PathVariable Long id) {
        try {
            UserDTO activatedUser = userServiceImpl.activateUser(id);
            return ResponseEntity.ok(activatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userServiceImpl.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/stats/active-users-count")
    public ResponseEntity<Map<String, Long>> getActiveUsersCount() {
        Long count = userServiceImpl.getActiveUsersCount();
        return ResponseEntity.ok(Map.of("activeUsers", count));
    }

    @GetMapping("/stats/parking-owners-count")
    public ResponseEntity<Map<String, Long>> getParkingOwnersCount() {
        Long count = userServiceImpl.getParkingOwnersCount();
        return ResponseEntity.ok(Map.of("parkingOwners", count));
    }
}

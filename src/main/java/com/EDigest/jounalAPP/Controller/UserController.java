package com.EDigest.jounalAPP.Controller;



import com.EDigest.jounalAPP.Entity.User;

import com.EDigest.jounalAPP.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
@Tag(name = "User APIs")

public class UserController {

    @Autowired
    private UserService service;
     @Autowired
    PasswordEncoder passwordEncoder;




    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        Optional<User> user = service.getUserById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>( HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable int id) {
        Optional<User> user = service.getUserById(id);
        if (user.isPresent()) {
            service.deleteUserById(id);
            return new ResponseEntity<>("User deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("allUsers")
    public ResponseEntity<String> deleteAllUsers() {
        service.deleteAllUsers();
        return new ResponseEntity<>("All users deleted", HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteLoggedInUser() {
        // Get username of logged-in user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Find the user
        User user = service.findUserByUsername(username);

        if (user == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        service.deleteUserById(user.getId()); // Delete using ID
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<?> updateLoggedInUser(@RequestBody User user) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User existingUser = service.findUserByUsername(username);

        if (existingUser == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        // Safely update password only if it is new and not already encoded
        String newPassword = user.getPassword();
        if (newPassword != null && !newPassword.isBlank()) {
            if (!newPassword.startsWith("$2a$") && !newPassword.startsWith("$2b$")) {
                existingUser.setPassword(passwordEncoder.encode(newPassword));
            } else {
                existingUser.setPassword(newPassword);
            }
        }

        // Optional: update username
        if (user.getUsername() != null) {
            existingUser.setUsername(user.getUsername());
        }

        User updatedUser = service.updateUser(existingUser.getId(), existingUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }






}

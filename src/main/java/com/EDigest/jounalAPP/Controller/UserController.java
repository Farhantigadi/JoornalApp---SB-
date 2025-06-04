package com.EDigest.jounalAPP.Controller;



import com.EDigest.jounalAPP.Entity.User;

import com.EDigest.jounalAPP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;



    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
    }



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
        // Get username of currently authenticated user
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Find the actual user by username
        User existingUser = service.findUserByUsername(username);

        if (existingUser == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }

        // Update fields
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());

        User updatedUser = service.updateUser(existingUser.getId(), existingUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

}

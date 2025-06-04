package com.EDigest.jounalAPP.Controller;

import com.EDigest.jounalAPP.Entity.User;
import com.EDigest.jounalAPP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("public")
public class PublicController {

    @Autowired
    private UserService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<User> register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash password
        user.setRoles(List.of("USER")); // Set default role
        return new ResponseEntity<>(service.saveUser(user), HttpStatus.CREATED); // Save user
    }
}

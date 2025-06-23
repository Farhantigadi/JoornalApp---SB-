package com.EDigest.jounalAPP.Controller;

import com.EDigest.jounalAPP.Entity.User;
import com.EDigest.jounalAPP.SecurityConfig.UserDetailsServiceImpl;
import com.EDigest.jounalAPP.service.UserService;
import com.EDigest.jounalAPP.util.jwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.EDigest.jounalAPP.util.*;

import java.util.List;
@RestController
@RequestMapping("public")
@Slf4j
@Tag(name = "Public APIs")

public class PublicController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    jwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Hash password
        user.setRoles(List.of("USER")); // Set default role
        return new ResponseEntity<>(service.saveUser(user), HttpStatus.CREATED); // Save user
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
            String jwtToken = jwtUtil.generateToken(userDetails.getUsername());
            log.info("Users token created");
            return new ResponseEntity<>(jwtToken, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            log.error("Error during Authentication", e);
            return new ResponseEntity<>("Authentication failed: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}

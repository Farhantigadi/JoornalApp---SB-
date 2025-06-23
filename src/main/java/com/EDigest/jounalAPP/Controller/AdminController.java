package com.EDigest.jounalAPP.Controller;

import com.EDigest.jounalAPP.Entity.User;
import com.EDigest.jounalAPP.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;



@RestController
@Slf4j
@RequestMapping("admin")
@Tag(name = "Admin APIs" , description = "Admin related APIs")
public class AdminController {
    @Autowired
    UserService service;

    @GetMapping("greet")
    public ResponseEntity<?> greet() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            log.info("Get started");
            return new ResponseEntity<>(authentication.getName(),HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Unauthorized user{}");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("create-admin-user")
    @Operation(summary = "Create a new admin and it must be done through admin")
    public ResponseEntity<?> createAdmin(@RequestBody User user){

        return new ResponseEntity<>(service.saveAdmin(user),HttpStatus.CREATED);
    }
    @GetMapping("allUsers")
    public ResponseEntity<?> getAllUsers() {
        try {

            log.info("Get started");
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);

        }
        catch (Exception e){
            log.error("Unauthorized user{}");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

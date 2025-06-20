package com.EDigest.jounalAPP.Controller;

import com.EDigest.jounalAPP.Entity.User;
import com.EDigest.jounalAPP.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@Slf4j
@RequestMapping("admin")
public class AdminController {
    @Autowired
    UserService service;

    @GetMapping("allUser")
    public ResponseEntity<?> getAllUsers() {
        try {
            log.info("Get started");
            return new ResponseEntity<>(service.getAllUsers(), HttpStatus.OK);
        }
        catch (Exception e){
            log.error("Unauthorized user{}");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("create-admin-user")
    public ResponseEntity<?> createAdmin(@RequestBody User user){
        return new ResponseEntity<>(service.saveAdmin(user),HttpStatus.CREATED);
    }
}

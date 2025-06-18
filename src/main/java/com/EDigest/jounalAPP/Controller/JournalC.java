package com.EDigest.jounalAPP.Controller;

import com.EDigest.jounalAPP.Entity.JournalEntity;
import com.EDigest.jounalAPP.Entity.User;
import com.EDigest.jounalAPP.service.JournalService;
import com.EDigest.jounalAPP.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;
@RestController
@RequestMapping("journal")
public class JournalC {

    @Autowired
    private JournalService service;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<JournalEntity>> getAll() {
        try {

            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.findUserByUsername(username);
            List<JournalEntity> journals = service.getByUser(user);
            return new ResponseEntity<>(journals, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody JournalEntity journal) {
        try {
            journal.setDate(LocalDateTime.now());
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            User user = userService.findUserByUsername(username);
            journal.setUser(user);
            JournalEntity savedJournal = service.save(journal);
            return new ResponseEntity<>(savedJournal, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}

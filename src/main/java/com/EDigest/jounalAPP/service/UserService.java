package com.EDigest.jounalAPP.service;



import com.EDigest.jounalAPP.Entity.User;
import com.EDigest.jounalAPP.Repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;  // inject PasswordEncoder

    public User saveUser(User user) {
        // Encode password before saving
        if (!user.getPassword().startsWith("$2a$") && !user.getPassword().startsWith("$2b$")) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(List.of("ROLE_USER"));
        }
        return repo.save(user);
    }
    public User saveAdmin(User user) {
        // Encode password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("ROLE_USER","ROLE_ADMIN"));
        return repo.save(user);
    }

    public Optional<User> getUserById(int id) {
        return repo.findById(id);
    }

    public List<User> getAllUsers() {
        return repo.findAll();
    }

    public void deleteUserById(int id) {
        repo.deleteById(id);
    }

    public void deleteAllUsers() {
        repo.deleteAll();
    }

    public User findUserByUsername(String Username){
        return repo.findByUsername(Username);
    }

    public User updateUser(int id, User updatedUser) {
        Optional<User> existingUser = repo.findById(id);
        if (existingUser.isPresent()) {
            updatedUser.setId(id);
            return repo.save(updatedUser);
        } else {
            return null;
        }
    }
}



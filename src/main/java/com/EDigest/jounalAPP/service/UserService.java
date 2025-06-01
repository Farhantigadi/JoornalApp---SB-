package com.EDigest.jounalAPP.service;



import com.EDigest.jounalAPP.Entity.User;
import com.EDigest.jounalAPP.Repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService  {

    @Autowired
    private UserRepo repo;

    public User saveUser(User user) {
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
            updatedUser.setId(id); // Set ID to ensure it's an update
            return repo.save(updatedUser);
        } else {
            return null;
        }
    }
}


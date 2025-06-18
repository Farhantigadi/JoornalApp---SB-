package com.EDigest.jounalAPP.service;

import com.EDigest.jounalAPP.Entity.JournalEntity;
import com.EDigest.jounalAPP.Entity.User;
import com.EDigest.jounalAPP.Repo.JournalRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JournalService {

    @Autowired
    JournalRepo repo;
    public JournalEntity save(JournalEntity entity){
      repo.save(entity);
        return entity;
    }
    public List<JournalEntity> getAll(){
        return repo.findAll();
    }


    public  void deleteAll(){
      try {
          repo.deleteAll();
      } catch (Exception e){
          log.info("No users found");
      }
    }
    public void DeleteById(int id){
        try {
            repo.deleteById(id);
        } catch (Exception e){
            log.warn("No id found");
        }
    }
    public List<JournalEntity> getByUser(User user) {
        return repo.findAll()
                .stream()
                .filter(journal -> journal.getUser().getId() == user.getId())
                .collect(Collectors.toList());
    }


    /*
    Why Optional<JournalEntity> is returned by findById(id)?
🔍 1. Because the record may or may not exist
The method repo.findById(id) might not find a record in the database with that ID.

Instead of returning null (which can cause NullPointerException), it returns an Optional<JournalEntity>, which is a safe wrapper around the possible result.
     */
    public Optional<JournalEntity> getById(int id){
        return repo.findById(id);

    }
}

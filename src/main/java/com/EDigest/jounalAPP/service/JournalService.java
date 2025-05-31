package com.EDigest.jounalAPP.service;

import com.EDigest.jounalAPP.Entity.JournalEntity;
import com.EDigest.jounalAPP.Repo.JournalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
        repo.deleteAll();
    }
    public void DeleteById(int id){
        repo.deleteById(id);
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

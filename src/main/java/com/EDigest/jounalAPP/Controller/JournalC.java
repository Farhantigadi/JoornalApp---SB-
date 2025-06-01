package com.EDigest.jounalAPP.Controller;

import com.EDigest.jounalAPP.Entity.JournalEntity;
import com.EDigest.jounalAPP.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("journal")
public class JournalC {
    @Autowired
    JournalService service;




    // Get all journals
    @GetMapping
    public ResponseEntity<List<JournalEntity>> getAll() {
        try {
            List<JournalEntity> list = service.getAll();
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a journal
    @PostMapping
    public ResponseEntity<?> post(@RequestBody JournalEntity journal) {
        try {
            journal.setDate(LocalDateTime.now());
            JournalEntity savedJournal = service.save(journal);
            return new ResponseEntity<>(savedJournal, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    // Get journal by ID
    @GetMapping("id/{id}")
    public ResponseEntity<?> getJournalById(@PathVariable int id) {
        Optional<JournalEntity> journal = service.getById(id);
        if (journal.isPresent()){
            return new ResponseEntity<>(journal.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }

    // Delete journal by ID
    @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id) {
     try {
         service.DeleteById(id);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }
     catch (Exception e){

         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
     }
    }
    @DeleteMapping
   public ResponseEntity<?> Delete(){
        service.deleteAll();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
   }

    // Update journal by ID
    @PutMapping("id/{id}")
    public ResponseEntity<?> changeById(@PathVariable int id, @RequestBody JournalEntity updatedJournal) {
     try {
         JournalEntity entity = service.save(updatedJournal);
         return new ResponseEntity<>(entity,HttpStatus.ACCEPTED);
     } catch (Exception e){
         return new ResponseEntity<>(HttpStatus.CONFLICT);
     }
    }
}

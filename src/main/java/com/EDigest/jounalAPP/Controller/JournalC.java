package com.EDigest.jounalAPP.Controller;

import com.EDigest.jounalAPP.Entity.JournalEntity;
import com.EDigest.jounalAPP.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("journal")
public class JournalC {
    @Autowired
    JournalService service;

    // In-memory database simulation
    public Map<Integer, JournalEntity> journalEntry = new HashMap<>();

    // Get all journals
    @GetMapping
    public List<JournalEntity> getAll() {
        return service.getAll();
    }

    // Create a journal
    @PostMapping
    public boolean post(@RequestBody JournalEntity journal) {
        journal.setDate(LocalDateTime.now());
         service.save(journal);
         return true;
    }

    // Get journal by ID
    @GetMapping("id/{id}")
    public Optional<JournalEntity> getJournalById(@PathVariable int id) {
        return service.getById(id);
    }

    // Delete journal by ID
    @DeleteMapping("id/{id}")
    public Boolean deleteById(@PathVariable int id) {
        service.DeleteById(id);
        return true;
    }
    @DeleteMapping
   public Boolean Delete(){
        service.deleteAll();
        return true;
   }

    // Update journal by ID
    @PutMapping("id/{id}")
    public JournalEntity changeById(@PathVariable int id, @RequestBody JournalEntity updatedJournal) {
         service.save(updatedJournal);
         return updatedJournal;
    }
}

package com.EDigest.jounalAPP.Controller;

import com.EDigest.jounalAPP.Entity.JournalEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.*;

@RestController
@RequestMapping("journal")
public class JournalC {

    // In-memory database simulation
    public Map<Integer, JournalEntity> journalEntry = new HashMap<>();

    // Get all journals
    @GetMapping
    public List<JournalEntity> getAll() {
        return new ArrayList<>(journalEntry.values());
    }

    // Create a journal
    @PostMapping
    public boolean post(@RequestBody JournalEntity journal) {
        journalEntry.put(journal.getId(), journal);
        return true;
    }

    // Get journal by ID
    @GetMapping("id/{id}")
    public JournalEntity getJournalById(@PathVariable int id) {
        return journalEntry.get(id);
    }

    // Delete journal by ID
    @DeleteMapping("id/{id}")
    public JournalEntity deleteById(@PathVariable int id) {
        return journalEntry.remove(id);
    }

    // Update journal by ID
    @PutMapping("id/{id}")
    public JournalEntity changeById(@PathVariable int id, @RequestBody JournalEntity updatedJournal) {
        journalEntry.put(id, updatedJournal);
        return updatedJournal;
    }
}

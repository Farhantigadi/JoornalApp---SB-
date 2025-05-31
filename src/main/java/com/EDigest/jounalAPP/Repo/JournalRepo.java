package com.EDigest.jounalAPP.Repo;

import com.EDigest.jounalAPP.Entity.JournalEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalRepo extends JpaRepository<JournalEntity, Integer> {
    // You can add custom query methods here if needed
}

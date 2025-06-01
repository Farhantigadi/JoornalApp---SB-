package com.EDigest.jounalAPP.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class JournalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime date;
    private String journalTitle;
    private String journalDescription;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

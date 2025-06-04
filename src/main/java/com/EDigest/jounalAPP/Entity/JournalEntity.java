package com.EDigest.jounalAPP.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class JournalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;


    @CreationTimestamp
    private LocalDateTime date;

    @ManyToOne
    private User user;


    public void setDate(LocalDateTime now) {
        this.date=now;
    }
}
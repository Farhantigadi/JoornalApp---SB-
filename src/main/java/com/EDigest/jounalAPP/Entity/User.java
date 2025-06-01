package com.EDigest.jounalAPP.Entity;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @Column(unique = true)
    private String username;

    @NonNull
    private String Password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JournalEntity> journals;
}


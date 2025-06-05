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
    private String password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JournalEntity> journals;

    @Convert(converter = StringListConverter.class)
    List<String> roles;

    /*
    @NonNull: Logbook's annotation (ensures these fields aren't null at runtime).

   @Column(unique = true): Username must be unique.

   @OneToMany(...): A user can have many journals.

   cascade = CascadeType.ALL: If you save/delete a user, the journal entries are also saved/deleted.

     orphanRemoval = true tells JPA:
     If you remove a child object (like a journal) from the parent's list (like user's journal list), then delete it from the database too.


     */

}


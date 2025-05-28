package com.EDigest.jounalAPP.Entity;

public class JournalEntity {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJournalTitle() {
        return journalTitle;
    }

    public void setJournalTitle(String journalTitle) {
        this.journalTitle = journalTitle;
    }

    public String getJornalDescription() {
        return jornalDescription;
    }

    public void setJornalDescription(String jornalDescription) {
        this.jornalDescription = jornalDescription;
    }

    private String journalTitle;
    private String jornalDescription;
}

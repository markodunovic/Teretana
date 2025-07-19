package org.unibl.etf.teretana.model;

public class Admin {
    private int id;
    private String ime;
    private String prezime;
    private String koriscnickoIme;
    private String email;
    private String lozinka;

    public Admin(int id, String ime, String prezime, String koriscnickoIme, String email, String lozinka) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.koriscnickoIme = koriscnickoIme;
        this.email = email;
        this.lozinka = lozinka;
    }

    public int getId() {
        return this.id;
    }

    public String getIme() {
        return this.ime;
    }

    public String getPrezime() {
        return this.prezime;
    }
}
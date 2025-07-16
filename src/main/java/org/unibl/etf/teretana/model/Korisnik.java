package org.unibl.etf.teretana.model;

public class Korisnik {
    private int id;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String email;

    public Korisnik(int id, String ime, String prezime, String korisnickoIme, String email) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.email = email;
    }

    public int getId() { return id; }
    public String getIme() { return ime; }
    public String getPrezime() { return prezime; }
    public String getKorisnickoIme() { return korisnickoIme; }
    public String getEmail() { return email; }

    public void setId(int id) { this.id = id; }
    public void setIme(String ime) { this.ime = ime; }
    public void setPrezime(String prezime) { this.prezime = prezime; }
    public void setKorisnickoIme(String korisnickoIme) { this.korisnickoIme = korisnickoIme; }
    public void setEmail(String email) { this.email = email; }
}

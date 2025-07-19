package org.unibl.etf.teretana.model;

import java.util.Date;

public class Radnik {
    private int id;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String email;
    private String lozinka;
    private boolean pol;
    protected Date datumRodjenja;
    private int idAdministratorNapravio;
    private int idAdministratorObrisao;

    public Radnik(int id, String ime, String prezime, String korisnickoIme, String email, String lozinka, boolean pol, Date datumRodjenja, int idAdministratorNapravio) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.email = email;
        this.lozinka = lozinka;
        this.pol = pol;
        this.datumRodjenja = datumRodjenja;
        this.idAdministratorNapravio = idAdministratorNapravio;
        this.idAdministratorObrisao = -1;
    }

    public int getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getEmail() {
        return email;
    }

    public String getLozinka() {
        return lozinka;
    }

    public boolean isPol() {
        return pol;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public int getIdAdministratorNapravio() {
        return idAdministratorNapravio;
    }

    public void setIme(String text) {
        this.ime = text;
    }

    public void setPrezime(String text) {
        this.prezime = text;
    }

    public void setKorisnickoIme(String text) {
        this.korisnickoIme = text;
    }

    public void setEmail(String text) {
        this.email = text;
    }

    public void setLozinka(String text) {
        this.lozinka = text;
    }

    public void setPol(boolean pol) {
        this.pol = pol;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public void setIdAdministratorNapravio(int idAdministratorNapravio) {
        this.idAdministratorNapravio = idAdministratorNapravio;
    }
}
package org.unibl.etf.teretana.model;

import java.util.Date;

public class Korisnik {
    protected int id;
    protected String ime;
    protected String prezime;
    protected String korisnickoIme;
    protected String email;
    protected String lozinka;
    protected boolean pol;
    protected Date datumRodjenja;
    protected boolean primajObavjestenja;

    public Korisnik(int id, String ime, String prezime, String korisnickoIme, String email, String lozinka, boolean pol, Date datumRodjenja, boolean primajObavjestenja) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.email = email;
        this.lozinka = lozinka;
        this.pol = pol;
        this.datumRodjenja = datumRodjenja;
        this.primajObavjestenja = primajObavjestenja;
    }
}

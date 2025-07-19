package org.unibl.etf.teretana.model;

import java.util.Date;

public class Clan extends Korisnik {

    public Clan(int id, String ime, String prezime, String korisnickoIme, String email, String lozinka, boolean pol, Date datumRodjenja, boolean primajObavjestenja) {
        super(id, ime, prezime, korisnickoIme, email, lozinka, pol, datumRodjenja, primajObavjestenja);
    }
}
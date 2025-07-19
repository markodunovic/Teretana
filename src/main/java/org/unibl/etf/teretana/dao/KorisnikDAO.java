package org.unibl.etf.teretana.dao;

import org.unibl.etf.teretana.model.Korisnik;
import org.unibl.etf.teretana.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KorisnikDAO {
    public static List<Korisnik> getAllKorisnici() {
        List<Korisnik> korisnici = new ArrayList<>();

        /*
        String query = "SELECT idKorisnika, Ime, Prezime, KorisnickoIme, Email FROM korisnik";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Korisnik k = new Korisnik(
                        rs.getInt("idKorisnika"),
                        rs.getString("Ime"),
                        rs.getString("Prezime"),
                        rs.getString("KorisnickoIme"),
                        rs.getString("Email")
                );
                korisnici.add(k);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
         */

        return korisnici;
    }
}
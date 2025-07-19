package org.unibl.etf.teretana.dao;

import org.unibl.etf.teretana.model.Admin;
import org.unibl.etf.teretana.model.Clan;
import org.unibl.etf.teretana.model.Radnik;
import org.unibl.etf.teretana.model.Trener;
import org.unibl.etf.teretana.util.DBConnection;

import java.sql.*;

public class LoginDAO {

    public static class LoginResult {
        public Object korisnik;
        public int tip;

        public LoginResult(Object korisnik, int tip) {
            this.korisnik = korisnik;
            this.tip = tip;
        }
    }

    public static LoginResult login(String korisnickoIme, String lozinka) {
        try (Connection conn = DBConnection.getConnection()) {
            // 1 - Admin
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM admin WHERE KorisnickoIme = ? AND Lozinka = ?");
            ps.setString(1, korisnickoIme);
            ps.setString(2, lozinka);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Admin admin = new Admin(rs.getInt("IdAdmina"), rs.getString("Ime"), rs.getString("Prezime"), rs.getString("KorisnickoIme"), rs.getString("Email"),rs.getString("Lozinka"));
                return new LoginResult(admin, 1);
            }

            // 2 - Radnik
            ps = conn.prepareStatement("SELECT * FROM radnik WHERE KorisnickoIme = ? AND Lozinka = ?");
            ps.setString(1, korisnickoIme);
            ps.setString(2, lozinka);
            rs = ps.executeQuery();
            if (rs.next()) {
                Radnik radnik = new Radnik(rs.getInt("IdRadnika"), rs.getString("Ime"), rs.getString("Prezime"), rs.getString("KorisnickoIme"), rs.getString("Email"),rs.getString("Lozinka"), rs.getInt("Pol") == 0, rs.getDate("DatumRodjenja"), rs.getInt("FK_radnik_admin_napravio"));
                return new LoginResult(radnik, 2);
            }

            // Korisnik
            ps = conn.prepareStatement("SELECT * FROM korisnik WHERE KorisnickoIme = ? AND Lozinka = ?");
            ps.setString(1, korisnickoIme);
            ps.setString(2, lozinka);
            rs = ps.executeQuery();
            if (rs.next()) {
                int idKorisnika = rs.getInt("IdKorisnika");

                // 3 - Clan
                PreparedStatement psClan = conn.prepareStatement("SELECT 1 FROM clan WHERE FK_clan_korisnik = ?");
                psClan.setInt(1, idKorisnika);
                ResultSet rsClan = psClan.executeQuery();
                if (rsClan.next()) {
                    Clan clan = new Clan(rsClan.getInt("IdKorisnika"), rsClan.getString("Ime"), rsClan.getString("Prezime"), rsClan.getString("KorisnickoIme"), rsClan.getString("Email"), rsClan.getString("Lozinka"), rsClan.getInt("Pol") == 0, rsClan.getDate("DatumRodjenja"), rs.getInt("PrimajObavjestenja") == 1);
                    return new LoginResult(clan, 3);
                }

                // 4 - Trener
                PreparedStatement psTrener = conn.prepareStatement("SELECT 1 FROM clan WHERE FK_trener_korisnik = ?");
                psClan.setInt(1, idKorisnika);
                ResultSet rsTrener = psTrener.executeQuery();
                if (rsTrener.next()) {
                    Trener trener = new Trener(rs.getInt("IdKorisnika"), rs.getString("Ime"), rs.getString("Prezime"), rs.getString("KorisnickoIme"), rs.getString("Email"),rs.getString("Lozinka"), rs.getInt("Pol") == 0, rs.getDate("DatumRodjenja"), rs.getInt("PrimajObavjestenja") == 1);
                    return new LoginResult(trener, 4);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
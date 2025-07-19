package org.unibl.etf.teretana.dao;

import org.unibl.etf.teretana.model.Radnik;
import org.unibl.etf.teretana.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RadnikDAO {
    private static final String CALL_PROCEDURE = "{call DodajRadnika(?, ?, ?, ?, ?, ?, ?, ?)}";

    public static void dodajRadnika(String ime, String prezime, String korisnickoIme, String email, String lozinka,
                                    int pol, java.sql.Date datumRodjenja, int adminId) throws SQLException {
        try (Connection conn = DBConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(CALL_PROCEDURE)) {

            stmt.setString(1, ime);
            stmt.setString(2, prezime);
            stmt.setString(3, korisnickoIme);
            stmt.setString(4, email);
            stmt.setString(5, lozinka);
            stmt.setInt(6, pol);
            stmt.setDate(7, datumRodjenja);
            stmt.setInt(8, adminId); // FK_radnik_admin_napravio

            stmt.execute();
        }
    }

    public static List<Radnik> getSviRadnici() {
        List<Radnik> lista = new ArrayList<>();

        String query = "SELECT * FROM radnik";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Radnik radnik = new Radnik(
                        rs.getInt("IdRadnika"),
                        rs.getString("Ime"),
                        rs.getString("Prezime"),
                        rs.getString("KorisnickoIme"),
                        rs.getString("Email"),
                        rs.getString("Lozinka"),
                        rs.getInt("Pol") == 1,
                        rs.getDate("DatumRodjenja"),
                        rs.getInt("FK_radnik_admin_napravio")
                );
                lista.add(radnik);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public static boolean delete(int idRadnika) {
        String sql = "DELETE FROM Radnik WHERE IdRadnika = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idRadnika);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean update(Radnik radnik) {
        String sql = "UPDATE Radnik SET Ime = ?, Prezime = ?, KorisnickoIme = ?, Email = ?, Lozinka = ?, Pol = ?, DatumRodjenja = ? WHERE IdRadnika = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, radnik.getIme());
            pstmt.setString(2, radnik.getPrezime());
            pstmt.setString(3, radnik.getKorisnickoIme());
            pstmt.setString(4, radnik.getEmail());
            pstmt.setString(5, radnik.getLozinka());
            pstmt.setBoolean(6, radnik.isPol());
            pstmt.setDate(7, new java.sql.Date(radnik.getDatumRodjenja().getTime()));
            pstmt.setInt(8, radnik.getId());

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
package org.unibl.etf.teretana.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import org.unibl.etf.teretana.dao.RadnikDAO;
import org.unibl.etf.teretana.model.Admin;
import org.unibl.etf.teretana.util.SceneManager;

import java.sql.SQLException;

public class DodajRadnikaController {
    @FXML private TextField imeField;
    @FXML private TextField prezimeField;
    @FXML private TextField korisnickoImeField;
    @FXML private TextField emailField;
    @FXML private PasswordField lozinkaField;
    @FXML private TextField polField;
    @FXML private TextField datumRodjenjaField;
    @FXML private Label statusLabel;

    private Admin admin; // Admin koji dodaje radnika

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @FXML
    private void handleDodajRadnika() {
        try {
            // Validacija unosa
            if (imeField.getText().isEmpty() || prezimeField.getText().isEmpty() ||
                    korisnickoImeField.getText().isEmpty() || emailField.getText().isEmpty() ||
                    lozinkaField.getText().isEmpty() || polField.getText().isEmpty() ||
                    datumRodjenjaField.getText().isEmpty()) {

                statusLabel.setText("Sva polja su obavezna!");
                return;
            }

            int pol = Integer.parseInt(polField.getText());
            if (pol != 0 && pol != 1) {
                statusLabel.setText("Pol mora biti 0 (ženski) ili 1 (muški)");
                return;
            }

            // Poziv DAO metode
            RadnikDAO.dodajRadnika(
                    imeField.getText(),
                    prezimeField.getText(),
                    korisnickoImeField.getText(),
                    emailField.getText(),
                    lozinkaField.getText(),
                    pol,
                    java.sql.Date.valueOf(datumRodjenjaField.getText()),
                    admin.getId()
            );

            statusLabel.setText("Radnik uspješno dodat!");
            resetForma();

        } catch (NumberFormatException e) {
            statusLabel.setText("Pol mora biti broj (0 ili 1)");
        } catch (IllegalArgumentException e) {
            statusLabel.setText("Neispravan format datuma (koristite YYYY-MM-DD)");
        } catch (SQLException e) {
            statusLabel.setText("Greška pri dodavanju radnika: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleNazad() {
        SceneManager.reloadScene("admin");
        SceneManager.switchScene("admin");
    }

    private void resetForma() {
        imeField.clear();
        prezimeField.clear();
        korisnickoImeField.clear();
        emailField.clear();
        lozinkaField.clear();
        polField.clear();
        datumRodjenjaField.clear();
    }
}
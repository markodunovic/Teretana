package org.unibl.etf.teretana.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.unibl.etf.teretana.dao.RadnikDAO;
import org.unibl.etf.teretana.model.Radnik;
import org.unibl.etf.teretana.util.SceneManager;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

public class AzurirajRadnikaController {

    @FXML
    public ChoiceBox polChoiceBox;
    @FXML
    private TextField imeField;
    @FXML
    private TextField prezimeField;
    @FXML
    private TextField korisnickoImeField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField lozinkaField;
    @FXML
    private RadioButton muskiRadio;
    @FXML
    private RadioButton zenskiRadio;
    @FXML
    private DatePicker datumRodjenjaPicker;

    @FXML
    private Button sacuvajButton;
    @FXML
    private Button nazadButton;

    private Radnik trenutniRadnik;

    private ToggleGroup polToggleGroup;

    @FXML
    public void initialize() {
        polToggleGroup = new ToggleGroup();
        muskiRadio.setToggleGroup(polToggleGroup);
        zenskiRadio.setToggleGroup(polToggleGroup);
    }

    public void setRadnik(Radnik radnik) {
        this.trenutniRadnik = radnik;

        imeField.setText(radnik.getIme());
        prezimeField.setText(radnik.getPrezime());
        korisnickoImeField.setText(radnik.getKorisnickoIme());
        emailField.setText(radnik.getEmail());
        lozinkaField.setText(radnik.getLozinka());

        if (radnik.isPol()) {
            muskiRadio.setSelected(true);
        } else {
            zenskiRadio.setSelected(true);
        }

        if (radnik.getDatumRodjenja() != null) {
            java.sql.Date sqlDate = (java.sql.Date) radnik.getDatumRodjenja();
            LocalDate localDate = sqlDate.toLocalDate();
            datumRodjenjaPicker.setValue(localDate);
        }
    }

    @FXML
    private void handleSacuvaj() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potvrda izmjene");
        alert.setHeaderText(null);
        alert.setContentText("Da li ste sigurni da želite sačuvati izmjene?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Ažuriraj objekat radnika sa podacima iz forme
            trenutniRadnik.setIme(imeField.getText());
            trenutniRadnik.setPrezime(prezimeField.getText());
            trenutniRadnik.setKorisnickoIme(korisnickoImeField.getText());
            trenutniRadnik.setEmail(emailField.getText());
            trenutniRadnik.setLozinka(lozinkaField.getText());
            trenutniRadnik.setPol(muskiRadio.isSelected());

            LocalDate datum = datumRodjenjaPicker.getValue();
            if (datum != null) {
                Date date = Date.from(datum.atStartOfDay(ZoneId.systemDefault()).toInstant());
                trenutniRadnik.setDatumRodjenja(date);
            } else {
                trenutniRadnik.setDatumRodjenja(null);
            }

            boolean success = RadnikDAO.update(trenutniRadnik);
            if (success) {
                Alert info = new Alert(Alert.AlertType.INFORMATION);
                info.setTitle("Uspjeh");
                info.setHeaderText(null);
                info.setContentText("Podaci uspješno ažurirani.");
                info.showAndWait();

                // Vrati se na pregled radnika
                SceneManager.switchScene("pregledRadnika");
            } else {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Greška");
                error.setHeaderText(null);
                error.setContentText("Došlo je do greške prilikom ažuriranja podataka.");
                error.showAndWait();
            }
        }
    }

    @FXML
    private void handleNazad() {
        // Jednostavno se vrati na prethodnu scenu
        SceneManager.reloadScene("pregledRadnika");
        SceneManager.switchScene("pregledRadnika");
    }
}
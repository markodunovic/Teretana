package org.unibl.etf.teretana;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.unibl.etf.teretana.controller.AdminController;
import org.unibl.etf.teretana.dao.LoginDAO;
import org.unibl.etf.teretana.model.Admin;
import org.unibl.etf.teretana.util.SceneManager;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField korisnickoImeField;
    @FXML
    private PasswordField lozinkaField;
    @FXML
    private Button prijavaButton;
    @FXML
    private Button registracijaButton;
    @FXML
    private Label greskaLabel;

    @FXML
    protected void prijavaButton(ActionEvent event) throws IOException {
        String korisnickoIme = korisnickoImeField.getText();
        String lozinka = lozinkaField.getText();

        LoginDAO.LoginResult rezultat = LoginDAO.login(korisnickoIme, lozinka);

        if (rezultat == null) {
            greskaLabel.setText("Pogresno korisnicko ime ili lozinka!");
            return;
        }

        if (rezultat.tip == 1) {
            AdminController adminController = SceneManager.getController("admin");
            adminController.initData((Admin) rezultat.korisnik);
            SceneManager.switchScene("admin");
        } else if (rezultat.tip == 2) {
            //otvoriView("radnik-fxml");
        } else if (rezultat.tip == 3) {
            //otvoriView("clan-fxml");
        } else {
            //otvoriView("trener-fxml");
        }
    }

    /*
    private void otvoriAdminView(String fxml, Admin admin) throws IOException {
        // Učitavanje FXML datoteke
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        System.out.println("Putanja do FXML: " + getClass().getResource(fxml));
        Parent root = loader.load();

        // Dobijanje reference na trenutni stage
        Stage stage = (Stage) korisnickoImeField.getScene().getWindow();

        // Postavljanje nove scene
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Teretana - Administratorski panel");
        stage.setResizable(true);

        AdminController controller = loader.getController();
        controller.initData(admin);
    }
    */
}
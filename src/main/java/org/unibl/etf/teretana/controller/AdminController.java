package org.unibl.etf.teretana.controller;

import javafx.fxml.FXML;
import org.unibl.etf.teretana.model.Admin;
import org.unibl.etf.teretana.util.SceneManager;

public class AdminController {
    private Admin admin;

    public void initData(Admin admin) {
        this.admin = admin;
        // Postavite pozdravnu poruku ako je potrebno
    }

    @FXML
    private void handleDodajRadnika() {
        // Preuzmi kontroler i proslijedi admina
        DodajRadnikaController controller = SceneManager.getController("dodajRadnika");
        controller.setAdmin(admin);

        // Prebaci na scenu
        SceneManager.reloadScene("dodajRadnika");
        SceneManager.switchScene("dodajRadnika");
    }

    @FXML
    private void handlePregledRadnika() {
        // Implementacija za pregled radnika
        SceneManager.reloadScene("pregledRadnika");
        SceneManager.switchScene("pregledRadnika");
    }

    @FXML
    private void handlePregledKvarova() {
        // Implementacija za pregled kvarova
    }
}
package org.unibl.etf.teretana;

import javafx.application.Application;
import javafx.stage.Stage;
import org.unibl.etf.teretana.util.SceneManager;

public class Login extends Application {
    @Override
    public void start(Stage stage) {
        SceneManager.initialize(stage);

        SceneManager.loadScene("login", "/org/unibl/etf/teretana/login-view.fxml");
        SceneManager.loadScene("admin", "/org/unibl/etf/teretana/admin-view.fxml");
        SceneManager.loadScene("dodajRadnika", "/org/unibl/etf/teretana/dodaj-radnika-view.fxml");
        SceneManager.loadScene("pregledRadnika", "/org/unibl/etf/teretana/pregled-radnika-view.fxml");
        SceneManager.loadScene("azurirajRadnika", "/org/unibl/etf/teretana/azuriraj-radnika-view.fxml");

        SceneManager.switchScene("login");
        stage.setTitle("Teretana");
        stage.show();
    }
}
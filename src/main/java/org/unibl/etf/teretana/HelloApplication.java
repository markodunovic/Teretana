package org.unibl.etf.teretana;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.unibl.etf.teretana.dao.KorisnikDAO;
import org.unibl.etf.teretana.model.Korisnik;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        List<Korisnik> korisnici = KorisnikDAO.getAllKorisnici();
        System.out.println("Ispis");
        for (Korisnik k : korisnici) {
            System.out.println(k.getIme() + " " + k.getPrezime() + " | " + k.getEmail());
        }
        launch();
    }
}
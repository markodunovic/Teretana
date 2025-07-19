package org.unibl.etf.teretana.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SceneManager {
    private static Stage primaryStage;
    private static final Map<String, String> scenePaths = new HashMap<>();
    private static final Map<String, Parent> scenes = new HashMap<>();
    private static final Map<String, Object> controllers = new HashMap<>();

    public static void initialize(Stage stage) {
        primaryStage = stage;
    }

    public static void loadScene(String name, String fxmlPath) {
        scenePaths.put(name, fxmlPath);
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(SceneManager.class.getResource(fxmlPath)));
            Parent root = loader.load();
            scenes.put(name, root);
            controllers.put(name, loader.getController());
        } catch (IOException e) {
            System.err.println("Greška pri učitavanju scene: " + name);
            e.printStackTrace();
        }
    }

    public static void switchScene(String name) {
        if (!scenes.containsKey(name)) {
            // Ako nije prethodno učitana scena, probaj je sada učitati
            if (scenePaths.containsKey(name)) {
                loadScene(name, scenePaths.get(name));
            } else {
                System.err.println("Scena '" + name + "' nije registrovana.");
                return;
            }
        }
        primaryStage.setScene(new Scene(scenes.get(name)));
        primaryStage.show();
    }

    public static <T> T getController(String name) {
        return (T) controllers.get(name);
    }

    // Omogućava ponovno učitavanje određene scene
    public static void reloadScene(String name) {
        if (!scenePaths.containsKey(name)) {
            System.err.println("Scene '" + name + "' nije registrovana.");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(SceneManager.class.getResource(scenePaths.get(name))));
            Parent root = loader.load();
            scenes.put(name, root);
            controllers.put(name, loader.getController());
        } catch (IOException e) {
            System.err.println("Greška pri ponovnom učitavanju scene: " + name);
            e.printStackTrace();
        }
    }

    // Uklanja scenu iz cache-a
    public static void unloadScene(String name) {
        scenes.remove(name);
        controllers.remove(name);
    }
}
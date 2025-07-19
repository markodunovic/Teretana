package org.unibl.etf.teretana.controller;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.unibl.etf.teretana.dao.RadnikDAO;
import org.unibl.etf.teretana.model.Radnik;
import org.unibl.etf.teretana.util.SceneManager;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class PregledRadnikaController {

    @FXML
    private TableView<Radnik> radniciTable;

    @FXML
    private TableColumn<Radnik, Integer> idColumn;

    @FXML
    private TableColumn<Radnik, String> imeColumn;

    @FXML
    private TableColumn<Radnik, String> prezimeColumn;

    @FXML
    private TableColumn<Radnik, String> korisnickoImeColumn;

    @FXML
    private TableColumn<Radnik, String> emailColumn;

    @FXML
    private TableColumn<Radnik, String> lozinkaColumn;

    @FXML
    private TableColumn<Radnik, String> polColumn;

    @FXML
    private TableColumn<Radnik, Date> datumRodjenjaColumn;

    @FXML
    private TableColumn<Radnik, Integer> fkRadnikAdminNapravioColumn;

    @FXML
    private TableColumn<Radnik, Void> obrisiColumn;

    @FXML
    private TableColumn<Radnik, Void> azurirajColumn;

    private ObservableList<Radnik> radnici;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        imeColumn.setCellValueFactory(new PropertyValueFactory<>("Ime"));
        prezimeColumn.setCellValueFactory(new PropertyValueFactory<>("Prezime"));
        korisnickoImeColumn.setCellValueFactory(new PropertyValueFactory<>("KorisnickoIme"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        lozinkaColumn.setCellValueFactory(new PropertyValueFactory<>("Lozinka"));
        polColumn.setCellValueFactory(cellData -> {
            boolean isPol = cellData.getValue().isPol();
            String polTekst = isPol ? "Muski" : "Zenski";
            return new ReadOnlyStringWrapper(polTekst);
        });
        datumRodjenjaColumn.setCellValueFactory(new PropertyValueFactory<>("DatumRodjenja"));
        fkRadnikAdminNapravioColumn.setCellValueFactory(new PropertyValueFactory<>("IdAdministratorNapravio"));

        List<Radnik> radniciLista = RadnikDAO.getSviRadnici();
        ObservableList<Radnik> radniciObservable = FXCollections.observableArrayList(radniciLista);
        radniciTable.setItems(radniciObservable);

        radnici = radniciObservable;

        addObrisiButtonToTable();
        addAzurirajButtonToTable();
    }

    @FXML
    private void handleNazad() {
        SceneManager.reloadScene("admin");
        SceneManager.switchScene("admin");
    }

    private void addObrisiButtonToTable() {
        Callback<TableColumn<Radnik, Void>, TableCell<Radnik, Void>> cellFactory = param -> new TableCell<>() {
            private final Button btn = new Button("Obriši");

            {
                btn.setOnAction(event -> {
                    Radnik radnik = getTableView().getItems().get(getIndex());

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Potvrda brisanja");
                    alert.setHeaderText(null);
                    alert.setContentText("Da li ste sigurni da želite obrisati nalog radnika " + radnik.getIme() + " " + radnik.getPrezime() + "?");

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        boolean success = RadnikDAO.delete(radnik.getId());
                        if (success) {
                            radnici.remove(radnik);
                        } else {
                            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                            errorAlert.setTitle("Greška");
                            errorAlert.setHeaderText(null);
                            errorAlert.setContentText("Brisanje naloga nije uspjelo.");
                            errorAlert.showAndWait();
                        }
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        };
        obrisiColumn.setCellFactory(cellFactory);
    }

    private void addAzurirajButtonToTable() {
        Callback<TableColumn<Radnik, Void>, TableCell<Radnik, Void>> cellFactory = param -> new TableCell<>() {
            private final Button btn = new Button("Ažuriraj");

            {
                btn.setOnAction(event -> {
                    Radnik radnik = getTableView().getItems().get(getIndex());

                    // Postavi radnika u kontroler update scene
                    AzurirajRadnikaController controller = SceneManager.getController("azurirajRadnika");
                    if (controller != null) {
                        controller.setRadnik(radnik);
                    }

                    SceneManager.switchScene("azurirajRadnika");
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btn);
                }
            }
        };
        azurirajColumn.setCellFactory(cellFactory);
    }
}
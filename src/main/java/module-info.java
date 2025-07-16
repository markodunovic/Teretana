module org.unibl.etf.teretana {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens org.unibl.etf.teretana to javafx.fxml;
    exports org.unibl.etf.teretana;
}
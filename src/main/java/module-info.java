module org.unibl.etf.teretana {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens org.unibl.etf.teretana to javafx.fxml;
    opens org.unibl.etf.teretana.controller to javafx.fxml;
    exports org.unibl.etf.teretana;
    exports org.unibl.etf.teretana.controller;
    exports org.unibl.etf.teretana.util;
    opens org.unibl.etf.teretana.util to javafx.fxml;
    opens org.unibl.etf.teretana.model to javafx.base;
}
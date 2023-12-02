module group06 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens group06 to javafx.fxml;
    exports group06;
}

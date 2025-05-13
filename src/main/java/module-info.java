module com.kpz2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.kpz2.ui to javafx.fxml;
    exports com.kpz2.ui;
}

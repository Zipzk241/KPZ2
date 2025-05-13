module com.kpz2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.kpz2 to javafx.fxml;
    exports com.kpz2;
}
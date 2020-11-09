module com.mycompany.latihanrpl {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.latihanrpl to javafx.fxml;
    exports com.mycompany.latihanrpl;
}

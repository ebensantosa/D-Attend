module com.mycompany.latihanrpl {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires jasperreports; 
    opens com.mycompany.latihanrpl to javafx.fxml;
    exports com.mycompany.latihanrpl;
}

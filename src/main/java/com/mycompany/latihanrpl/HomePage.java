package com.mycompany.latihanrpl;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomePage {

    @FXML
    private void Logout() throws IOException {
//        Stage stage = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
//        Scene scene = new Scene(root,400,400);
//        stage.setScene(scene);
//        stage.show();
          App.setRoot("login");
    }
}
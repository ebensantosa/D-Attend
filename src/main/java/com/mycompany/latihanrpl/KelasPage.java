/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.latihanrpl;

import java.io.IOException;
import javafx.fxml.FXML;

public class KelasPage {
    @FXML
    private void Logout() throws IOException {
          App.setRoot("login");
    }
}

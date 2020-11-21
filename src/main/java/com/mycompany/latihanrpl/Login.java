package com.mycompany.latihanrpl;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author asus
 */
public class Login implements Initializable{
    public LoginConnection loginConn = new LoginConnection();
    
    @FXML
    private Label txtLabel;
    
    @FXML
    private TextField txtEmail;
    
    @FXML
    private PasswordField txtPassword;
    
    @FXML
    void login(ActionEvent event) {
    if(txtEmail.getText().isEmpty()== false && txtPassword.getText().isEmpty() == false){
           validateLogin();
       }else {
           txtLabel.setText("Masukkan email dan password");
       }
   }
   
   public void validateLogin(){       
       Connection conn = DBConnector.getInstance().getConnection();
       String email = txtEmail.getText();
       String password = txtPassword.getText();
       String verifyLogin = "SELECT count(1) FROM User WHERE email = '" 
               + email + "' AND password = '" + password + "'";
       
       try {
           Statement statement = conn.createStatement();
           ResultSet queryResult = statement.executeQuery(verifyLogin);
           
           while (queryResult.next()){
               if(queryResult.getInt(1) == 1) {
                   //loginMessageLabel11.setText("Login Berhasil ");
//                   Stage stage = new Stage();
//                   Parent root = FXMLLoader.load(getClass().getResource("secondary.fxml"));
//                   Scene scene = new Scene(root,400,400);
//                   stage.setScene(scene);
//                   stage.show();
                     App.setRoot("HomePage");
               } else {
                   txtLabel.setText("Login Gagal,Coba lagi !!!");
               }
           }
       } catch (Exception e){
           e.printStackTrace();
           e.getCause();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(loginConn.isDBConnected()){
//            isConnected.setText("Connected");
            System.out.println("Connected");
        } else{
//            isConnected.setText("Not Connected");
            System.out.println("Not Connected");
        }    
    }

    private Parent loadFXML(String login) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
package com.mycompany.latihanrpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HomePage {
    
    @FXML
    private TextField kodeKelas;
    @FXML
    private TextField namaKelas;
    @FXML
    private TextField idDosen;
    @FXML
    private Label labelStatus;
    
    @FXML 
    public void insert(ActionEvent event) throws SQLException{
       if(kodeKelas.getText().isEmpty() == false && namaKelas.getText().isEmpty() == false && idDosen.getText().isEmpty() == false){
           Connection conn = DBConnector.getInstance().getConnection();
           String insertData = "INSERT INTO kelas values('" + kodeKelas.getText() + "','" + namaKelas.getText() + "','" + idDosen.getText() + "')";
           Statement st = conn.createStatement();
            try{
                st.executeUpdate(insertData);
                labelStatus.setText("Input Data Sukses");
            }catch(Exception e){
                e.printStackTrace();
                labelStatus.setText("Input Data Gagal");
            }
       }else{
           labelStatus.setText("Semua data harus terisi");
       }
    }

    @FXML
    private void Logout() throws IOException {
          App.setRoot("login");
    }
    }
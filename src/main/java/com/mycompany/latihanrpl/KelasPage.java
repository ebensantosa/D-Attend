/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.latihanrpl;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class KelasPage implements Initializable{
    
    @FXML
    private TextField noPertemuan;
    @FXML
    private TableView<Pertemuan> tvPertemuan;
    @FXML
    private TableColumn<Pertemuan, Integer> colIdDosen;
    @FXML
    private TableColumn<Pertemuan, String> colMingguPertemuan;
    @FXML
    private Button btnGo;
    
    
    @FXML
    private void Logout() throws IOException {
          App.setRoot("login");
    }
    
    public ObservableList<Pertemuan> getPertemuanList(){
        ObservableList<Pertemuan> pertemuanList = FXCollections.observableArrayList();
        Connection conn = DBConnector.getInstance().getConnection();
        String query = "SELECT id_pertemuan,minggu FROM pertemuan";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Pertemuan pertemuan;
            while(rs.next()){
                pertemuan = new Pertemuan(rs.getInt("id_pertemuan"), rs.getString("minggu"));
                pertemuanList.add(pertemuan);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return pertemuanList;
    }
    
    private void showPertemuan(){
        ObservableList<Pertemuan> list = getPertemuanList();
        
        colIdDosen.setCellValueFactory(new PropertyValueFactory<Pertemuan, Integer>("id_pertemuan"));
        colMingguPertemuan.setCellValueFactory(new PropertyValueFactory<Pertemuan, String>("minggu"));        
        
        tvPertemuan.setItems(list);
    }
    
    @FXML
    private void Go() throws IOException{
        App.setRoot("MahasiswaPage");
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException{        
        if(event.getSource() == btnGo){
            Go();
        }
    }
    
    @FXML
    private void handleMouseAction(MouseEvent event){
        Pertemuan pertemuan = tvPertemuan.getSelectionModel().getSelectedItem();
        noPertemuan.setText("" + pertemuan.getId_Pertemuan());
    }
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        showPertemuan();
    }
}

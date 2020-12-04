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

/**
 *
 * @author asus
 */
public class DosenPage implements Initializable{
    
    @FXML
    private TextField kodeKelas;
    @FXML
    private TableView<Kelas> tvKelas;
    @FXML
    private TableColumn<Kelas, String> colKodeKelas;
    @FXML
    private TableColumn<Kelas, String> colNamaKelas;
    @FXML
    private Button btnGO;
            
    @FXML
    private void Logout() throws IOException {
          App.setRoot("login");
    }
    @FXML
    private void Go() throws IOException{
        App.setRoot("KelasPage");
    }

    public ObservableList<Kelas> getKelasList(){
        ObservableList<Kelas> kelasList = FXCollections.observableArrayList();
        Connection conn = DBConnector.getInstance().getConnection();
        String query = "SELECT * FROM Kelas";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Kelas kelas;
            while(rs.next()){
                kelas = new Kelas(rs.getString("kode_kelas"), rs.getString("nama_kelas"));
                kelasList.add(kelas);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return kelasList;
    }
    
    private void showKelas(){
        ObservableList<Kelas> list = getKelasList();
        
        colKodeKelas.setCellValueFactory(new PropertyValueFactory<Kelas, String>("kode_kelas"));
        colNamaKelas.setCellValueFactory(new PropertyValueFactory<Kelas, String>("nama_kelas"));        
        
        tvKelas.setItems(list);
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException{        
        if(event.getSource() == btnGO){
            Go();
        }
    }
    
    @FXML
    private void handleMouseAction(MouseEvent event){
        Kelas kelas = tvKelas.getSelectionModel().getSelectedItem();
        kodeKelas.setText(kelas.getKode_kelas());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showKelas();
    }

}

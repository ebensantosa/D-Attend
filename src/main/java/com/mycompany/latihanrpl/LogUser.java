/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.latihanrpl;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class LogUser implements Initializable{

    @FXML
    private TableView<User> tvLog;
    
    @FXML
    private TableColumn<User, Integer> colNomor;

    @FXML
    private TableColumn<User, String> colEmail;

    @FXML
    private TableColumn<User, String> colWaktu;

    @FXML
    void Logout(ActionEvent event) throws IOException {
        App.setRoot("login");
    }
    
    @FXML
    void Kembali(ActionEvent event) throws IOException {
        App.setRoot("AdminPage");
    }
    
    public ObservableList<User> getLogUser(){
        ObservableList<User> userList = FXCollections.observableArrayList();
        String query = "SELECT * FROM log_user ";        
        Statement st;
        ResultSet rs;        
        
        try{
            st = Login.conn.createStatement();            
            rs = st.executeQuery(query);
            User user;
            while(rs.next()){
                user = new User(rs.getInt("id_log"), rs.getString("email_user"), rs.getString("waktu_login"));         
                userList.add(user);                
            }            
        } catch(Exception e){
            e.printStackTrace();
        }
        return userList;
    }
    
    public void showUser(){
        ObservableList<User> list = getLogUser();                      
        
        colNomor.setCellValueFactory(new PropertyValueFactory<User, Integer>("idLog"));
        colEmail.setCellValueFactory(new PropertyValueFactory<User, String>("emailUser"));
        colWaktu.setCellValueFactory(new PropertyValueFactory<User, String>("waktuLogin"));        
        tvLog.setItems(list);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showUser();
    }
    
}
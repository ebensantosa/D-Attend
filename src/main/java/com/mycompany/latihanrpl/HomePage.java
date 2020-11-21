package com.mycompany.latihanrpl;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

public class HomePage{ 
    
    @FXML
    private TextField kodeKelas;
    
    @FXML
    private TextField namaKelas;
    
    @FXML
    private TextField idDosen;
    
    @FXML
    private Label labelStatus;
    
    @FXML
    private TableView<Kelas> tblKelas;
    
    @FXML
    private TableColumn<Kelas, String> kode_kelas;
    
    @FXML
    private TableColumn<Kelas, String> nama_kelas;
    
    @FXML
    private TableColumn<Kelas, String> dosen_pengajar;
    
    @FXML
    private TableColumn<Kelas, Button> action;
    

    @FXML
    private void Logout() throws IOException {
          App.setRoot("login");
    }    
    
    @FXML 
    public void insert(ActionEvent event) throws SQLException{
       if(kodeKelas.getText().isEmpty() == false && namaKelas.getText().isEmpty() == false && idDosen.getText().isEmpty() == false){
           Connection conn = DBConnector.getInstance().getConnection();
           String insertData = "INSERT INTO kelas values('" + kodeKelas.getText() + "','" + namaKelas.getText() + "','" + idDosen.getText() + "')";
           String verifyData = "SELECT count(1) FROM kelas WHERE kode_kelas = '" + kodeKelas.getText() + "'";
           
           try {
            Statement statement = conn.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyData);
           
                while (queryResult.next()){
                    if(queryResult.getInt(1) == 1) {
                        labelStatus.setText("Tambah Data Gagal! Kelas dengan kode " + kodeKelas.getText() + " sudah pernah dibuat");
                    }else {
                        Statement st = conn.createStatement();
                        try{
                            st.executeUpdate(insertData);
                            labelStatus.setText("Input Data Sukses");
                        }catch(SQLException e){
                            e.printStackTrace();
                            labelStatus.setText("Input Data Gagal");
                        }
                    }
                }
           }catch (Exception e){
                e.printStackTrace();
                e.getCause();
           }
           
       }else{
           labelStatus.setText("Semua data harus terisi");
       }
    }
    
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        Statement
//    }
    
//    private void initTable(){
//        initCols();
//    }
//    
//    private void initCols(){
////        ObservableList<Kelas> list = getListKelas();
//        
//        kode_kelas.setCellValueFactory(new PropertyValueFactory<>("kode_kelas"));
//        nama_kelas.setCellValueFactory(new PropertyValueFactory<>("nama_kelas"));
//        dosen_pengajar.setCellValueFactory(new PropertyValueFactory<>("dosen_pengajar"));
//        action.setCellValueFactory(new PropertyValueFactory<>("update"));
//        
////        tblKelas.setItems(list);
//    }
//    
//    private void updateCols(){
//        kode_kelas.setCellFactory(TextFieldTableCell.forTableColumn());
//        kode_kelas.setOnEditCommit(e -> {
//                e.getTableView().getItems().get(e.getTablePosition().getRow()).setKode_kelas(e.getNewValue());
//        });
//
//        nama_kelas.setCellFactory(TextFieldTableCell.forTableColumn());
//        nama_kelas.setOnEditCommit(e -> {
//                e.getTableView().getItems().get(e.getTablePosition().getRow()).setNama_kelas(e.getNewValue());
//        });
//        
//        dosen_pengajar.setCellFactory(TextFieldTableCell.forTableColumn());
//        dosen_pengajar.setOnEditCommit(e -> {
//                e.getTableView().getItems().get(e.getTablePosition().getRow()).setDosen_pengajar(e.getNewValue());
//        });
//        
//        tblKelas.setEditable(true);
//    }
//    
////    private void loadData(){
////        ObservableList<Kelas> tblData = FXCollections.observableArrayList();        
////        
////        for(int i=0; i<7; i++){
////            tblData.add(new Kelas(String.valueOf(i), "nama_kelas" + i, "dosen_pengajar" + 1, new Button("update")));
////            tblKelas.setItems(tblData);
////        }
////    }
//    
//    public ObservableList<Kelas> getListKelas(){
//        ObservableList<Kelas> tblData = FXCollections.observableArrayList();        
//        Connection conn = DBConnector.getInstance().getConnection();
//        String query = "SELECT * FROM kelas";
//        Statement st;
//        ResultSet rs;
//        
//        try{
//            st = conn.createStatement();
//            rs = st.executeQuery(query);            
//            while(rs.next()){                
//                tblData.add(new Kelas(rs.getString("kode_kelas"), rs.getString("nama_kelas"), rs.getString("dosen_pengajar"), new Button("update")));
//                tblKelas.setItems(tblData);
//            }
//        } catch(Exception e){
//            e.printStackTrace();
//        }
//        return tblData;
//    }
}    
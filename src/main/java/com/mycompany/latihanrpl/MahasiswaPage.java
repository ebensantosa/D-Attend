/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.latihanrpl;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class MahasiswaPage implements Initializable{
    @FXML
    private TableView<Mahasiswa> tvMahasiswa;

    @FXML
    private TableColumn<Mahasiswa, String> colNIM;

    @FXML
    private TableColumn<Mahasiswa, String> colNamaMhs;

    @FXML
    private Button btnTambah;

    @FXML
    private TextField tfNIM;
    
    @FXML
    private Label labelStatus;

    @FXML
    void Kembali(ActionEvent event) throws IOException {
        App.setRoot("KelasPage");
    }
    
    @FXML
    void Logout(ActionEvent event) throws IOException {
        App.setRoot("login");
    }
    
    public ObservableList<Mahasiswa> getMahasiswaList(){
        ObservableList<Mahasiswa> mahasiswaList = FXCollections.observableArrayList();
        String query = "SELECT * FROM Mahasiswa";
        Statement st;
        ResultSet rs;
        
        try{
            st = Login.conn.createStatement();
            rs = st.executeQuery(query);
            Mahasiswa mahasiswa;
            while(rs.next()){
                mahasiswa = new Mahasiswa(rs.getString("nim"), rs.getString("nama_mahasiswa"));
                mahasiswaList.add(mahasiswa);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return mahasiswaList;
    }
    
    private void showMahasiswa(){
        ObservableList<Mahasiswa> list = getMahasiswaList();
        
        colNIM.setCellValueFactory(new PropertyValueFactory<Mahasiswa, String>("nim"));
        colNamaMhs.setCellValueFactory(new PropertyValueFactory<Mahasiswa, String>("namaMhs"));
        
        tvMahasiswa.setItems(list);
    }
    
    @FXML
    private void handleMouseAction(MouseEvent event){
        Mahasiswa mahasiswa = tvMahasiswa.getSelectionModel().getSelectedItem();
        tfNIM.setText(mahasiswa.getNim());        
    }
    
    @FXML
    private void tambah(){                
        String insertData = "INSERT INTO presensi(nim, kode_kelas, pertemuan) "
                + "values('" + tfNIM.getText() + "', '"+ AdminPage.getKodeKelas() +"', '1')";
        String verifyData = "SELECT count(1) FROM presensi "
                + "WHERE kode_kelas = '" + AdminPage.getKodeKelas() + "' "
                + "AND nim = '"+ tfNIM.getText() +"'";
        try {
        Statement statement = Login.conn.createStatement();
        ResultSet queryResult = statement.executeQuery(verifyData);

            while (queryResult.next()){
                if(queryResult.getInt(1) == 1) {
                    labelStatus.setText("Tambah Data Gagal! Mahasiswa dengan NIM " + tfNIM.getText() + " sudah pernah dibuat");
                }else {
                    Statement st = Login.conn.createStatement();
                    try{
                        st.executeUpdate(insertData);
                        labelStatus.setText("Tambah Mahasiswa Sukses");
                    }catch(SQLException e){
                        e.printStackTrace();
                        labelStatus.setText("Tambah Mahasiswa Gagal");
                    }
                }
            }
       }catch (Exception e){
            e.printStackTrace();
            e.getCause();
       }                  
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException{
        System.out.println("Tombol Berhasil");
        labelStatus.setText("Berhasil");
        if (event.getSource() == btnTambah){
            tambah();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showMahasiswa();
    }
}

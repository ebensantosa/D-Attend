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
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
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

public class KelasPage implements Initializable{
    
    private static int pertemuan;    
        
    @FXML
    private TableView<Presensi> tvKelas;
    @FXML
    private TableColumn<Presensi, String> colNim;
    @FXML
    private TableColumn<Presensi, String> colNamaMhs;
    @FXML
    private TableColumn<Presensi, Integer> colPertemuan;
    @FXML
    private TableColumn<Presensi, String> colStatus;
    @FXML
    private TableColumn<Presensi, String> colWaktu;
    @FXML
    private Label labelKelas;
    
    
    public static int getPertemuan(){
        return pertemuan;
    }    
    
    @FXML
    private void Logout() throws IOException {
          App.setRoot("login");
    }
    
    public ObservableList<Presensi> getPresensiList(){
        ObservableList<Presensi> presensiList = FXCollections.observableArrayList();
        Connection conn = DBConnector.getInstance().getConnection();
        String query = "SELECT k.nama_kelas AS nama_kelas, m.nim AS nim, m.nama_mahasiswa AS nama_mahasiswa, p.status AS status, p.waktu AS waktu, p.pertemuan AS pertemuan, p.id_presensi AS id_presensi "
                + "FROM Kelas k INNER JOIN Presensi p ON k.kode_kelas = p.kode_kelas "
                + "INNER JOIN Mahasiswa m ON p.nim = m.nim "
                + "WHERE p.kode_kelas = '" + DosenPage.getKodeKelas() + "'";
        Statement st;
        ResultSet rs;        
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Presensi presensi;
            while(rs.next()){
                presensi = new Presensi(rs.getString("nim"), rs.getString("status"), rs.getString("waktu"), rs.getInt("id_presensi"), rs.getInt("pertemuan"));
                presensi.setNama_mahasiwa(rs.getString("nama_mahasiswa"));
                presensiList.add(presensi);
                labelKelas.setText(rs.getString("nama_kelas"));
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return presensiList;
    }
    
    public void showPresensi(){
        ObservableList<Presensi> list = getPresensiList();
        tvKelas.setItems(list);
        
        colNim.setCellValueFactory(new PropertyValueFactory<Presensi, String>("nim"));
        colNamaMhs.setCellValueFactory(new PropertyValueFactory<Presensi, String>("nama_mahasiswa"));
        colPertemuan.setCellValueFactory(new PropertyValueFactory<Presensi, Integer>("pertemuan"));
        colStatus.setCellValueFactory(new PropertyValueFactory<Presensi, String>("status"));
        colWaktu.setCellValueFactory(new PropertyValueFactory<Presensi, String>("waktu"));
        
        tvKelas.setItems(list);
    }       
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException{        
        
    }
    
    @FXML
    private void handleMouseAction(MouseEvent event) throws SQLException{
        Presensi presensi = tvKelas.getSelectionModel().getSelectedItem();
        
        Connection conn = DBConnector.getInstance().getConnection();
        Statement st = conn.createStatement();
        
        DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if(presensi.getStatus().equals("Hadir")){
            String query = "UPDATE presensi SET status = 'Tidak Hadir', waktu = '" + formatTanggal.format(LocalDateTime.now()) + "' "
                    + "WHERE id_presensi = " + presensi.getId_presensi();
            st.execute(query);
        } else if(presensi.getStatus().equals("Tidak Hadir")){
            String query2 = "UPDATE presensi SET status = 'Hadir', waktu = '-' "
                    + "WHERE id_presensi = " + presensi.getId_presensi();
            st.execute(query2);
        }
        showPresensi();
    }
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        Connection conn = DBConnector.getInstance().getConnection();
        Statement st;
        ResultSet rs;
        try{
            String query = "SELECT MAX(pertemuan) AS pertemuan FROM Presensi "
                    + "WHERE kode_kelas = '" + DosenPage.getKodeKelas() + "'";
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        showPresensi();
    }
}

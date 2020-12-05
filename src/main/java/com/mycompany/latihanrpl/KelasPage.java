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
    private static int idPresensi;
        
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
    @FXML
    private Button btnHadir;
    @FXML
    private Button btnAbsen;
    @FXML
    private Button btnPertemuan;
    
    
    public static int getPertemuan(){
        return pertemuan;
    }    
    
    @FXML
    private void Logout() throws IOException {
          App.setRoot("login");
    }
    
    @FXML private void Kembali() throws IOException{
        if(Login.getIsAdmin() == 1){
            App.setRoot("AdminPage");
        }else{
            App.setRoot("DosenPage");
        }
    }
    
    public ObservableList<Presensi> getPresensiList(){
        ObservableList<Presensi> presensiList = FXCollections.observableArrayList();
//        Connection conn = DBConnector.getInstance().getConnection();
        String query = "SELECT k.nama_kelas AS nama_kelas, m.nim AS nim, m.nama_mahasiswa AS nama_mahasiswa, p.status AS status, p.waktu AS waktu, p.pertemuan AS pertemuan, p.id_presensi AS id_presensi "
                + "FROM Kelas k INNER JOIN Presensi p ON k.kode_kelas = p.kode_kelas "
                + "INNER JOIN Mahasiswa m ON p.nim = m.nim "
                + "WHERE p.kode_kelas = '" + DosenPage.getKodeKelas() + "' AND p.pertemuan = " + pertemuan;
        String query2 = "SELECT k.nama_kelas AS nama_kelas, m.nim AS nim, m.nama_mahasiswa AS nama_mahasiswa, p.status AS status, p.waktu AS waktu, p.pertemuan AS pertemuan, p.id_presensi AS id_presensi "
                + "FROM Kelas k INNER JOIN Presensi p ON k.kode_kelas = p.kode_kelas "
                + "INNER JOIN Mahasiswa m ON p.nim = m.nim "
                + "WHERE p.kode_kelas = '" + AdminPage.getKodeKelas() + "' AND p.pertemuan = " + pertemuan;
        Statement st;
        ResultSet rs;        
        
        try{
            st = Login.conn.createStatement();
            if(Login.getIsAdmin() == 1){
                rs = st.executeQuery(query2);   
            }else{
                rs = st.executeQuery(query);   
            }
            Presensi presensi;
            while(rs.next()){
                presensi = new Presensi(rs.getString("nim"), rs.getString("status"), rs.getString("waktu"), rs.getInt("id_presensi"), rs.getInt("pertemuan"));
                presensi.setNamaMahasiswa(rs.getString("nama_mahasiswa"));                
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
        
        colNim.setCellValueFactory(new PropertyValueFactory<Presensi, String>("nim"));
        colNamaMhs.setCellValueFactory(new PropertyValueFactory<Presensi, String>("namaMahasiswa"));
        colPertemuan.setCellValueFactory(new PropertyValueFactory<Presensi, Integer>("pertemuan"));
        colStatus.setCellValueFactory(new PropertyValueFactory<Presensi, String>("status"));
        colWaktu.setCellValueFactory(new PropertyValueFactory<Presensi, String>("waktu"));
        
        tvKelas.setItems(list);
    }       
    
    @FXML
    private void mahasiswaHadir(){               
        DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String queryHadir = "UPDATE presensi SET status = 'Hadir', waktu = '" + formatTanggal.format(LocalDateTime.now()) + "' "
                    + "WHERE id_presensi = " + idPresensi + ";";
        try{
            Statement st = Login.conn.createStatement();
            st.executeUpdate(queryHadir);            
            showPresensi();
        }catch(SQLException e){
            e.printStackTrace();            
        }
    }
    
    @FXML
    private void mahasiswaAbsen(){                      
        String queryAbsen = "UPDATE presensi SET status = 'Tidak Hadir', waktu = '-' "
                    + "WHERE id_presensi = " + idPresensi + ";";
        try{
            Statement st = Login.conn.createStatement();
            st.executeUpdate(queryAbsen);            
            showPresensi();            
        }catch(SQLException e){
            e.printStackTrace();            
        }
    }
    
    @FXML
    private void buatPertemuan() throws IOException{        
        pertemuan++;
        
        Statement st;
        ResultSet rs;
        try{
            if(Login.getIsAdmin() == 1){
                String queryPert = "SELECT * FROM Presensi "
                    + "WHERE kode_kelas = '" + AdminPage.getKodeKelas() + "' "
                    + "GROUP BY nim";
                st = Login.conn.createStatement();
                rs = st.executeQuery(queryPert);
            } else{
                String queryPert = "SELECT * FROM Presensi "
                    + "WHERE kode_kelas = '" + DosenPage.getKodeKelas() + "' "
                    + "GROUP BY nim";
                st = Login.conn.createStatement();
                rs = st.executeQuery(queryPert);
            }            
            while(rs.next()){                
                String queryInsert = "INSERT INTO Presensi (nim, kode_kelas, pertemuan) "
                        + "VALUES('" + rs.getString("nim") + "', '" + rs.getString("kode_kelas") + "', " + pertemuan + ")";
                Statement st2 = Login.conn.createStatement();
                st2.execute(queryInsert);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        showPresensi();
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException{                
        if(event.getSource() == btnHadir){
            mahasiswaHadir();
        } else if(event.getSource() == btnAbsen){
            mahasiswaAbsen();
        } else if(event.getSource() == btnPertemuan){
            buatPertemuan();
        }
    }
    
    @FXML
    private void handleMouseAction(MouseEvent event) throws SQLException{
        Presensi presensi = tvKelas.getSelectionModel().getSelectedItem();
        idPresensi = presensi.getIdPresensi();
//        DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        if(presensi.getStatus().equals("Hadir")){
//            String query = "UPDATE presensi SET status = 'Tidak Hadir', waktu = '" + formatTanggal.format(LocalDateTime.now()) + "' "
//                    + "WHERE id_presensi = " + presensi.getIdPresensi();
//            try{
//                Connection conn = DBConnector.getInstance().getConnection();
//                Statement st = conn.createStatement();
//                st.executeUpdate(query);
//                conn.close();
//            }catch(SQLException e){
//                e.printStackTrace();
//            }
//        } else if(presensi.getStatus().equals("Tidak Hadir")){
//            try{
//                Connection conn = DBConnector.getInstance().getConnection();
//                Statement st = conn.createStatement();
//                String query = "UPDATE presensi SET status = 'Hadir', waktu = '-' "
//                    + "WHERE id_presensi = " + presensi.getIdPresensi();
//                st.executeUpdate(query);
//                conn.close();
//            }catch(SQLException e){
//                e.printStackTrace();
//            }
//        }
//        showPresensi();
    }
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
//        Connection conn = DBConnector.getInstance().getConnection();
        Statement st;
        ResultSet rs;
        try{
            String query = "SELECT MAX(pertemuan) AS pertemuan FROM Presensi "
                    + "WHERE kode_kelas = '" + DosenPage.getKodeKelas() + "'";
            String query2 = "SELECT MAX(pertemuan) AS pertemuan FROM Presensi "
                    + "WHERE kode_kelas = '" + AdminPage.getKodeKelas() + "'";
            st = Login.conn.createStatement();
            if(Login.getIsAdmin() == 1){
                rs = st.executeQuery(query2);
            }else{
                rs = st.executeQuery(query);
            }
            while(rs.next()){
                pertemuan = rs.getInt("pertemuan");
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        showPresensi();
    }
}
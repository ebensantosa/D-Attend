package com.mycompany.latihanrpl;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AdminPage implements Initializable{
      
    @FXML
    private TextField kodeKelas;
    @FXML
    private TextField kodeKelas1;
    @FXML
    private TextField namaKelas;
    @FXML
    private TextField idDosen;
    @FXML
    private Label labelStatus;
    @FXML
    private TableView<Kelas> tvKelas;
    @FXML
    private TableColumn<Kelas, String> colKodeKelas;
    @FXML
    private TableColumn<Kelas, String> colNamaKelas;
    @FXML
    private TableColumn<Kelas, String> colIdDosen1;    
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnGo;
    @FXML
    private TableView<Dosen> tvDosen;
    @FXML
    private TableColumn<Dosen, Integer> colIdDosen2;
    @FXML
    private TableColumn<Dosen, String> colNamaDosen;
    
    private static String detailKodeKelas;
    
    public static String getKodeKelas(){
        return detailKodeKelas;
    }
    
    
    @FXML 
    public void insert(){
       if(kodeKelas.getText().isEmpty() == false && namaKelas.getText().isEmpty() == false && idDosen.getText().isEmpty() == false){
           String insertData = "INSERT INTO kelas values('" + kodeKelas.getText() + "','" + namaKelas.getText() + "','" + idDosen.getText() + "')";
           String verifyData = "SELECT count(1) FROM kelas WHERE kode_kelas = '" + kodeKelas.getText() + "'";
           
           try {
            Statement statement = Login.conn.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyData);
           
                while (queryResult.next()){
                    if(queryResult.getInt(1) == 1) {
                        labelStatus.setText("Tambah Data Gagal! Kelas dengan kode " + kodeKelas.getText() + " sudah pernah dibuat");
                    }else {
                        Statement st = Login.conn.createStatement();
                        try{
                            st.executeUpdate(insertData);
                            labelStatus.setText("Input Data Sukses");
                            showKelas();
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
    
    @FXML
    private void update(){
        String query = "UPDATE kelas SET nama_kelas ='" + namaKelas.getText() +"',id_dosen=" + idDosen.getText() + " WHERE kode_kelas = '" + kodeKelas.getText() + "'";
        try{
            Statement st = Login.conn.createStatement();
            st.executeUpdate(query);
            labelStatus.setText("Update Data Sukses");
            showKelas();
        }catch(SQLException e){
            e.printStackTrace();
            labelStatus.setText("Update Data Gagal");
        }
    }
    
    @FXML
    public void delete(){
        String query = "DELETE FROM kelas WHERE kode_kelas='" + kodeKelas.getText() + "'";
        try{
            Statement st = Login.conn.createStatement();
            st.executeUpdate(query);
            labelStatus.setText("Hapus Data Sukses");
            showKelas();
        }catch(SQLException e){
            e.printStackTrace();
            labelStatus.setText("Hapus Data Gagal");
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException{
        System.out.println("Tombol Berhasil");
        labelStatus.setText("Berhasil");
        if (event.getSource() == btnInsert){
            insert();
        }else if(event.getSource() == btnUpdate){
            update();
        }else if(event.getSource() == btnDelete){
            delete();
        }else if(event.getSource() == btnGo){
            Go();
        }
    }
    
     @FXML
    private void Go() throws IOException{
        PreparedStatement ps;
        ResultSet rs;
        try{
           String query = "SELECT kode_kelas FROM Kelas WHERE kode_kelas = '" + kodeKelas.getText() + "'";
           ps = Login.conn.prepareStatement(query);
           rs = ps.executeQuery();
           if(rs.next()){
               detailKodeKelas = kodeKelas.getText();
               App.setRoot("KelasPage");
           } else{
               labelStatus.setText("Kode Kelas tidak ditemukan!");
           }
        } catch(Exception e){
            e.printStackTrace();
        }       
    }
    
    @FXML
    private void handleMouseAction(MouseEvent event){
        Kelas kelas = tvKelas.getSelectionModel().getSelectedItem();
        kodeKelas.setText(kelas.getKode_kelas());
        namaKelas.setText(kelas.getNama_kelas());
        idDosen.setText("" + kelas.getId_dosen());
        kodeKelas1.setText(kelas.getKode_kelas());
    }
    
    @FXML
    private void Logout() throws IOException {
          App.setRoot("login");
    }
    @FXML 
    private void Lihat() throws IOException {
        App.setRoot("LogUser");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showKelas();
        showDosen();
    }
    
    public ObservableList<Kelas> getKelasList(){
        ObservableList<Kelas> kelasList = FXCollections.observableArrayList();
        String query = "SELECT kode_kelas, nama_kelas, id_dosen FROM Kelas";
        Statement st;
        ResultSet rs;
        
        try{
            st = Login.conn.createStatement();
            rs = st.executeQuery(query);
            Kelas kelas;
            while(rs.next()){
                kelas = new Kelas(rs.getString("kode_kelas"), rs.getString("nama_kelas"), rs.getInt("id_dosen"));
                kelasList.add(kelas);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return kelasList;
    }
    
    public ObservableList<Dosen> getDosenList(){
        ObservableList<Dosen> dosenList = FXCollections.observableArrayList();
        String query = "SELECT * FROM Dosen";
        Statement st;
        ResultSet rs;
        
        try{
            st = Login.conn.createStatement();
            rs = st.executeQuery(query);
            Dosen dosen;
            while(rs.next()){
                dosen = new Dosen(rs.getInt("id_dosen"), rs.getString("nama_dosen"));
                dosenList.add(dosen);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return dosenList;
    }
    
    private void showKelas(){
        ObservableList<Kelas> list = getKelasList();
        
        colKodeKelas.setCellValueFactory(new PropertyValueFactory<Kelas, String>("kode_kelas"));
        colNamaKelas.setCellValueFactory(new PropertyValueFactory<Kelas, String>("nama_kelas"));
        colIdDosen1.setCellValueFactory(new PropertyValueFactory<Kelas, String>("id_dosen"));
        
        tvKelas.setItems(list);
    }
    
    private void showDosen(){
        ObservableList<Dosen> list = getDosenList();
        
        colIdDosen2.setCellValueFactory(new PropertyValueFactory<Dosen, Integer>("id_dosen"));
        colNamaDosen.setCellValueFactory(new PropertyValueFactory<Dosen, String>("nama_dosen"));
        
        tvDosen.setItems(list);
    }
}
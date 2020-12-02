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
import javafx.stage.Stage;

public class AdminPage implements Initializable{
      
    @FXML
    private TextField kodeKelas;
    @FXML
    private TextField namaKelas;
    @FXML
    private TextField dosen;
    @FXML
    private Label labelStatus;
    @FXML
    private TableView<Kelas> tvKelas;
    @FXML
    private TableColumn<Kelas, String> colKodeKelas;
    @FXML
    private TableColumn<Kelas, String> colNamaKelas;
    @FXML
    private TableColumn<Kelas, String> colDosen;    
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    
//    @FXML 
//    public void insert(ActionEvent event) throws SQLException{
//       if(kodeKelas.getText().isEmpty() == false && namaKelas.getText().isEmpty() == false && dosen.getText().isEmpty() == false){
//           Connection conn = DBConnector.getInstance().getConnection();
//           String insertData = "INSERT INTO kelas values('" + kodeKelas.getText() + "','" + namaKelas.getText() + "','" + dosen.getText() + "')";
//           String verifyData = "SELECT count(1) FROM kelas WHERE kode_kelas = '" + kodeKelas.getText() + "'";
//           
//           try {
//            Statement statement = conn.createStatement();
//            ResultSet queryResult = statement.executeQuery(verifyData);
//           
//                while (queryResult.next()){
//                    if(queryResult.getInt(1) == 1) {
//                        labelStatus.setText("Tambah Data Gagal! Kelas dengan kode " + kodeKelas.getText() + " sudah pernah dibuat");
//                    }else {
//                        Statement st = conn.createStatement();
//                        try{
//                            st.executeUpdate(insertData);
//                            labelStatus.setText("Input Data Sukses");
//                        }catch(SQLException e){
//                            e.printStackTrace();
//                            labelStatus.setText("Input Data Gagal");
//                        }
//                    }
//                }
//           }catch (Exception e){
//                e.printStackTrace();
//                e.getCause();
//           }
//           
//       }else{
//           labelStatus.setText("Semua data harus terisi");
//       }
//    }
//    
//    @FXML
//    public void deleteData(){
//        
//    }

    @FXML
    private void handleButtonAction(ActionEvent event){
        System.out.println("Tombol Berhasil");
        labelStatus.setText("Berhasil");
    }
    
    @FXML
    private void Logout() throws IOException {
          App.setRoot("login");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showKelas();
    }
    
    public ObservableList<Kelas> getKelasList(){
        ObservableList<Kelas> kelasList = FXCollections.observableArrayList();
        Connection conn = DBConnector.getInstance().getConnection();
//        String query = "SELECT * FROM Kelas";
        String query = "SELECT k.kode_kelas AS kode_kelas, k.nama_kelas AS nama_kelas, d.nama_dosen AS nama_dosen "
                + "FROM Kelas k INNER JOIN Dosen d ON k.id_dosen = d.id_dosen";
        Statement st;
        ResultSet rs;
        
        try{
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Kelas kelas;
            while(rs.next()){
                kelas = new Kelas(rs.getString("kode_kelas"), rs.getString("nama_kelas"), rs.getString("nama_dosen"));
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
        colDosen.setCellValueFactory(new PropertyValueFactory<Kelas, String>("nama_dosen"));
        
        tvKelas.setItems(list);
    }
}
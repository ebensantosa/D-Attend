/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.latihanrpl;

import static com.mycompany.latihanrpl.Login.conn;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.time.LocalDateTime.now;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class LaporanPage implements Initializable{            
    @FXML
    private Label labelKelas;
    
    @FXML
    private TableView<Laporan> tvLaporan;
    
    @FXML
    private TableColumn<Laporan, Integer> colPertemuan;

    @FXML
    private TableColumn<Laporan, Integer>  colHadir;

    @FXML
    private TableColumn<Laporan, Integer>  colTidakHadir;

    @FXML
    private TableColumn<Laporan, Integer>  colTotal;
    
    @FXML
    private TableColumn<Laporan, String> colWaktu;
    
    @FXML
    private TextField tfPrint;
     
    @FXML
    private TextField filterField;
    
    @FXML
    private void Logout() throws IOException {
          App.setRoot("login");
    }
    
    @FXML private void kembali() throws IOException{
        if(Login.getIsAdmin() == 1){
            App.setRoot("AdminPage");
        }else{
            App.setRoot("DosenPage");
        }
    }            
    
    public ObservableList<Laporan> getLaporanList(){
        ObservableList<Laporan> laporanList = FXCollections.observableArrayList();
        String query = "SELECT pertemuan, COUNT(CASE WHEN status = 'Hadir' THEN 1 END) AS Hadir, COUNT(CASE WHEN status = 'Tidak Hadir' THEN 1 END) AS TidakHadir, COUNT(NIM) AS Total, substr(waktu, 1, 10) AS waktu "
                + "FROM presensi WHERE kode_kelas = '"+DosenPage.getKodeKelas()+ "' "
                + "GROUP BY pertemuan" ;
        String query2 = "SELECT pertemuan, COUNT(CASE WHEN status = 'Hadir' THEN 1 END) AS Hadir, COUNT(CASE WHEN status = 'Tidak Hadir' THEN 1 END) AS TidakHadir, COUNT(NIM) AS Total, substr(waktu, 1, 10) AS waktu "
                + "FROM presensi WHERE kode_kelas = '"+AdminPage.getKodeKelas()+ "' "
                + "GROUP BY pertemuan" ;
        Statement st;
        ResultSet rs;        
        
        try{
            st = Login.conn.createStatement();
            if(Login.getIsAdmin() == 1){
                rs = st.executeQuery(query2);   
            }else{
                rs = st.executeQuery(query);   
            }
            Laporan laporan;
            while(rs.next()){
                laporan = new Laporan(rs.getInt("pertemuan"), rs.getInt("Hadir"), rs.getInt("TidakHadir"), rs.getInt("Total"), rs.getString("waktu"));         
                laporanList.add(laporan);
                labelKelas.setText(KelasPage.getNamaKelas());
            }            
        } catch(Exception e){
            e.printStackTrace();
        }
        return laporanList;
    }
       
      public void showLaporan(){
        ObservableList<Laporan> list = getLaporanList();                      
        
        colPertemuan.setCellValueFactory(new PropertyValueFactory<Laporan, Integer>("pertemuan"));
        colHadir.setCellValueFactory(new PropertyValueFactory<Laporan, Integer>("hadir"));
        colTidakHadir.setCellValueFactory(new PropertyValueFactory<Laporan, Integer>("tidakHadir"));
        colTotal.setCellValueFactory(new PropertyValueFactory<Laporan, Integer>("total"));
        colWaktu.setCellValueFactory(new PropertyValueFactory<Laporan, String>("waktu"));
        tvLaporan.setItems(list);
    }
      
    @FXML
    void filter_waktu() {          
        ObservableList<Laporan> list = getLaporanList();                      
        
        colPertemuan.setCellValueFactory(new PropertyValueFactory<Laporan, Integer>("pertemuan"));
        colHadir.setCellValueFactory(new PropertyValueFactory<Laporan, Integer>("hadir"));
        colTidakHadir.setCellValueFactory(new PropertyValueFactory<Laporan, Integer>("tidakHadir"));
        colTotal.setCellValueFactory(new PropertyValueFactory<Laporan, Integer>("total"));
        colWaktu.setCellValueFactory(new PropertyValueFactory<Laporan, String>("waktu"));
        tvLaporan.setItems(list);
        
        FilteredList<Laporan> filteredData = new FilteredList<>(list, b -> true);  
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(laporan -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }    
            String lowerCaseFilter = newValue.toLowerCase();

            if (laporan.getWaktu().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                return true;
            } else  
                return false;
           });
        });  
        SortedList<Laporan> sortedData = new SortedList<>(filteredData);  
        sortedData.comparatorProperty().bind(tvLaporan.comparatorProperty());  
        tvLaporan.setItems(sortedData);      
    }
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      showLaporan();
      filter_waktu();
    } 
    
    @FXML
    private void handleMouseAction(MouseEvent event){
        Laporan laporan = tvLaporan.getSelectionModel().getSelectedItem();
        tfPrint.setText("" + laporan.getPertemuan());
    }
    
     @FXML
    public void showLaporan(ActionEvent event) throws JRException {              
        try{           
            if(Login.getIsAdmin()==1){
                String bebas = " SELECT pertemuan, kode_kelas FROM presensi WHERE pertemuan = '"  + tfPrint.getText() + "'AND kode_kelas = '" + AdminPage.getKodeKelas() + "'";   
                Statement statement = Login.conn.createStatement();
                ResultSet rs = statement.executeQuery(bebas);
            }else{
                String bebas = " SELECT pertemuan, kode_kelas FROM presensi WHERE pertemuan = '"  + tfPrint.getText() + "'AND kode_kelas = '" + DosenPage.getKodeKelas() + "'"; 
                Statement statement = Login.conn.createStatement();
                ResultSet rs = statement.executeQuery(bebas);
            }
                       
            String Jasperdalan = ("G:\\RPL\\D-Attend\\src\\main\\java\\com\\mycompany\\latihanrpl\\Blank_A4.jrxml");
            HashMap Jaspernya = new HashMap();
            Jaspernya.put("LaporanJasper", tfPrint.getText());
            if(Login.getIsAdmin()==1){
                Jaspernya.put("KodeKelas", AdminPage.getKodeKelas());
            }else{
                Jaspernya.put("KodeKelas", DosenPage.getKodeKelas());
            }
            JasperReport JasperReport  = JasperCompileManager.compileReport(Jasperdalan);
            JasperPrint printThis = JasperFillManager.fillReport(JasperReport, Jaspernya, Login.conn);
            JasperViewer.viewReport(printThis, false);
        }catch (Exception e){
             System.out.println(e.getMessage());
        }
    }
    
    
}
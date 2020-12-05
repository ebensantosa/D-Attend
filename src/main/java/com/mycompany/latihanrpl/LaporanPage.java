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
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
    
//    @FXML
//    private LineChart<?, ?> chartLaporan;
     
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

//    private void lineChart(){
//        final NumberAxis xAxis = new NumberAxis();
//        final NumberAxis yAxis = new NumberAxis();
//        xAxis.setLabel("Number of Month");
//        //creating the chart
//        chartLaporan = 
//                new LineChart<Number,Number>(xAxis,yAxis);
//
//        chartLaporan.setTitle("Stock Monitoring, 2010");
//        //defining a series
//        XYChart.Series series = new XYChart.Series();
//        series.setName("My portfolio");
//        //populating the series with data         
//        try 
//        {            
//            Statement stmt = Login.conn.createStatement();
//            ResultSet rs = stmt.executeQuery("select pertemuan, COUNT(CASE WHEN status = 'Hadir' THEN 1 END) AS Hadir, COUNT(CASE WHEN status = 'Tidak Hadir' THEN 1 END) AS TidakHadir, COUNT(NIM) AS Total "
//                + "FROM presensi WHERE kode_kelas = '"+DosenPage.getKodeKelas()+ "' "
//                + "GROUP BY pertemuan");
//
//            //Iterate through results.
//            while(rs.next())
//            {
//                series.getData().add(new XYChart.Data(rs.getInt("pertemuan"), rs.getInt("Hadir")));//Add data to Chart. Changed the second input to Integer due to LineChart<Number,Number>. This should work, though I haven't tested it.
//            }
//        }
//        catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        chartLaporan.getData().add(series);        
//    }  
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      showLaporan();
      filter_waktu();
//      lineChart();
    } 
    
}
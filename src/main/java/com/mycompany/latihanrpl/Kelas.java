/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.latihanrpl;

import javafx.scene.control.Button;

/**
 *
 * @author asus
 */
public class Kelas {
    String kode_kelas, nama_kelas, dosen_pengajar;
    
    Button update;
    
    public Kelas(String kode_kelas, String nama_kelas, String dosen_pengajar, Button update){
        this.kode_kelas = kode_kelas;
        this.nama_kelas = nama_kelas;
        this.dosen_pengajar = dosen_pengajar;
        this.update = update;
        
//        update.setOnAction(e->{
//            
//        });
    }
    
    public String getKode_kelas(){
        return kode_kelas;
    }
    
    public String getNama_kelas(){
        return nama_kelas;
    }
    
    public String getDosen_pengajar(){
        return dosen_pengajar;
    }
    
    public Button getUpdate(){
        return update;
    }
    
    public void setKode_kelas(String kode_kelas){
        this.kode_kelas = kode_kelas;
    }
    
    public void setNama_kelas(String nama_kelas){
        this.nama_kelas = nama_kelas;
    }
    
    public void setDosen_pengajar(String dosen_pengajar){
        this.dosen_pengajar = dosen_pengajar;
    }
    
    public void setUpdate(Button update){
        this.update = update;
    }
    
    
}

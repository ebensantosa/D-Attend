/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.latihanrpl;

/**
 *
 * @author HEZKIEL
 */
public class Dosen {
    int id_dosen;
    String nama_dosen;
    
    public Dosen(int id_dosen,String nama_dosen){
        this.id_dosen = id_dosen;
        this.nama_dosen = nama_dosen;
    }
    
    public int getId_dosen(){
        return id_dosen;
    }
    
    public String getNama_dosen(){
        return nama_dosen;
    }
    
    public void setId_dosen(int id_dosen){
        this.id_dosen = id_dosen;
    }
    
    public void setNama_dosen(String nama_dosen){
        this.nama_dosen = nama_dosen;
    }
}
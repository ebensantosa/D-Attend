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
public class Pertemuan {
    int id_pertemuan;
    String minggu;
    
    public Pertemuan(int id_pertemuan,String minggu){
        this.id_pertemuan = id_pertemuan;
        this.minggu = minggu;
    }
    
    public String getMinggu(){
        return minggu;
    }
    public int getId_Pertemuan(){
        return id_pertemuan;
    }
    public void setMinggu(String minggu){
        this.minggu = minggu;
    }
    public void setId_Pertemuan(int id_pertemuan){
        this.id_pertemuan = id_pertemuan;
    }
}

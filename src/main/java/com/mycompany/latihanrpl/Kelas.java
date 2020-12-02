/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.latihanrpl;

import javafx.scene.control.Button;

public class Kelas {
    String kode_kelas, nama_kelas, nama_dosen;
    
    public Kelas(String kode_kelas, String nama_kelas, String nama_dosen){
        this.kode_kelas = kode_kelas;
        this.nama_kelas = nama_kelas;
        this.nama_dosen = nama_dosen;
    }

    public String getKode_kelas() {
        return kode_kelas;
    }

    public void setKode_kelas(String kode_kelas) {
        this.kode_kelas = kode_kelas;
    }

    public String getNama_kelas() {
        return nama_kelas;
    }

    public void setNama_kelas(String nama_kelas) {
        this.nama_kelas = nama_kelas;
    }

    public String getNama_dosen() {
        return nama_dosen;
    }

    public void setNama_dosen(String nama_dosen) {
        this.nama_dosen = nama_dosen;
    }
    
    
}
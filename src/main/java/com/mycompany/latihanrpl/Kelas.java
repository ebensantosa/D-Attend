/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.latihanrpl;

public class Kelas {
    String kode_kelas, nama_kelas;
    int id_dosen;
    
    public Kelas(String kode_kelas, String nama_kelas, int id_dosen){
        this.kode_kelas = kode_kelas;
        this.nama_kelas = nama_kelas;
        this.id_dosen = id_dosen;
    }

    public Kelas(String kode_kelas, String nama_kelas) {
        this.kode_kelas = kode_kelas;
        this.nama_kelas = nama_kelas;
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

    public int getId_dosen() {
        return id_dosen;
    }

    public void setId_dosen(int id_dosen) {
        this.id_dosen = id_dosen;
    }
    
    
}
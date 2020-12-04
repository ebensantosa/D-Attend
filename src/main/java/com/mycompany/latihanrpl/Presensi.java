/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.latihanrpl;

public class Presensi {
    String nim, nama_mahasiswa, status, waktu;
    int id_presensi, pertemuan;

    public Presensi(String nim, String status, String waktu, int id_presensi, int pertemuan) {
        this.nim = nim;        
        this.status = status;
        this.waktu = waktu;
        this.id_presensi = id_presensi;
        this.pertemuan = pertemuan;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama_mahasiwa() {
        return nama_mahasiswa;
    }

    public void setNama_mahasiwa(String nama_mahasiwa) {
        this.nama_mahasiswa = nama_mahasiswa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public int getId_presensi() {
        return id_presensi;
    }

    public void setId_presensi(int id_presensi) {
        this.id_presensi = id_presensi;
    }
    
    public int getPertemuan() {
        return pertemuan;
    }

    public void setPertemuan(int pertemuan) {
        this.pertemuan = pertemuan;
    }
     
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.latihanrpl;

public class Presensi {
    private String nim, namaMahasiswa, status, waktu;
    private int idPresensi, pertemuan;

    public Presensi(String nim, String status, String waktu, int idPresensi, int pertemuan) {
        this.nim = nim;        
        this.status = status;
        this.waktu = waktu;
        this.idPresensi = idPresensi;
        this.pertemuan = pertemuan;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }

    public void setNamaMahasiswa(String namaMahasiswa) {
        this.namaMahasiswa = namaMahasiswa;
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

    public int getIdPresensi() {
        return idPresensi;
    }

    public void setIdPresensi(int idPresensi) {
        this.idPresensi = idPresensi;
    }  
    
    public int getPertemuan() {
        return pertemuan;
    }

    public void setPertemuan(int pertemuan) {
        this.pertemuan = pertemuan;
    }
     
}
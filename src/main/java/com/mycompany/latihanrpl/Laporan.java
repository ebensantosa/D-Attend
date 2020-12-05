/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.latihanrpl;

public class Laporan {
   private int pertemuan, hadir, tidakHadir, total;
   private String waktu;

    public Laporan(int pertemuan, int hadir, int tidakHadir, int total, String waktu) {
        this.pertemuan = pertemuan;
        this.hadir = hadir;
        this.tidakHadir = tidakHadir;
        this.total = total;
        this.waktu = waktu;
    }

    public int getPertemuan() {
        return pertemuan;
    }

    public void setPertemuan(int pertemuan) {
        this.pertemuan = pertemuan;
    }

    public int getHadir() {
        return hadir;
    }

    public void setHadir(int hadir) {
        this.hadir = hadir;
    }

    public int getTidakHadir() {
        return tidakHadir;
    }

    public void setTidakHadir(int tidakHadir) {
        this.tidakHadir = tidakHadir;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }
      
}

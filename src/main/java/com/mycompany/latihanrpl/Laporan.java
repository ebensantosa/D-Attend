/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.latihanrpl;

/**
 *
 * @author Eben
 */
public class Laporan {
   private int pertemuan, hadir, tidakHadir, total;

    public Laporan(int pertemuan, int hadir, int tidakHadir, int total) {
        this.pertemuan = pertemuan;
        this.hadir = hadir;
        this.tidakHadir = tidakHadir;
        this.total = total;
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
   
   
   
   
}

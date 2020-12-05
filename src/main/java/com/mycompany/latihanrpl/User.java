/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.latihanrpl;


public class User {
    private int idLog;
    private String emailUser, waktuLogin;

    public User(int idLog, String emailUser, String waktuLogin) {
        this.idLog = idLog;
        this.emailUser = emailUser;
        this.waktuLogin = waktuLogin;
    }

    public int getIdLog() {
        return idLog;
    }

    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getWaktuLogin() {
        return waktuLogin;
    }

    public void setWaktuLogin(String waktuLogin) {
        this.waktuLogin = waktuLogin;
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.latihanrpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author asus
 */
public class LoginConnection {
    Connection conn;
    public LoginConnection(){
        conn = DBConnector.getInstance().getConnection();       
        if(conn == null) System.exit(1);
    }
    
    public boolean isDBConnected(){
        try {
            return !conn.isClosed();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
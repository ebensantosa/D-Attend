/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.latihanrpl;

import java.io.*;
import java.lang.*;
import java.util.*;
import java.sql.*;
/**
 *
 * @author asus
 */
public class DBConnector {
    
    private DBConnector(){
        
    }
    
    public static DBConnector getInstance(){
        return new DBConnector();
    }
    
    public Connection getConnection(){
        String connectString = "jdbc:sqlite:user.db";
        Connection connection = null;
        try{
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(connectString);
            return connection;
        } catch(Exception e){ 
            e.printStackTrace();
            return null;
        }      
    }
    
//    public static void executeQuery(String query){
//        Connection conn = getConnection();
//        Statement st;
//        try{
//            st = conn.createStatement();
//            st.executeUpdate(query);
//        } catch(Exception e){
//            e.printStackTrace();
//        }
//    }
}
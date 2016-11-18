package com.dominic.mortgage.models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBManager {
    final String TAG = DBManager.class.getName();
    public ResultSet SELECT_QUERY(String query, Connection conn) {
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            return rs;
        } catch (SQLException ex) {
            return null;
        }
    }

    public boolean EXECUTE_QUERY(String query, Connection conn) {
        try {
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            
            return true;
        } catch (SQLException ex) { 
        	//Log.e(TAG, ex.getMessage());
            return false;
        }
    }

}
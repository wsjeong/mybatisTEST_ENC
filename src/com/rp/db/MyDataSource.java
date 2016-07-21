package com.rp.db;
import java.beans.PropertyVetoException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp.BasicDataSource;

public class MyDataSource {
	private static MyDataSource myDataSource;
    private static BasicDataSource ds;
    private MyDataSource() throws IOException, SQLException, PropertyVetoException {
         
        ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUsername("wsjeong");
        ds.setPassword("rplinux");
        ds.setUrl("jdbc:mysql://192.168.56.15:3306/wsjeongdb");
         
     // the settings below are optional -- dbcp can work with defaults
        ds.setMinIdle(5);
        ds.setMaxIdle(20);
        ds.setMaxOpenPreparedStatements(180);
     
     }
    public static BasicDataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (myDataSource == null) {
        	myDataSource = new MyDataSource();
            return myDataSource.ds;
        } else {
            return myDataSource.ds;
        }
    }
    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
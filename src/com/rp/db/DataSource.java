package com.rp.db;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp.BasicDataSource;
public class DataSource {
    private static DataSource     datasource;
    private BasicDataSource ds;
    private DataSource() throws IOException, SQLException, PropertyVetoException {
        ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUsername("wsjeong");
        ds.setPassword("rplinux");
        //ds.setUrl("jdbc:mysql://172.16.168.2:3306/wsjeongdb");
        ds.setUrl("jdbc:mysql://192.168.57.102:3306/wsjeongdb");
        
     // the settings below are optional -- dbcp can work with defaults
        ds.setMinIdle(5);
        ds.setMaxIdle(20);
        ds.setMaxOpenPreparedStatements(180);
     }
    public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (datasource == null) {
            datasource = new DataSource();
            return datasource;
        } else {
            return datasource;
        }
    }
    public Connection getConnection() throws SQLException {
        return this.ds.getConnection();
    }
}
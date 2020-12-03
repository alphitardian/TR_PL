/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Ardian
 */
public class DBConfig {
    private String dbDriver = "com.mysql.jdbc.Driver";
    private String dbUsername = "root";
    private String dbPassword = "";
    private String dbURL = "jdbc:mysql://localhost:3306/db_library?zeroDateTimeBehavior=convertToNull";
    
    private Connection connection; 
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public DBConfig() {
        try {
            Class.forName(dbDriver);
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        } catch (Exception e) {
            connection = null;
            System.out.println("Exception : " + e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public ResultSet connectDBResultSet(String query) throws SQLException {
        resultSet = null;

        try {
            getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            System.out.println("Connect DB ResultSet Success");
        } catch (Exception e) {
            System.out.println("Connect DB ResultSet : " + e);
        }

        return resultSet;
    }
}

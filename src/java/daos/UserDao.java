/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.User;
import utils.DBConfig;

/**
 *
 * @author Ardian
 */
public class UserDao {
    
    private ResultSet resultSet;
    private Statement statement;
    private PreparedStatement preparedStatement;
    
    public List<User> getAll() {
        DBConfig connection = new DBConfig();
        
        if (connection.getConnection() == null) {
            return null;
        } else {
            List<User> result = null;
            
            try {
                resultSet = connection.connectDBResultSet(Query.QUERY_GET_ALL_USER.getDisplayName());
                
                result = new ArrayList<>();
                
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setPassword(resultSet.getString("username"));
                    result.add(user);
                }
                
                statement.close();
                resultSet.close();
                connection.getConnection().close();
                
                System.out.println("Get All Data Success");
                
            } catch (Exception e) {
                System.out.println("Exception getAll() User : " + e);
            }
            
            return result;
            
        }
    }
    
    public User getUserById(int id) {
        DBConfig connection = new DBConfig();
        
        if (connection.getConnection() == null) {
            return null;
        } else {
            User user = new User();
            
            try {
                resultSet = connection.connectDBPreparedStatementSingleValue(Query.QUERY_GET_USER_BY_ID.getDisplayName(), id + "");
                
                while (resultSet.next()) {
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                }
                
                statement.close();
                resultSet.close();
                connection.getConnection().close();
                
                System.out.println("Get User Data By Id Success");
                
            } catch (Exception e) {
                System.out.println("Exception getById User : " + e);
            }
            
            return user;
            
        }
    }
    
}

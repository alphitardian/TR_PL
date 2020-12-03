/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

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
                System.out.println("Exception : " + e);
            }
            
            return result;
            
        }
    }
    
}

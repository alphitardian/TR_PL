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
import models.Book;
import models.Publisher;
import utils.DBConfig;

/**
 *
 * @author Ardian
 */
public class PublisherDao {
    
    private ResultSet resultSet;
    private Statement statement;
    private PreparedStatement preparedStatement;
    
    DBConfig connection = new DBConfig();
    
    public List<Publisher> getAll() {
        
        if (connection.getConnection() == null) {
            return null;
        } else {
            List<Publisher> result = null;
            
            try {
                resultSet = connection.connectDBResultSet(Query.QUERY_GET_ALL_PUBLISHER.getDisplayName());
                
                result = new ArrayList<>();
                
                while (resultSet.next()) {
                    Publisher publisher = new Publisher();
                    publisher.setId(resultSet.getInt("id"));
                    publisher.setName(resultSet.getString("name"));
                    publisher.setAddress(resultSet.getString("address"));
                    publisher.setTelephone(resultSet.getString("telephone"));
                    result.add(publisher);
                }
                
                statement.close();
                resultSet.close();
                connection.getConnection().close();
                
                System.out.println("Get All Data Success");
                
            } catch (Exception e) {
                System.out.println("Exception PublisherDao : " + e);
            }
            
            return result;
            
        }
    }
    
    public Publisher getBookById(int id) {
        
        if (connection.getConnection() == null) {
            return null;
        } else {
            Publisher publisher = new Publisher();
            
            try {
                resultSet = connection.connectDBPreparedStatementSingleValue(Query.QUERY_GET_PUBLISHER_BY_ID.getDisplayName(), id + "");
                
                while (resultSet.next()) {
                    publisher.setId(resultSet.getInt("id"));
                    publisher.setName(resultSet.getString("name"));
                    publisher.setAddress(resultSet.getString("address"));
                    publisher.setTelephone(resultSet.getString("telephone"));
                }
                
                preparedStatement.close();
                resultSet.close();
                connection.getConnection().close();
                
                System.out.println("Get Publisher Data By Id Success");
                
            } catch (Exception e) {
                System.out.println("Exception getById Publisher : " + e);
            }
            
            return publisher;
            
        }
    }
    
}

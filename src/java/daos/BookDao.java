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
import models.Book;
import utils.DBConfig;

/**
 *
 * @author Ardian
 */
public class BookDao {
    
    private ResultSet resultSet;
    private Statement statement;
    
    public List<Book> getAll() {
        DBConfig connection = new DBConfig();
        
        if (connection.getConnection() == null) {
            return null;
        } else {
            List<Book> result = null;
            
            try {
                resultSet = connection.connectDBResultSet(Query.QUERY_GET_ALL_BOOK.getDisplayName());
                
                result = new ArrayList<>();
                
                while (resultSet.next()) {
                    Book book = new Book();
                    book.setId(resultSet.getInt("id"));
                    book.setTitle(resultSet.getString("title"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setAvailability(resultSet.getString("availability"));
                    result.add(book);
                }
                
                statement.close();
                resultSet.close();
                connection.getConnection().close();
                
                System.out.println("Get All Data Success");
                
            } catch (Exception e) {
                System.out.println("Exception BookDao : " + e);
            }
            
            return result;
            
        }
    }
}

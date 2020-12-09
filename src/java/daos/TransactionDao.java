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
import models.Borrowed;
import models.User;
import utils.DBConfig;

/**
 *
 * @author Ardian
 */
public class TransactionDao {
    
    private ResultSet resultSet;
    private Statement statement;
    private PreparedStatement preparedStatement;
    
    DBConfig connection = new DBConfig();
    
    public List<Borrowed> getAll() {
        
        BookDao bookDao = new BookDao();
        UserDao userDao = new UserDao();
        
        if (connection.getConnection() == null) {
            return null;
        } else {
            List<Borrowed> result = null;
            
            try {
                resultSet = connection.connectDBResultSet(Query.QUERY_GET_ALL_BORROWED.getDisplayName());
                
                result = new ArrayList<>();
                
                while (resultSet.next()) {
                    Borrowed borrowed = new Borrowed();
                    borrowed.setId(resultSet.getInt("id"));
                    borrowed.setBook(bookDao.getBookById(resultSet.getInt("book")));
                    borrowed.setUser(userDao.getUserById(resultSet.getInt("user")));
                    borrowed.setDueDate(resultSet.getString("due_date"));
                    result.add(borrowed);
                }
                
                statement.close();
                resultSet.close();
                connection.getConnection().close();
                
                System.out.println("Get All Data Success");
                
            } catch (Exception e) {
                System.out.println("Exception TransactionDao : " + e);
            }
            
            return result;
            
        }
    }
    
    public List<Borrowed> getAllByUser(int id) {
        
        if (connection.getConnection() == null) {
            return null;
        } else {
            List<Borrowed> result = null;
            
            try {
                resultSet = connection.connectDBPreparedStatementSingleValue(Query.QUERY_GET_BORROWED_BY_USER_ID.getDisplayName(), id + "");
                
                result = new ArrayList<>();
                
                while (resultSet.next()) {
                    Borrowed borrowed = new Borrowed();
                    borrowed.setId(resultSet.getInt("id"));
                    borrowed.setBook(resultSet.getObject("book", new Book().getClass()));
                    borrowed.setUser(resultSet.getObject("user", new User().getClass()));
                    borrowed.setDueDate(resultSet.getString("due_date"));
                    result.add(borrowed);
                }
                
                preparedStatement.close();
                resultSet.close();
                connection.getConnection().close();
                
                System.out.println("Get Data By User Success");
                
            } catch (Exception e) {
                System.out.println("Exception TransactionDao : " + e);
            }
            
            return result;
            
        }
    }
    
    public boolean insertBorrowed(int book, int user, String dueDate) {
        
        if (connection.getConnection() == null) {
            return false;
        } else {
            try {
                connection.queryInsertUser(Query.QUERY_INSERT_BORROWED.getDisplayName(), book + "", user + "", dueDate);

                preparedStatement.close();
                connection.getConnection().close();
                
                System.out.println("Insert Borrowed Data Success");
                
            } catch (Exception e) {
                System.out.println("Exception Insert Borrowed : " + e);
            }
            
            return true;
            
        }
    }
    
    public boolean update(String id, String book, String user, String dueDate) {
        if (connection.getConnection() == null) {
            return false;
        } else {
            try {
                String[] data = {book, user, dueDate, id};
                return connection.connectDBPreparedStatementDoQuery(Query.QUERY_UPDATE_BORROWED.getDisplayName(), data);
            } catch (Exception e) {
                System.out.println("Error : " + e);
            }
        }
        return false;
    }
    
    public boolean delete(String id) {

        if (connection.getConnection() == null) {
            return false;
        } else {
            try {
                connection.deleteDataQuery(Query.QUERY_DELETE_BORROWED.getDisplayName(), id);

                preparedStatement.close();
                connection.getConnection().close();

                System.out.println("Delete Book Data By Id Success");

            } catch (Exception e) {
                System.out.println("Exception delete Book : " + e);
            }

            return true;

        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Borrowed;
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

    DBConfig connection = new DBConfig();

    public List<User> getAll() {

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

    public User getUserById(String id) {

        BookDao bookDao = new BookDao();
        List<Borrowed> borroweds = new ArrayList<>();
        String data[] = {id};

        try {
            resultSet = connection.connectDBPreparedStatement(Query.QUERY_GET_BORROWED_BY_USER_ID.getDisplayName(), data);

            while (resultSet.next()) {
                Borrowed b = new Borrowed();
                b.setBook(bookDao.getBookById(resultSet.getInt("book")));
                b.setDueDate(resultSet.getString("due_date"));

                borroweds.add(b);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

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
                    user.setBorrowedList(borroweds);
                }

                preparedStatement.close();
                resultSet.close();
                connection.getConnection().close();

                System.out.println("Get User Data By Id Success");

            } catch (Exception e) {
                System.out.println("Exception getById User : " + e);
            }

            return user;

        }
    }

    public boolean deleteUser(int id) {

        if (connection.getConnection() == null) {
            return false;
        } else {
            try {
                connection.deleteDataQuery(Query.QUERY_DELETE_USER.getDisplayName(), id + "");

                preparedStatement.close();
                connection.getConnection().close();

                System.out.println("Delete User Data By Id Success");

            } catch (Exception e) {
                System.out.println("Exception delete User : " + e);
            }

            return true;

        }
    }

    public boolean insertUser(String name, String username, String password) {

        if (connection.getConnection() == null) {
            return false;
        } else {
            try {
                connection.queryInsertUser(Query.QUERY_INSERT_USER.getDisplayName(), name, username, password);

                preparedStatement.close();
                connection.getConnection().close();

                System.out.println("Insert User Data Success");

            } catch (Exception e) {
                System.out.println("Exception Insert User : " + e);
            }

            return true;

        }
    }

    public User getByUsernameAndPassword(String username, String password) {
        if (connection.getConnection() == null) {
            return null;
        } else {
            User user = new User();

            try {

                String[] data = {username, password};

                resultSet = connection.connectDBPreparedStatement(Query.QUERY_GET_USER_BY_USERPASS.getDisplayName(), data);

                while (resultSet.next()) {
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                }

                System.out.println("User Found!!");

                //preparedStatement.close();
                resultSet.close();
                connection.getConnection().close();

            } catch (SQLException e) {
                System.out.println("Couldn't get users : " + e);
            }

            return user;

        }
    }

}

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
import models.Borrowed;
import utils.DBConfig;

/**
 *
 * @author kelvi
 */
public class BorrowDao {

    private ResultSet resultSet;
    private Statement statement;
    private PreparedStatement preparedStatement;

    DBConfig connection = new DBConfig();

    public List<Borrowed> getBorrowedByBook(String bookId) {

        BookDao bookDao = new BookDao();
        UserDao userDao = new UserDao();

        if (connection.getConnection() == null) {
            return null;
        } else {
            List<Borrowed> result = null;

            try {
                String data[] = {bookId};
                resultSet = connection.connectDBPreparedStatement(Query.QUERY_GET_BORROWED_BY_BOOK_ID.getDisplayName(), data);

                result = new ArrayList<>();

                while (resultSet.next()) {
                    Borrowed borrowed = new Borrowed();
                    borrowed.setBook(bookDao.getBookById(resultSet.getInt("id")));
                    borrowed.setDueDate(resultSet.getString("due_date"));
                    borrowed.setUser(userDao.getUserById(resultSet.getString("user")));
                    result.add(borrowed);
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
}

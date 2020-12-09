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
import utils.DBConfig;

/**
 *
 * @author Ardian
 */
public class BookDao {

    private ResultSet resultSet;
    private Statement statement;
    private PreparedStatement preparedStatement;

    DBConfig connection = new DBConfig();

    public List<Book> getAll() {

        PublisherDao publisherDao = new PublisherDao();

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
                    book.setIsbn(resultSet.getString("isbn"));
                    book.setTitle(resultSet.getString("title"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setPublisher(publisherDao.getPublisherById(resultSet.getString("publisher")));
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

    public Book getBookById(int id) {

        PublisherDao publisherDao = new PublisherDao();

        if (connection.getConnection() == null) {
            return null;
        } else {
            Book book = new Book();

            try {
                resultSet = connection.connectDBPreparedStatementSingleValue(Query.QUERY_GET_BOOK_BY_ID.getDisplayName(), id + "");

                while (resultSet.next()) {
                    book.setId(resultSet.getInt("id"));
                    book.setIsbn(resultSet.getString("isbn"));
                    book.setTitle(resultSet.getString("title"));
                    book.setAuthor(resultSet.getString("author"));
                    book.setPublisher(publisherDao.getPublisherById(resultSet.getString("publisher")));
                    book.setAvailability(resultSet.getString("availability"));
                }

                preparedStatement.close();
                resultSet.close();
                connection.getConnection().close();

                System.out.println("Get Book Data By Id Success");

            } catch (Exception e) {
                System.out.println("Exception getById Book : " + e);
            }

            return book;

        }
    }

    public boolean delete(String id) {

        if (connection.getConnection() == null) {
            return false;
        } else {
            try {
                connection.deleteDataQuery(Query.QUERY_DELETE_BOOK.getDisplayName(), id + "");

                preparedStatement.close();
                connection.getConnection().close();

                System.out.println("Delete Book Data By Id Success");

            } catch (Exception e) {
                System.out.println("Exception delete Book : " + e);
            }

            return true;

        }
    }

    public boolean insert(String isbn, String title, String author, String publisher, String availability) {

        if (connection.getConnection() == null) {
            return false;
        } else {
            try {
                String data[] = {isbn, title, author, publisher, availability};
                connection.connectDBPreparedStatementDoQuery(Query.QUERY_INSERT_BOOK.getDisplayName(), data);

                preparedStatement.close();
                connection.getConnection().close();

                System.out.println("Insert Book Data Success");

            } catch (Exception e) {
                System.out.println("Exception Insert Book : " + e);
            }

            return true;

        }
    }

    public boolean update(String id, String isbn, String title, String author, String publisher, String availability) {
        if (connection.getConnection() == null) {
            return false;
        } else {
            try {
                String[] data = {isbn, title, author, publisher, availability, id};
                return connection.connectDBPreparedStatementDoQuery(Query.QUERY_UPDATE_BOOK.getDisplayName(), data);
            } catch (Exception e) {
                System.out.println("Error : " + e);
            }
        }
        return false;
    }

    public boolean updateAvailability(String availability) {

        if (connection.getConnection() == null) {
            return false;
        } else {
            try {
                connection.connectDBPreparedStatementSingleValue(Query.QUERY_UPDATE_BOOK_AVAILABILITY.getDisplayName(), availability);

                preparedStatement.close();
                connection.getConnection().close();

                System.out.println("Update Book Availability Success");

            } catch (Exception e) {
                System.out.println("Exception Update Availability Book : " + e);
            }

            return true;

        }
    }
}

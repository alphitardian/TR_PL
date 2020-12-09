/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

/**
 *
 * @author Ardian
 */
public enum Query {
    QUERY_GET_ALL_USER("select * from user"),
    QUERY_GET_ALL_BOOK("select * from book"),
    QUERY_GET_ALL_BORROWED("select * from borrowed"),
    QUERY_GET_ALL_PUBLISHER("select * from publisher"),
    QUERY_GET_USER_BY_ID("select * from user where user.id = ?"),
    QUERY_GET_USER_BY_USERPASS("select * from user where user.username = ? and user.password = ?"),
    QUERY_GET_BOOK_BY_ID("select * from book where book.id = ?"),
    QUERY_GET_PUBLISHER_BY_ID("select * from publisher where publisher.id = ?"),
    QUERY_GET_BORROWED_BY_USER_ID("select * from borrowed where borrowed.user = ? and status = 0"),
    QUERY_GET_BORROWED_BY_BOOK_ID("select * from borrowed where borrowed.book = ? and status = 0"),
    QUERY_DELETE_USER("delete from user where user.id = ?"),
    QUERY_DELETE_BOOK("delete from book where book.id = ?"),
    QUERY_INSERT_USER("insert into user (name, username, password) values (?,?,?)"),
    QUERY_INSERT_BOOK("insert into book (isbn, title, author, publisher, availability) values (?, ?, ?, ?, ?)"),
    QUERY_INSERT_BORROWED("insert into borrowed (book, user, due_date, status) values (?,?,?, 0)"),
    QUERY_UPDATE_BORROWED("update borrowed set book = ?, user = ?, due_date = ? where id = ?"),
    QUERY_DELETE_BORROWED("delete from borrowed where borrowed.id = ?"),
    QUERY_UPDATE_BOOK_AVAILABILITY("update book set availability = ?"),
    QUERY_INSERT_PUBLISHER("insert into publisher (name, address, telephone) values (?, ?, ?)"),
    QUERY_DELETE_PUBLISHER("delete from publisher where id = ?"),
    QUERY_UPDATE_PUBLISHER("update publisher set name = ?, address = ?, telephone = ? where id = ?"),
    QUERY_UPDATE_BOOK("update book set isbn = ?, title = ?, author = ?, publisher = ?, availability = ? where id = ?"),
    QUERY_UPDATE_USER("update user set name = ?, username = ?, password = ? where id = ?");

    private final String displayName;

    Query(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

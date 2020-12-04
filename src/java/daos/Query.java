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
    QUERY_GET_USER_BY_ID("select * from user where user.id = ?"),
    QUERY_GET_BOOK_BY_ID("select * from book where book.id = ?");
    
    private final String displayName;

    Query(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Book;
import models.User;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-09T14:47:08")
@StaticMetamodel(Borrowed.class)
public class Borrowed_ { 

    public static volatile SingularAttribute<Borrowed, String> dueDate;
    public static volatile SingularAttribute<Borrowed, Book> book;
    public static volatile SingularAttribute<Borrowed, Integer> id;
    public static volatile SingularAttribute<Borrowed, User> user;
    public static volatile SingularAttribute<Borrowed, Integer> status;

}
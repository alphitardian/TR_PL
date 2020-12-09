package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Borrowed;
import models.Publisher;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-09T14:47:08")
@StaticMetamodel(Book.class)
public class Book_ { 

    public static volatile SingularAttribute<Book, String> author;
    public static volatile SingularAttribute<Book, String> isbn;
    public static volatile SingularAttribute<Book, Publisher> publisher;
    public static volatile SingularAttribute<Book, Integer> id;
    public static volatile SingularAttribute<Book, String> availability;
    public static volatile SingularAttribute<Book, String> title;
    public static volatile SingularAttribute<Book, Integer> initialStock;
    public static volatile ListAttribute<Book, Borrowed> borrowedList;

}
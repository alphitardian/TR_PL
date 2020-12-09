package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Book;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-09T15:11:04")
@StaticMetamodel(Publisher.class)
public class Publisher_ { 

    public static volatile SingularAttribute<Publisher, String> address;
    public static volatile SingularAttribute<Publisher, String> name;
    public static volatile SingularAttribute<Publisher, String> telephone;
    public static volatile SingularAttribute<Publisher, Integer> id;
    public static volatile ListAttribute<Publisher, Book> bookList;

}
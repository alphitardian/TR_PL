package models;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import models.Borrowed;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-12-09T14:47:08")
@StaticMetamodel(User.class)
public class User_ { 

    public static volatile SingularAttribute<User, String> password;
    public static volatile SingularAttribute<User, Integer> role;
    public static volatile SingularAttribute<User, String> name;
    public static volatile SingularAttribute<User, Integer> id;
    public static volatile ListAttribute<User, Borrowed> borrowedList;
    public static volatile SingularAttribute<User, String> username;

}
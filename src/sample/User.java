package sample;

import java.io.File;

/**
 * Created by Allen on 9/27/2016.
 */
public class User {
    private String userName;
    private String password;
    private String type;
    private String email;
    private String address;
    private String title;

    public User(String userName, String password , String type){
       this(userName,password,type,null,null,null);
    }
    public User(String userName, String password , String type,String email,String address,String title){
        this.userName = userName;
        this.password =password;
        this.type = type;
        this.email = email;
        this.address = address;
        this.title = title;
    }

}

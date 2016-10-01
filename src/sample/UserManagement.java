package sample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import com.firebase.client.*;
import java.util.Map;
import java.util.HashMap;


/**
 * Created by Allen on 9/27/2016.
 */
public class UserManagement {
    public static File userInfor = new File( "userInfor.txt");
    public static User currentUser;
    public static Firebase ref = new Firebase("https://clean-water-crowdsourcing.firebaseio.com/");
    public static Firebase userRef = myFirebaseRef.child("User");
    public static void register(String userName, String password , String type) {
        Map<String, String> newUser = new HashMap<String, String>();
        newUser.put("userName",userName);
        newUser.put("passWord",password);
        newUser.put("type",type);
        newUser.put("email","empty");
        newUser.put("address","empty");
        newUser.put("title","empty");
        //User tem = new User(userName,password,type);

        userRef.push().setValue(newUser);
        System.out.print( userRef.getValue());
    }
    public static boolean verify(String userName, String password ){

        return false;
    }
}

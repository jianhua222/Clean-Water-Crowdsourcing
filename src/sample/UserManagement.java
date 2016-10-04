package sample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Created by Allen on 9/27/2016.
 */
public class UserManagement {

    public static User currentUser;
    //public static Firebase ref = new Firebase("https://clean-water-crowdsourcing.firebaseio.com");
    //public static Firebase userRef = ref.child("User");
    public static ArrayList<User> users = new ArrayList<>();

    public static void register(String userName, String password , String type) {
        User tem = new User(userName,password,type);
        users.add(tem);
    }
    public static boolean verify(String userName, String password ){
        for (User x : users){
            if(x.getUserName().equals(userName)&&x.getPassword().equals(password)){
                currentUser = x;
                return true;
            }
        }
        return false;
    }

    public static User getUser() {
        return currentUser;
    }

}

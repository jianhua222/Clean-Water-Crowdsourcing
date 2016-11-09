package sample.model;

import sample.model.User;

import java.io.*;
import java.util.ArrayList;

/**
 * user management class
 * Created by Allen on 9/27/2016.
 * Service Provider
 */
public class UserManagement {

    private static User currentUser;
    //private static ArrayList<User> users = new ArrayList<>();

    /**
     * register a new user
     *
     * @param userName input user name
     * @param password input password
     * @param type     input type
     */
    public static void register(String userName, String password, String type) {
        User newuser = new User();
        if (type.equals("User")) {
            newuser = new User(userName, password, type);
        } else if (type.equals("Worker")) {
            newuser = new Worker(userName, password, type);
        } else if (type.equals("Manager")) {
            newuser = new Manager(userName, password, type);
        } else if (type.equals("Admin")) {
            //Change to ADMIN if needed
            newuser = new User(userName, password, type);
        } else {
            throw new IllegalArgumentException("The given user type was not correct");
        }
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(newuser.getUserName()+".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(newuser);
            //out.writeObject(b);
            out.close();
            fileOut.close();
            //System.out.printf("Serialized data is saved in /tmp/employee.ser");
        }catch(IOException i) {
            i.printStackTrace();
        }
        //users.add(newuser);
    }

    /**
     * verify a user information
     *
     * @param userName input user name
     * @param password input password
     * @return a boolean that checks the given username and password
     */
    public static boolean verify(String userName, String password) {
        User tem = null;
        try {
            FileInputStream fileIn = new FileInputStream(userName+".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            tem = ( User) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i) {
            i.printStackTrace();
            //return;
        }catch(ClassNotFoundException c) {
            System.out.println();
            c.printStackTrace();
            //return;
        }

            if (tem.getUserName().equals(userName) && tem.getPassword().equals(password)) {
                currentUser = tem;
                return true;
            }

        return false;
    }

    /**
     * return the current user
     *
     * @return the current user
     */
    public static User getUser() {
        return currentUser;
    }

    /**
     * search for a user
     *
     * @return the user
     */
   /* public static User getUser(String userName) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
    }*/
}

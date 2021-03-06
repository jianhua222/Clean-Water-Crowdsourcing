package sample.model;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

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
        User newUser;
        if (type.equals("User")) {
            newUser = new User(userName, password, type);
        } else if (type.equals("Worker")) {
            newUser = new Worker(userName, password, type);
        } else if (type.equals("Manager")) {
            newUser = new Manager(userName, password, type);
        } else if (type.equals("Admin")) {
            //Change to ADMIN if needed
            newUser = new User(userName, password, type);
        } else {
            throw new IllegalArgumentException(
                    "The given user type was not correct");
        }
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(newUser.getUserName() + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(newUser);
            //out.writeObject(b);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
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
            FileInputStream fileIn = new FileInputStream(userName + ".ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            tem = (User) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            //return;
        } catch (ClassNotFoundException c) {
            System.out.println();
            c.printStackTrace();
            //return;
        }
        if (tem != null) {
            if (tem.getUserName().equals(userName)
                    && tem.getPassword().equals(password)) {
                currentUser = tem;
                return true;
            }
        }

        return false;
    }

    /**
     * return the user that was logged out
     *
     * @return the user that was logged out
     */
    public static User logoutUser() {
        User temp = currentUser;
        currentUser = null;
        return temp;
    }

    /**
     * return the current user
     *
     * @return the current user
     */
    public static User getUser() {
        return currentUser;
    }
}

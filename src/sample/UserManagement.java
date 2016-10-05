package sample;

import java.util.ArrayList;

/**
 * user management class
 * Created by Allen on 9/27/2016.
 */
public class UserManagement {

    public static User currentUser;
    public static ArrayList<User> users = new ArrayList<>();

    /**
     * register a new user
     *
     * @param userName input user name
     * @param password input password
     * @param type     input type
     */
    public static void register(String userName, String password, String type) {
        User tem = new User(userName, password, type);
        users.add(tem);
    }

    /**
     * verify a user information
     *
     * @param userName input user name
     * @param password input password
     */
    public static boolean verify(String userName, String password) {
        for (User x : users) {
            if (x.getUserName().equals(userName) && x.getPassword().equals(password)) {
                currentUser = x;
                return true;
            }
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

}

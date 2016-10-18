package sample;

import java.util.ArrayList;

/**
 * user management class
 * Created by Allen on 9/27/2016.
 * Service Provider
 */
public class UserManagement {

    private static User currentUser;
    private static ArrayList<User> users = new ArrayList<>();

    /**
     * register a new user
     *
     * @param userName input user name
     * @param password input password
     * @param type     input type
     */
    public static void register(String userName, String password, String type) {
        for(User x : users){
            if(x.getUserName().equals(userName)){
                System.out.println("user name already exists. ");
                    return;
            }
        }
        User tem = new User(userName, password, type);
        users.add(tem);
    }

    /**
     * verify a user information
     *
     * @param userName input user name
     * @param password input password
     * @return a boolean that checks the given username and password
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

    /**
     * search for a user
     *
     * @return the user
     */
    public static User getUser(String userName) {
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
    }

}

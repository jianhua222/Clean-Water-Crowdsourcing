package sample.model;

import java.io.Serializable;

/**
 * Created by Jai Chauhan on 11/4/2016.
 */
public class Manager extends Worker implements Serializable {

    /**
     * Constructor for manager class
     * @param username username of the manager
     * @param password the password
     * @param type the type
     */
    public Manager(String username, String password, String type) {
        super(username, password, type);
    }
}

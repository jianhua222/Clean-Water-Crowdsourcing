package sample.model;

/*
 * Created by Jai Chauhan on 10/31/2016.
 */

import java.io.Serializable;

/*
 * Constructor for worker
 */
public class Worker extends User implements Serializable {

    /**
     * Constructor for worker class
     * @param username username of the worker
     * @param password the password
     * @param type the type
     */
    public Worker(String username, String password, String type) {
        super(username, password, type);
    }

}

package sample.model;

/**
 * Created by Jai Chauhan on 10/31/2016.
 */

import java.io.Serializable;

/**
 * Constructor for woker
 */
public class Worker extends User implements Serializable {
    public Worker(String username, String password, String type) {
        super(username, password, type);
    }

}

package sample.model;

import java.io.Serializable;

/**
 * Created by Jai Chauhan on 11/4/2016.
 */
public class Manager extends Worker implements Serializable {
    public Manager(String username, String password, String type) {
        super(username, password, type);
    }
}

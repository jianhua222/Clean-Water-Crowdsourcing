package sample.model;

import java.io.Serializable;

/**
 * Created by Allen on 9/27/2016.
 * User information holder
 */
public class User implements Serializable {
    private String userName;
    private String password;
    private String type;
    private String email;
    private String address;
    private String title;

    /**
     * Default Constructor - make compiler happy
     */
    public User() {

    }

    /**
     * Constructor
     *
     * @param userName input user name
     * @param password input password
     * @param type     input type
     */
    public User(String userName, String password, String type) {
        this.userName = userName;
        this.password = password;
        this.type = type;
    }

    /*
     * Constructor
     *
     * @param userName input user name
     * @param password input password
     * @param type     input type
     * @param email    input email
     * @param address  inupt address
     * @param title    input title
     */
    /*public User(String userName, String password, String type,
    String email, String address, String title) {
        this.userName = userName;
        this.password = password;
        this.type = type;
        this.email = email;
        this.address = address;
        this.title = title;
    }*/

    /**
     * userName getter
     *
     * @return userName value
     */
    public String getUserName() {
        return userName;
    }

    /**
     * password getter
     *
     * @return password value
     */
    public String getPassword() {
        return password;
    }

    /**
     * type getter
     *
     * @return type value
     */
    public String getType() {
        return type;
    }

    /**
     * email getert
     *
     * @return email value
     */
    public String getEmail() {
        return email;
    }

    /**
     * address getter
     *
     * @return address value
     */
    public String getAddress() {
        return address;
    }

    /**
     * title getter
     *
     * @return title value
     */
    public String getTitle() {
        return title;
    }

    /**
     * email setter
     *
     * @param email value
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * address setter
     *
     * @param address value
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * title setter
     *
     * @param title value
     */
    public void setTitle(String title) {
        this.title = title;
    }



    /**
     * userName  setter
     *
     * @param userName value
     */
    public void setUsername(String userName) {
        this.userName = userName;
    }
}

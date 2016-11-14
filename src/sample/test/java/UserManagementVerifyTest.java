package sample.test.java;


import org.junit.Test;
import sample.model.User;
import sample.model.UserManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by David on 11/13/16.
 */
public class UserManagementVerifyTest {

    private User temp1 = new User();
    private User temp2 = new User();

    /**
     * Doest the setUp for every test
     */
    private void setUp() {
        UserManagement.register("UN1", "P1", "User");
        UserManagement.register("UN2", "P2", "Admin");
        try {
            FileInputStream fileIn = new FileInputStream("UN1.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            temp1 = (User) in.readObject();
            in.close();
            fileIn.close();
            FileInputStream fileIn2 = new FileInputStream("UN2.ser");
            ObjectInputStream in2 = new ObjectInputStream(fileIn2);
            temp2 = (User) in2.readObject();
            in2.close();
            fileIn2.close();
        } catch (IOException | ClassNotFoundException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testCorrectCredentials() {
        setUp();

        assertEquals(true, UserManagement.verify(temp1.getUserName(),
                temp1.getPassword()));
        assertEquals(true, UserManagement.verify(temp2.getUserName(),
                temp2.getPassword()));
        cleanUp();
    }

    @Test
    public void testWrongPassword() {
        setUp();

        assertEquals(false, UserManagement.verify(temp1.getUserName(),
                temp2.getPassword()));
        assertEquals(null, UserManagement.getUser());

        assertEquals(false, UserManagement.verify(temp2.getUserName(),
                temp1.getPassword()));
        assertEquals(null, UserManagement.getUser());

        cleanUp();
    }

    @Test
    public void testCurrentUserCorrectCredentials() {
        setUp();

        assertEquals(true, UserManagement.verify(temp1.getUserName(),
                temp1.getPassword()));
        assertEquals(temp1.getUserName(),
                UserManagement.getUser().getUserName());
        assertEquals(temp1.getPassword(),
                UserManagement.getUser().getPassword());
        assertEquals(temp1.getType(),
                UserManagement.getUser().getType());

        assertEquals(true, UserManagement.verify(temp2.getUserName(),
                temp2.getPassword()));
        assertEquals(temp2.getUserName(),
                UserManagement.getUser().getUserName());
        assertEquals(temp2.getPassword(),
                UserManagement.getUser().getPassword());
        assertEquals(temp2.getType(),
                UserManagement.getUser().getType());
        cleanUp();
    }
    /**
     * Doest the cleanUp for every test
     */
    private void cleanUp() {

        try {
            File f = new File("UN1.ser");
            File f2 = new File("UN2.ser");
            if (!(f.delete() && f2.delete())) {
                throw new IOException("Couldn't delete file.");
            }
        } catch (IOException e) {
            fail(e.getMessage());
        }
        UserManagement.logoutUser();
    }
}

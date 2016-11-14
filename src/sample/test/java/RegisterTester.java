package sample.test.java;

import org.junit.Before;
import org.junit.Test;
import sample.model.Manager;
import sample.model.User;
import sample.model.UserManagement;
import sample.model.Worker;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Ashwin on 11/8/16.
 */
public class RegisterTester {

    private User tem = new User();
    private User tep = new User();
    private User tes = new User();
    private User ter = new User();

    @Before
    public void setUp() {
        UserManagement.register("UN1", "P1", "User");
        UserManagement.register("UN2", "P2", "Admin");
        UserManagement.register("UN3", "P3", "Worker");
        UserManagement.register("UN4", "P4", "Manager");
        try {
            FileInputStream fileIn = new FileInputStream("UN1.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            tem = (User) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.out.println(i.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage() + "1");
        }
        try {
            FileInputStream fileIn = new FileInputStream("UN2.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            tep = (User) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.out.println(i.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage() + "2");
        }
        try {
            FileInputStream fileIn = new FileInputStream("UN3.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            tes = (User) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.out.println(i.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage() + "3");
        }
        try {
            FileInputStream fileIn = new FileInputStream("UN4.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            ter = (User) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.out.println(i.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage() + "4");
        }
    }

    @Test
    public void testFilesCreated() {
        File file1 = new File("UN1.ser");
        assertTrue(file1.exists());
        File file2 = new File("UN2.ser");
        assertTrue(file2.exists());
        assertTrue(new File("UN3.ser").exists());
        assertTrue(new File("UN4.ser").exists());
    }

    @Test
    public void testUserNames() {
        assertEquals("The user names do not match", tem.getUserName(), "UN1");
        assertEquals("The user names do not match", tep.getUserName(), "UN2");
        assertEquals("The user names do not match", tes.getUserName(), "UN3");
        assertEquals("The user names do not match", ter.getUserName(), "UN4");
    }

    @Test
    public void testTypes() {
        assertTrue(tes instanceof Worker);
        assertTrue(ter instanceof Manager);
        assertEquals("The types do not match", tem.getType(), "User");
        assertEquals("The types do not match", tep.getType(), "Admin");
        assertEquals("The types do not match", tes.getType(), "Worker");
        assertEquals("The types do not match", ter.getType(), "Manager");
    }

    @Test
    public void testPasswords() {
        assertEquals("The passwords do not match", tem.getPassword(), "P1");
        assertEquals("The passwords do not match", tep.getPassword(), "P2");
        assertEquals("The passwords do not match", tes.getPassword(), "P3");
        assertEquals("The passwords do not match", ter.getPassword(), "P4");

    }

    @Test
    public void testWrongUserTypeGiven() {
        boolean thrown = false;
        try {
            UserManagement.register("UN3", "P3", "Something");
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
}

package sample.test;

import org.junit.Before;
import org.junit.Test;
import sample.model.UserManagement;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Ashwin on 11/8/16.
 */
public class RegisterTester {

    @Before
    public void setUp() {
        UserManagement.clear();
        UserManagement.register("UN1", "P1", "User");
        UserManagement.register("UN2", "P2", "Admin");
    }

    @Test
    public void testRegisterUserNames() {
        assertEquals(UserManagement.users.get(0).toString(), "UN1");
        assertEquals(UserManagement.users.get(1).toString(), "UN2");
    }

    @Test
    public void testRegisterTypes() {
        assertEquals(UserManagement.users.get(0).getType(), "User");
        assertEquals(UserManagement.users.get(1).getType(), "Admin");
    }

    @Test
    public void testPasswords() {
        assertEquals(UserManagement.users.get(0).getPassword(), "P1");
        assertEquals(UserManagement.users.get(1).getPassword(), "P2");
    }

    @Test
    public void testAddDuplicateUserNames() {
        UserManagement.register("UN1", "P1", "User");
        UserManagement.register("UN2", "P2", "Admin");
        assertEquals(UserManagement.users.size(), 2);
    }

    @Test
    public void testWrongUserTypeGiven() {
        boolean thrown = false;
        try {
            UserManagement.register("UN3", "P3", "Something");
        } catch (IllegalArgumentException e){
            thrown = true;
        }
        assertTrue(thrown);
    }
}

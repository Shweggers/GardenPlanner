package com.gardenplanner.gardenplanner;

import com.gardenplanner.gardenplanner.model.DAO.MockUserDAO;
import com.gardenplanner.gardenplanner.model.User;
import com.gardenplanner.gardenplanner.model.UserManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserManagerTest {
    private UserManager userManager;

    private final User[] users = {
            new User("JohnS", "email1", "password1"),
            new User("John824", "email2", "password1"),
            new User("Jane", "email3", "password1"),
            new User("Shane", "email32", "password1"),
            new User("Alex", "email4", "password1"),
            new User("Alexander", "email1email1", "password1"),
    };

    @BeforeEach
    public void setUp() {
        userManager = new UserManager(new MockUserDAO());
    }

    @Test
    public void testSearchInOneUser() {
        userManager.insert(users[0]);

        User user = userManager.getUser("JohnS");
        assertEquals("JohnS", user.username());
        assertEquals("email1", user.email());
    }

    @Test
    public void testSearchInMultipleUsers() {
        for (User user : users) {
            userManager.insert(user);
        }

        User user = userManager.getUser("Jane");
        assertEquals("Jane", user.username());
        assertEquals("email3", user.email());
    }

    @Test
    public void testSearchNoResults() {
        for (User user : users) {
            userManager.insert(user);
        }

        User user = userManager.getUser("NotAUser");
        assertNull(user);
    }

    @Test
    public void testSearchEmptyQuery() {
        for (User user : users) {
            userManager.insert(user);
        }

        User user = userManager.getUser("");
        assertNull(user);
    }

    @Test
    public void testSearchNullQuery() {
        for (User user : users) {
            userManager.insert(user);
        }

        User user = userManager.getUser(null);
        assertNull(user);
    }

    @Test
    public void testSearchCaseSensitive() {
        for (User user : users) {
            userManager.insert(user);
        }

        User user = userManager.getUser("johns");
        assertNull(user);
    }

    @Test
    public void testSearchPartialQuery() {
        for (User user : users) {
            userManager.insert(user);
        }

        User user = userManager.getUser("John");
        assertNull(user);
    }

    @Test
    public void testSearchEmptyUsers() {
        User user = userManager.getUser("John");
        assertNull(user);
    }

    @Test
    public void testReplaceInOneUser() {
        userManager.insert(users[0]);
        userManager.updatePassword("JohnS", "newPassword");

        User user = userManager.getUser("JohnS");
        assertEquals("JohnS", user.username());
        assertEquals("email1", user.email());
        assertEquals("newPassword", user.hashedPassword());
    }

    @Test
    public void testReplaceInMultipleUsers() {
        for (User user : users) {
            userManager.insert(user);
        }

        userManager.updatePassword("JohnS", "newPassword");

        User user = userManager.getUser("JohnS");
        assertEquals("JohnS", user.username());
        assertEquals("email1", user.email());
        assertEquals("newPassword", user.hashedPassword());
    }

    @Test
    public void testReplaceNoResults() {
        for (User user : users) {
            userManager.insert(user);
        }

        userManager.updatePassword("NotAUser", "newPassword");

        User user = userManager.getUser("NotAUser");
        assertNull(user);
    }

    @Test
    public void testReplaceEmptyQuery() {
        for (User user : users) {
            userManager.insert(user);
        }

        userManager.updatePassword("", "newPassword");

        User user = userManager.getUser("");
        assertNull(user);
    }

    @Test
    public void testReplaceNullQuery() {
        for (User user : users) {
            userManager.insert(user);
        }

        userManager.updatePassword(null, "newPassword");

        User user = userManager.getUser(null);
        assertNull(user);
    }

    @Test
    public void testReplaceEmptyPassword() {
        for (User user : users) {
            userManager.insert(user);
        }

        userManager.updatePassword("Alex", "");

        User user = userManager.getUser("Alex");
        assertEquals("Alex", user.username());
        assertEquals("email4", user.email());
        assertEquals("password1", user.hashedPassword());
    }

    @Test
    public void testReplaceNullPassword() {
        for (User user : users) {
            userManager.insert(user);
        }

        userManager.updatePassword("Alexander", null);

        User userAfterUpdate = userManager.getUser("Alexander");
        assertEquals("Alexander", userAfterUpdate.username());
        assertEquals("email1email1", userAfterUpdate.email());
        assertEquals("password1", userAfterUpdate.hashedPassword());
    }

    @Test
    public void testReplaceCaseSensitive() {
        for (User user : users) {
            userManager.insert(user);
        }

        userManager.updatePassword("johns", "newPassword");

        User user = userManager.getUser("johns");
        assertNull(user);
    }

    @Test
    public void testReplacePartialQuery() {
        for (User user : users) {
            userManager.insert(user);
        }

        userManager.updatePassword("John", "newPassword");

        User user = userManager.getUser("John");
        assertNull(user);
    }

    @Test
    public void testReplaceEmptyUsers() {
        userManager.updatePassword("John", "newPassword");

        User user = userManager.getUser("John");
        assertNull(user);
    }
}

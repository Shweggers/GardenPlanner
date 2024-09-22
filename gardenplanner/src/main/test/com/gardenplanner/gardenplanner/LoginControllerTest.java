package com.gardenplanner.gardenplanner;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import java.util.concurrent.CountDownLatch;

import static org.mockito.Mockito.*;

public class LoginControllerTest {

    // Instance of the LoginController to be tested
    private LoginController loginController;

    // Mock implementation of the UserDAO for testing purposes
    private MockUserDAO userDAOMock;

    // Mock of the DataStore, which holds the logged-in user
    private DataStore dataStoreMock;

    // Initialise JavaFX Toolkit before running any tests
    @BeforeAll
    public static void initToolkit() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await();
    }

    // Set up the test environment before each test case
    @BeforeEach
    public void setUp() {
        dataStoreMock = mock(DataStore.class);  // Mock the DataStore
        loginController = new LoginController(dataStoreMock);

        userDAOMock = new MockUserDAO();

        // Mock the TextField and PasswordField for the LoginController
        loginController.usernameField = mock(TextField.class);
        loginController.passwordField = mock(PasswordField.class);

        // Mock the Scene and Stage to prevent NullPointerException during UI operations
        Scene mockScene = mock(Scene.class);
        Stage mockStage = mock(Stage.class);

        // Mock the behavior of getScene and getWindow methods for TextField
        when(loginController.usernameField.getScene()).thenReturn(mockScene);
        when(mockScene.getWindow()).thenReturn(mockStage);
    }

    // Test when the user is not found in the system
    @Test
    public void testHandleLogin_UserNotFound() throws Exception {
        Platform.runLater(() -> {
            // Simulate input where the user does not exist
            when(loginController.usernameField.getText()).thenReturn("nonexistentUser");
            when(loginController.passwordField.getText()).thenReturn("password");

            // Call handleLogin() and verify that no user is set in the DataStore
            loginController.handleLogin();
            verify(dataStoreMock, never()).setCurrentUser(any(User.class));
        });

        waitForRunLater();
    }

    // Test when the user enters an invalid password
    @Test
    public void testHandleLogin_InvalidPassword() throws Exception {
        Platform.runLater(() -> {
            // Arrange valid user and password
            String username = "validUser";
            String correctPassword = "correctPassword";
            String wrongPassword = "wrongPassword";
            String hashedPassword = BCrypt.hashpw(correctPassword, BCrypt.gensalt());

            // Add a valid user to the MockUserDAO
            User user = new User(username, "valid@user.com", hashedPassword);
            userDAOMock.addUser(user);

            // Simulate wrong password input
            when(loginController.usernameField.getText()).thenReturn(username);
            when(loginController.passwordField.getText()).thenReturn(wrongPassword);

            // Call handleLogin() and verify no user is set in the DataStore
            loginController.handleLogin();
            verify(dataStoreMock, never()).setCurrentUser(any(User.class));
        });

        waitForRunLater();
    }

    // Test when the user enters valid credentials and successfully logs in
    @Test
    public void testHandleLogin_SuccessfulLogin() throws Exception {
        Platform.runLater(() -> {
            // Arrange valid user and correct password
            String username = "validUser";
            String password = "correctPassword";
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            // Add the user to the MockUserDAO
            User user = new User(username, "valid@user.com", hashedPassword);
            userDAOMock.addUser(user);

            // Simulate correct username and password input
            when(loginController.usernameField.getText()).thenReturn(username);
            when(loginController.passwordField.getText()).thenReturn(password);

            // Call handleLogin() and verify the correct user is set in the DataStore
            loginController.handleLogin();
            verify(dataStoreMock).setCurrentUser(user);
        });

        waitForRunLater();
    }

    // Test when the username and password fields are left empty
    @Test
    public void testHandleLogin_EmptyFields() throws Exception {
        Platform.runLater(() -> {
            // Simulate empty input for username and password
            when(loginController.usernameField.getText()).thenReturn("");
            when(loginController.passwordField.getText()).thenReturn("");

            // Call handleLogin() and verify no user is set in the DataStore
            loginController.handleLogin();
            verify(dataStoreMock, never()).setCurrentUser(any(User.class));
        });

        waitForRunLater();
    }

    // Helper method to wait for JavaFX tasks to complete
    private void waitForRunLater() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(latch::countDown);
        latch.await();
    }
}

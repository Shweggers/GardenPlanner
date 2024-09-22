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

    private LoginController loginController;
    private MockUserDAO userDAOMock;
    private DataStore dataStoreMock;

    @BeforeAll
    public static void initToolkit() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.startup(latch::countDown);
        latch.await();
    }

    @BeforeEach
    public void setUp() {
        dataStoreMock = mock(DataStore.class);
        loginController = new LoginController(dataStoreMock);

        userDAOMock = new MockUserDAO();

        loginController.usernameField = mock(TextField.class);
        loginController.passwordField = mock(PasswordField.class);

        Scene mockScene = mock(Scene.class);
        Stage mockStage = mock(Stage.class);

        when(loginController.usernameField.getScene()).thenReturn(mockScene);
        when(mockScene.getWindow()).thenReturn(mockStage);
    }

    @Test
    public void testHandleLogin_UserNotFound() throws Exception {
        Platform.runLater(() -> {
            when(loginController.usernameField.getText()).thenReturn("nonexistentUser");
            when(loginController.passwordField.getText()).thenReturn("password");

            loginController.handleLogin();

            verify(dataStoreMock, never()).setCurrentUser(any(User.class));
        });

        waitForRunLater();
    }

    @Test
    public void testHandleLogin_InvalidPassword() throws Exception {
        Platform.runLater(() -> {
            String username = "validUser";
            String correctPassword = "correctPassword";
            String wrongPassword = "wrongPassword";
            String hashedPassword = BCrypt.hashpw(correctPassword, BCrypt.gensalt());

            User user = new User(username, "valid@user.com", hashedPassword);
            userDAOMock.addUser(user);

            when(loginController.usernameField.getText()).thenReturn(username);
            when(loginController.passwordField.getText()).thenReturn(wrongPassword);

            loginController.handleLogin();

            verify(dataStoreMock, never()).setCurrentUser(any(User.class));
        });

        waitForRunLater();
    }

    @Test
    public void testHandleLogin_SuccessfulLogin() throws Exception {
        Platform.runLater(() -> {
            String username = "validUser";
            String password = "correctPassword";
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            User user = new User(username, "valid@user.com", hashedPassword);
            userDAOMock.addUser(user);

            when(loginController.usernameField.getText()).thenReturn(username);
            when(loginController.passwordField.getText()).thenReturn(password);

            loginController.handleLogin();

            verify(dataStoreMock).setCurrentUser(user);
        });

        waitForRunLater();
    }

    @Test
    public void testHandleLogin_EmptyFields() throws Exception {
        Platform.runLater(() -> {
            when(loginController.usernameField.getText()).thenReturn("");
            when(loginController.passwordField.getText()).thenReturn("");

            loginController.handleLogin();

            verify(dataStoreMock, never()).setCurrentUser(any(User.class));
        });

        waitForRunLater();
    }

    private void waitForRunLater() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(latch::countDown);
        latch.await();
    }
}

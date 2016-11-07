package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jdk.nashorn.internal.runtime.ECMAException;
import sample.main.Main;
import sample.model.UserManagement;

import java.io.IOException;

/**
 * Controller for login screen
 */
public class LoginController {

    @FXML
    private TextField first;

    @FXML
    private PasswordField second;

    @FXML
    private javafx.scene.control.Button closeButton;

    /**
     * Sets up the stage and scene to show when the user is logging in.
     *
     * @throws IOException if for some reason the file is not found.
     *
     */
    @FXML
    private void showLoggedInScreen() throws IOException {
        if (UserManagement.verify(first.getText(), second.getText())) {

            try {
                Parent root = FXMLLoader.load(getClass().getResource("/mainScreen.fxml"));
                Stage primaryStage = (Stage) closeButton.getScene().getWindow();
                primaryStage.setTitle("Main Screen");
                primaryStage.setScene(new Scene(root, 600, 400));
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("I/O ERROR");
            }
        } else {
            System.out.println("The given username or password is incorrect.");
        }
    }

    /**
     * Button handler for closing the login screen when the user presses the button.
     *
     */
    @FXML
    private void cancelHit() {
        try {
            Stage primaryStage = (Stage) closeButton.getScene().getWindow();
            Main mainclass = new Main();
            mainclass.start(primaryStage);
        } catch (Exception e) {
        }
    }
}

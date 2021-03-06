package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.main.Main;
import sample.model.UserManagement;


/**
 * The controller for the root/main window
 *
 */
public class RegisterController {

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    private TextField userfx;

    @FXML
    private PasswordField passfx;

    private String userType;


    /**
     * Handles closing the stage of the register screen.
     *
     */
    @FXML
    private void cancelPressed() {
        try {
            Stage primaryStage = (Stage) closeButton.getScene().getWindow();
            Main mainClass = new Main();
            mainClass.start(primaryStage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Handles registering a user once they press register.
     *
     */
    @FXML
    private void registerpressed()  {
        UserManagement.register(userfx.getText(), passfx.getText(), userType);
        cancelPressed();
    }

    /**
     * sets user Type to user when pressed.
     *
     */
    @FXML
    private void handle1() {
        userType = "User";
    }

    /**
     * sets user Type to worker when pressed.
     *
     */
    @FXML
    private void handle2() {
        userType = "Worker";
    }

    /**
     * sets user Type to manager when pressed.
     *
     */
    @FXML
    private void handle3() {
        userType = "Manager";
    }

    /**
     * sets user Type to admin when pressed.
     *
     */
    @FXML
    private void handle4() {
        userType = "Admin";
    }



}
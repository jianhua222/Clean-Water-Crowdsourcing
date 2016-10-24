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

    /** reference back to mainApplication if needed */
    private Main mainApplication;

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    private TextField userfx;

    @FXML
    private PasswordField passfx;

    private String usertype;

    /**
     * allow for calling back to the main application code if necessary
     * @param main   the reference to the FX Application instance
     * */
    public void setMainApp(Main main) {
        mainApplication = main;
    }

    /**
     * Close menu item event handler
     */
    @FXML
    private void handleCloseMenu() {
        System.exit(0);
    }

    /**
     * Handles closing the stage of the register screen.
     *
     */
    @FXML
    private void cancelPressed() {

        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Handles registering a user once they press register.
     *
     */
    @FXML
    private void registerpressed()  {
        UserManagement.register(userfx.getText(), passfx.getText(), usertype);
        cancelPressed();
    }

    /**
     * sets usertype to user when pressed.
     *
     */
    @FXML
    private void handle1() {
        usertype = "User";
    }

    /**
     * sets usertype to worker when pressed.
     *
     */
    @FXML
    private void handle2() {
        usertype = "Worker";
    }

    /**
     * sets usertype to manager when pressed.
     *
     */
    @FXML
    private void handle3() {
        usertype = "Manager";
    }

    /**
     * sets usertype to admin when pressed.
     *
     */
    @FXML
    private void handle4() {
        usertype = "Admin";
    }



}
package sample;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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

    @FXML
    private void cancelPressed() {

        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void registerpressed() throws IOException {
        try {
            UserManagement.register(userfx.getText(), passfx.getText(), usertype);
            cancelPressed();
        } catch (IOException e) {
            throw new IOException("new IO!!");
        }
    }

    @FXML
    private void handle1() {
        usertype = "User";
    }

    @FXML
    private void handle2() {
        usertype = "Worker";
    }

    @FXML
    private void handle3() {
        usertype = "Manager";
    }

    @FXML
    private void handle4() {
        usertype = "Admin";
    }



}
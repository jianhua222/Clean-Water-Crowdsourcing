package sample;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;

/**
 * The controller for the root/main window
 *
 */
public class WelcomeController {

    /** reference back to mainApplication if needed */
    private Main mainApplication;

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
    private void addLoginPressed() {
        mainApplication.showLoginScreen();

    }

    @FXML
    private void addRegisterPressed() {

    }

}

package sample;

import javafx.fxml.FXML;

/**
 * The controller for the root/main window
 *
 */
public class welcomeController {

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

    /**
     * shows the login screen when the user pressed the button.
     *
     */
    @FXML
    private void addLoginPressed() {
        mainApplication.showLoginScreen();
    }

    /**
     * shows the register screen when the user presses the button.
     *
     */
    @FXML
    private void addRegisterPressed() {
        mainApplication.showRegisterScreen();
    }

}

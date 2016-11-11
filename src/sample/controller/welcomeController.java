package sample.controller;

import javafx.fxml.FXML;
import sample.main.Main;

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

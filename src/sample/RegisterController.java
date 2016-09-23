package sample;

import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * The controller for the root/main window
 *
 */
public class RegisterController {

    /** reference back to mainApplication if needed */
    private Main mainApplication;

    @FXML
    private javafx.scene.control.Button closeButton;

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

}
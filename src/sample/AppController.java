package sample;

import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Created by Ashwin on 9/23/16.
 */
public class AppController {

    @FXML
    private javafx.scene.control.Button logoutButton;

    @FXML
    private void something() {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }
}

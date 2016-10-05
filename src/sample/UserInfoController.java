package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by Ashwin on 10/3/16.
 */
public class UserInfoController {

    @FXML
    private Label unfx;

    @FXML
    private Label titfx;

    @FXML
    private Label emfx;

    @FXML
    private Label adfx;

    @FXML
    private Label tyfx;

    @FXML
    private TextField cufx;

    @FXML
    private TextField ctfx;

    @FXML
    private TextField cefx;

    @FXML
    private TextField cafx;

    /**
     * Initialize the user info screen for the application.
     *
     */
    @FXML
    private void initialize() {
        if (!cufx.getText().equals("")) {
            UserManagement.getUser().setUsername(cufx.getText());
        }
        if (!ctfx.getText().equals("")) {
            UserManagement.getUser().setTitle(ctfx.getText());
        }
        if (!cefx.getText().equals("")) {
            UserManagement.getUser().setEmail(cefx.getText());
        }
        if (!cafx.getText().equals("")) {
            UserManagement.getUser().setAddress(cafx.getText());
        }
        unfx.setText(UserManagement.getUser().getUserName());
        titfx.setText(UserManagement.getUser().getTitle());
        emfx.setText(UserManagement.getUser().getEmail());
        adfx.setText(UserManagement.getUser().getAddress());
        tyfx.setText(UserManagement.getUser().getType());
    }

    /**
     * Button handler for closing the user info window.
     *
     */
    @FXML
    private void closedat() {
        Stage stage = (Stage) emfx.getScene().getWindow();
        stage.close();
    }
}

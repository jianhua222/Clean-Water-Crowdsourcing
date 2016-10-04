package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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
    private TextField cufx;

    @FXML
    private TextField ctfx;

    @FXML
    private TextField cefx;

    @FXML
    private TextField cafx;

    @FXML
    private void initialize() {
        if (!cufx.getText().equals("")) {
            UserManagement.getUser().setUsername(cufx.getText());
        }
        if (!ctfx.getText().equals("")) {
            UserManagement.getUser().setType(ctfx.getText());
        }
        if (!cefx.getText().equals("")) {
            UserManagement.getUser().setEmail(cefx.getText());
        }
        if (!cafx.getText().equals("")) {
            UserManagement.getUser().setAddress(cafx.getText());
        }
        unfx.setText(UserManagement.getUser().getUserName());
        titfx.setText(UserManagement.getUser().getType());
        emfx.setText(UserManagement.getUser().getEmail());
        adfx.setText(UserManagement.getUser().getAddress());
    }

    @FXML
    private void closedat() {
        Stage stage = (Stage) emfx.getScene().getWindow();
        stage.close();
    }
}

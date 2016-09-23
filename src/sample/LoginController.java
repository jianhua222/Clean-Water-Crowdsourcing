package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for login screen
 */
public class LoginController {
    @FXML
    private PasswordField first;

    @FXML
    private PasswordField second;

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    private void showLoggedInScreen() {
        if (first.getText().equals("user") && second.getText().equals("pass")) {

            try {
                Parent root = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
                Stage primaryStage = new Stage();
                primaryStage.setTitle(" LOGIN");
                primaryStage.setScene(new Scene(root, 600, 400));
                primaryStage.show();

            } catch (IOException e) {

            }
        } else {
            System.out.println("The given username or password is incorrect.");
        }
    }

    @FXML
    private void cancelHit() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}

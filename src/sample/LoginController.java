package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for login screen
 */
public class LoginController {
    @FXML
    private TextField first;

    @FXML
    private PasswordField second;

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    private void showLoggedInScreen() throws IOException {
        if (UserManagement.verify(first.getText(), second.getText())) {

            try {
                Parent root = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
                Stage primaryStage = new Stage();
                primaryStage.setTitle(" LOGIN");
                primaryStage.setScene(new Scene(root, 600, 400));
                primaryStage.show();
                Stage stage = (Stage) closeButton.getScene().getWindow();
                stage.close();


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

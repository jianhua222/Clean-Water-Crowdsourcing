package sample;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


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

    @FXML
    private void userInfo() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("UserInfo.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle(" LOGIN");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();

        } catch (IOException e) {

        }
    }
}

package sample;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;


/**
 * Created by Ashwin on 9/23/16.
 */
public class AppController {
    // **Note Change UI to where we are only changing scenes
    // and not creating new stages each time.

    @FXML
    private javafx.scene.control.Button logoutButton;

    /**
     * This method handles closing the main app, when the user chooses to do so.
     *
     */
    @FXML
    private void something() {
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
    }

    /**
     * This method sets up the stage and screen for showing the user info.
     *
     */
    @FXML
    private void userInfo() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("UserInfo.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("User Information");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();

        } catch (IOException e) {
            //System.out.println("I/O ERROR");
        }
    }

    @FXML
    private void toReportScreen() {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("WaterSourceReport.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Water Source Report");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("I/O ERROR");
        }
    }

    /**
     * This method sets up the stage and screen for showing the user info.
     *
     */
    @FXML
    private void toWaterSourceList() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("WaterSourceList.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle("Water Source List");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("WaterSourceList.fxml I/O ERROR");
        }
    }

}

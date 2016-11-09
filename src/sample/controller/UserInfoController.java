package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.model.UserManagement;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(
                            UserManagement.getUser().getUserName() + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(UserManagement.getUser());
            //out.writeObject(b);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * Button handler for closing the user info window.
     *
     */
    @FXML
    private void closedat() {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/mainScreen.fxml"));
            Stage primaryStage = (Stage) unfx.getScene().getWindow();
            primaryStage.setTitle("Main Screen");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("I/O ERROR");
        }
    }
}

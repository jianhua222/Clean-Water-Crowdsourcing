package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    /** the main container for the application window */
    private Stage mainScreen;

    /** the main layout for the main window */
    private Pane rootLayout;

    @Override
    public void start(Stage primaryStage) throws Exception {
        mainScreen = primaryStage;
        initRootLayout(mainScreen);
    }

    /**
     * Initialize the main screen for the application.  Most other views will be shown in this screen.
     *
     * @param mainScreen  the main Stage window of the application
     */
    private void initRootLayout(Stage mainScreen) {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("welcome.fxml"));
            rootLayout = loader.load();

            // Give the controller access to the main app.
            welcomeController controller = loader.getController();
            controller.setMainApp(this);

            // Set the Main App title
            mainScreen.setTitle("Clean Water Crowdsourcing");

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            mainScreen.setScene(scene);
            mainScreen.show();

        } catch (IOException e) {
        }
    }

    /**
     * this is the main method
     *
     * @param args the array string passed into this method
     */
    public static void main(String[] args) {

        launch(args);
    }

    /**
     * This method sets up the stage and scene when the user is logging in.
     *
     */
    public void showLoginScreen() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle(" LOGIN");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();

        } catch (IOException e) {

        }
    }

    /**
     * This method sets up the stage and scene when the user is registering.
     *
     */
    public void showRegisterScreen() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
            Stage primaryStage = new Stage();
            primaryStage.setTitle(" Register");
            primaryStage.setScene(new Scene(root, 600, 400));
            primaryStage.show();

        } catch (IOException e) {

        }
    }
}

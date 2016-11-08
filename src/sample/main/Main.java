package sample.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.controller.welcomeController;
import sample.model.WaterSourceReportList;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

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
            loader.setLocation(Main.class.getResource("/welcome.fxml"));
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
        try {
            WaterSourceReportList tem = new WaterSourceReportList();
            FileOutputStream fileOut =
                    new FileOutputStream("ReportBase.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(tem);
            //out.writeObject(b);
            out.close();
            fileOut.close();
            //System.out.printf("Serialized data is saved in /tmp/employee.ser");
        }catch(IOException i) {
            i.printStackTrace();
        }

        launch(args);
    }

    /**
     * This method sets up the stage and scene when the user is logging in.
     *
     */
    public void showLoginScreen() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
            mainScreen.setTitle("LOGIN");
            mainScreen.setScene(new Scene(root, 600, 400));
            mainScreen.show();

        } catch (IOException e) {

        }
    }

    /**
     * This method sets up the stage and scene when the user is registering.
     *
     */
    public void showRegisterScreen() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/register.fxml"));
            mainScreen.setTitle(" Register");
            mainScreen.setScene(new Scene(root, 600, 400));
            mainScreen.show();

        } catch (IOException e) {

        }
    }
}

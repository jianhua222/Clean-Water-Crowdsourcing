package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class UserInfoController {

    @FXML
    private javafx.scene.control.Button changes;

    private final ObservableList<Person> data =
            FXCollections.observableArrayList(new user());

    @FXML
    private void changesMade() {
        Stage stage = (Stage) changes.getScene().getWindow();
        stage.close();
    }


}

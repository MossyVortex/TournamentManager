import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class RegisterController {

    @FXML
    private Button adminButton;

    @FXML
    private ImageView adminImage;

    @FXML
    private ImageView backButton;

    @FXML
    private ImageView logoImage;

    @FXML
    private Label regLable;

    @FXML
    private Button studentImage;

    @FXML
    void adminButtonOnclicked(ActionEvent event) {
//        Parent fxmlLoader = null;
//        try {
//            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("RegisterAdminScene.fxml")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Scene registerAdminPage = new Scene(fxmlLoader);
//        Stage stage = new Stage();
//        stage.setScene(registerAdminPage);
//        stage.setTitle("Tournament Manager - Register - Admin");
//        stage.show();
    }

    @FXML
    void studentButtonOnclicked(ActionEvent event) {
//        Parent fxmlLoader = null;
//        try {
//            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("RegisterStudentScene.fxml")));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Scene registerStudentPage = new Scene(fxmlLoader);
//        Stage stage = new Stage();
//        stage.setScene(registerStudentPage);
//        stage.setTitle("Tournament Manager - Register - Student");
//        stage.show();
    }

}

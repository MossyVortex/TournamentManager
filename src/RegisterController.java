import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("RegisterAdminScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene registerAdminPage = new Scene(fxmlLoader);
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
        stage.setScene(registerAdminPage);
        stage.setTitle("classes.Tournament Manager - Register - Admin");
        stage.show();
    }

    @FXML
    void studentButtonOnclicked(ActionEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("RegisterStudentScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene registerStudentPage = new Scene(fxmlLoader);
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
        stage.setScene(registerStudentPage);
        stage.setTitle("classes.Tournament Manager - Register - classes.Student");
        stage.show();
    }


    @FXML
    void setBackButton(MouseEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene loginStudentPage = new Scene(fxmlLoader);
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
        stage.setScene(loginStudentPage);
        stage.setTitle("classes.Tournament Manager - Login");
        stage.show();
    }

}

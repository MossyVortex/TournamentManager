import java.io.IOException;
import java.util.Objects;
import javafx.scene.Node;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class EditAdminProfileController {

    @FXML
    private Label IDLabel;

    @FXML
    private Label authorizedLabel;

    @FXML
    private TextField authorizedTextField;

    @FXML
    private ImageView backButton;

    @FXML
    private Label emailLable1;

    @FXML
    private Label nameLable;

    @FXML
    private Label nameLable1;

    @FXML
    private Label nameLable11;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField nameTextField1;

    @FXML
    private TextField nameTextField11;

    @FXML
    private Button regesterButton;

    @FXML
    private Label tournamentsNumLabel;

    @FXML
    private TextField tournamentsNumTextField;

    @FXML
    private Button saveButton;

    @FXML
    void regesterButtonOnclicked(ActionEvent event) {

    }

    @FXML
    void SaveButtonOnClicked(ActionEvent event) {

    }

    @FXML
    void setBackButton(MouseEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminHomeScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene loginStudentPage = new Scene(fxmlLoader);
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
        stage.setScene(loginStudentPage);
        stage.setTitle("Tournament Manager - Admin Home");
        stage.show();

    }

}

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

public class EditStudentProfileController {

    @FXML
    private ImageView backButton;

    @FXML
    private Label nameLable;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label phoneLable;

    @FXML
    private TextField phoneTextField;

    @FXML
    private Label IDLabel;

    @FXML
    private TextField IDTextField;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField EmailTextField;

    @FXML
    private Label passwordLable;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label HeightLabel;

    @FXML
    private TextField heightLabel;

    @FXML
    private Label WinsLabel;

    @FXML
    private TextField WinsTextField;

    @FXML
    private Label LossesLabel;

    @FXML
    private TextField LossesTextField;

    @FXML
    private Label TiesLabel;

    @FXML
    private TextField TiesTextField;

    @FXML
    private Label WeightLabel;

    @FXML
    private TextField WeightTextField;

    @FXML
    private Label TournamentsNumLabel;

    @FXML
    private TextField TournamentsNumTextField;

    @FXML
    private Label nameLable11111;

    @FXML
    private TextField nameTextField11111;

    @FXML
    private Button EditButton;

    @FXML
    void regesterButtonOnclicked(ActionEvent event) {

    }

    @FXML
    void setBackButton(MouseEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StudentHomeScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene loginStudentPage = new Scene(fxmlLoader);
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
        stage.setScene(loginStudentPage);
        stage.setTitle("Tournament Manager - Student Home");
        stage.show();

    }

}

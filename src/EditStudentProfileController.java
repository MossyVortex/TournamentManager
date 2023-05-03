import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import classes.Student;
import javafx.fxml.Initializable;
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

public class EditStudentProfileController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream("src\\LogedinPerson.dat"));
            Student student = (Student) objInStream.readObject();
            nameTextField.setText(student.getName());
            IDTextField.setText(student.getID());
            EmailTextField.setText(student.getEmail());
            WinsTextField.setText(String.valueOf(student.getWins()));
            LossesTextField.setText(String.valueOf(student.getLosses()));
            TiesTextField.setText(String.valueOf(student.getTies()));
            WeightTextField.setText(String.valueOf(student.getWeight()));
            heightLabel.setText(String.valueOf(student.getHeight()));
            phoneTextField.setText(student.getPhoneNumber());
            objInStream.close();
//            TournamentsNumTextField.setText(student.getJoinedTournamentsID());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

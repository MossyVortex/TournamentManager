import classes.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class EditStudentProfileController implements Initializable {

    @FXML
    private ImageView EditButton;

    @FXML
    private TextField EmailTextField;

    @FXML
    private Label HeightLabel;

    @FXML
    private Label IDLabel;

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
    private Label WinsLabel;

    @FXML
    private TextField WinsTextField;

    @FXML
    private ImageView backButton;

    @FXML
    private Label emailLabel;

    @FXML
    private TextField heightTextField;

    @FXML
    private Label nameLable;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label passwordLable;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Label phoneLable;

    @FXML
    private TextField phoneTextField;

    @FXML
    private Button saveButton;

    @FXML
    void EditButtonOnClicked(MouseEvent event) {

    }

    @FXML
    void saveButtonOnClicked(ActionEvent event) {

    }

    @FXML
    void setBackButton(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream("src\\LogedinPerson.dat"));
            Student student = (Student) objInStream.readObject();
            objInStream.close();

            IDLabel.setText(student.getID());
            WinsTextField.setText(String.valueOf(student.getWins()));
            TiesTextField.setText(String.valueOf(student.getTies()));
            LossesTextField.setText(String.valueOf(student.getLosses()));

            nameTextField.setText(student.getName());
            EmailTextField.setText(student.getEmail());
            heightTextField.setText(String.valueOf(student.getHeight()));

            phoneTextField.setText(student.getPhoneNumber());
            passwordTextField.setText(student.getPassword());
            WeightTextField.setText(String.valueOf(student.getWeight()));

            WinsTextField.setEditable(false);TiesTextField.setEditable(false);LossesTextField.setEditable(false);nameTextField.setEditable(false);EmailTextField.setEditable(false);
            heightTextField.setEditable(false);phoneTextField.setEditable(false);passwordTextField.setEditable(false);WeightTextField.setEditable(false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

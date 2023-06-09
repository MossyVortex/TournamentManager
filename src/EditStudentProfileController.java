import classes.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
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
    private Label usernameLable;

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
        saveButton.setVisible(true);
        EditButton.setVisible(false);

        nameTextField.setEditable(true);EmailTextField.setEditable(true);
        heightTextField.setEditable(true);phoneTextField.setEditable(true);
        passwordTextField.setEditable(true);WeightTextField.setEditable(true);

    }

    @FXML
    void saveButtonOnClicked(ActionEvent event) {
        boolean isChanged = false;
        try {
            ObjectInputStream objInStreamLogedinPerson = new ObjectInputStream(new FileInputStream("src\\LogedinPerson.dat"));
            Student student = (Student) objInStreamLogedinPerson.readObject();
            objInStreamLogedinPerson.close();


            String breviousUN = student.getUserName();

            if (!nameTextField.getText().equals(student.getName())) {
                student.setName(nameTextField.getText()); isChanged=true;
            }
            if (!EmailTextField.getText().equals(student.getEmail())) {
                student.setEmail(EmailTextField.getText()); isChanged=true;
            }
            if (!passwordTextField.getText().equals(student.getPassword())) {
                student.setPassword(passwordTextField.getText()); isChanged=true;
            }
            if (!phoneTextField.getText().equals(student.getPhoneNumber())) {
                student.setPhoneNumber(phoneTextField.getText()); isChanged=true;
            }
            if (!(Integer.parseInt(heightTextField.getText())==(student.getHeight()))) {
                student.setHeight(Integer.parseInt(heightTextField.getText())); isChanged=true;
            }
            if (!(Integer.parseInt(WeightTextField.getText())==(student.getWeight()))) {
                student.setWeight(Integer.parseInt(WeightTextField.getText())); isChanged=true;
            }
            System.out.println(isChanged);
            if (isChanged){
                ObjectInputStream objInStreamStudentsBFile = new ObjectInputStream(new FileInputStream("src\\StudentsBFile.dat"));
                HashMap<String, Student> studentHashMap = (HashMap<String, Student>) objInStreamStudentsBFile.readObject();
                objInStreamStudentsBFile.close();

                studentHashMap.remove(breviousUN);
                studentHashMap.put(student.getUserName(),student);

                ObjectOutputStream objOutStreamStudentsBFile = new ObjectOutputStream(new FileOutputStream("src\\StudentsBFile.dat"));
                objOutStreamStudentsBFile.writeObject(studentHashMap);
                objOutStreamStudentsBFile.close();

                ObjectOutputStream objOutStreamLogedinPerson = new ObjectOutputStream(new FileOutputStream("src\\LogedinPerson.dat"));
                objOutStreamLogedinPerson.writeObject(student);
                objOutStreamLogedinPerson.close();
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        EditButton.setVisible(true); saveButton.setVisible(false);
        WinsTextField.setEditable(false);TiesTextField.setEditable(false);LossesTextField.setEditable(false);
        nameTextField.setEditable(false);EmailTextField.setEditable(false);
        heightTextField.setEditable(false);phoneTextField.setEditable(false);passwordTextField.setEditable(false);WeightTextField.setEditable(false);


    }

    @FXML
    void setBackButton(MouseEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StudentHomeScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene homePage = new Scene(fxmlLoader);
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
        stage.setScene(homePage);
        stage.setTitle("Tournament Manager - Home Page");
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream("src\\LogedinPerson.dat"));
            Student student = (Student) objInStream.readObject();
            objInStream.close();

            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src\\LogedinFrom.dat"));
            String LogedinFrom = (String) objectInputStream.readObject();
            objectInputStream.close();

            if (LogedinFrom.equals("API"))
                EditButton.setVisible(false);

            usernameLable.setText(student.getUserName());
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

            WinsTextField.setEditable(false);TiesTextField.setEditable(false);LossesTextField.setEditable(false);
            nameTextField.setEditable(false);EmailTextField.setEditable(false);
            heightTextField.setEditable(false);phoneTextField.setEditable(false);passwordTextField.setEditable(false);WeightTextField.setEditable(false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void EditButtonExit(MouseEvent event) {
        EditButton.setOpacity(1);
    }
    @FXML
    void EditButtonMoved(MouseEvent event) {
        EditButton.setOpacity(0.8);
    }
    @FXML
    void backButtonExit(MouseEvent event) {
        backButton.setOpacity(1);
    }
    @FXML
    void backButtonMoved(MouseEvent event) {
        backButton.setOpacity(0.8);
    }
    @FXML
    void saveButtonExit(MouseEvent event) {
        saveButton.setOpacity(1);
    }
    @FXML
    void saveButtonMoved(MouseEvent event) {
        saveButton.setOpacity(0.8);
    }
}

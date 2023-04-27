import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class LoginController {

    @FXML
    private ImageView KFUPMIcon;

    @FXML
    private RadioButton adminRadioButton;

    @FXML
    private Label emailLable;

    @FXML
    private TextField emailTextF;

    @FXML
    private Label existingLable;

    @FXML
    private ImageView iconLogo;

    @FXML
    private Button loginButton;

    @FXML
    private Label loginLable;

    @FXML
    private ImageView passwordEye;

    @FXML
    private Label passwordLable;

    @FXML
    private PasswordField passwordTextF;

    @FXML
    private Label registerLable;

    @FXML
    private RadioButton studentRadioButton;

    @FXML
    void registerLabelOnClicked(MouseEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("RegesterScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene registerPage = new Scene(fxmlLoader);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setScene(registerPage);
        stage.setTitle("Tournament Manager - Register");
        stage.show();
    }

    @FXML
    void adminRadioButtonOnClicked(ActionEvent event) {
        studentRadioButton.setSelected(false);
    }

    @FXML
    void studentRadioButtonOnClicked(ActionEvent event) {
        adminRadioButton.setSelected(false);
    }

    @FXML
    void passwordEyeOnClicked(MouseEvent event) {

    }

    @FXML
    void loginButtonOnClicked(ActionEvent event) throws IOException {
       if(!emailTextF.getText().isEmpty() && !passwordTextF.getText().isEmpty()){
        if(studentRadioButton.isSelected()){
            FileReader student = new FileReader("StudentsFile.csv");
            BufferedReader readStudentFile = new BufferedReader(student);
            String line = readStudentFile.readLine();
            while(line != null){
                String[] valuesOfStudentFile = line.split(",");
                if(valuesOfStudentFile[3].equals(emailTextF.getText())){
                    if(valuesOfStudentFile[4].equals(passwordTextF.getText())){
                        Parent fxmlLoader = null;
                    try {
                         fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomeScene.fxml")));
                    } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene homePage = new Scene(fxmlLoader);
                        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
                        stage.setScene(homePage);
                        stage.setTitle("Tournament Manager - Home Page");
                        stage.show();
                    }
                        
                }
            }
        System.out.println("One of the email or password is/are not correct");
        }else if(adminRadioButton.isSelected()){
            FileReader Admin = new FileReader("AdminsFile.csv");
            BufferedReader readAdmins = new BufferedReader(Admin);
            String line = readAdmins.readLine();
            while(line != null){
                String[] valuesOfAdminFile = line.split(",");
                if(valuesOfAdminFile[3].equals(emailTextF.getText())){
                    if(valuesOfAdminFile[4].equals(passwordTextF.getText())){
                        Parent fxmlLoader = null;
                    try {
                         fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HomeScene.fxml")));
                    } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene homePage = new Scene(fxmlLoader);
                        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
                        stage.setScene(homePage);
                        stage.setTitle("Tournament Manager - Home Page");
                        stage.show();
                    }
                }

            }
            System.out.println("One of the email or password is/are not correct");
        }
       }else{
        System.out.println("fill the blank");
       }

    }
}

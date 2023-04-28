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


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

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
     if((! emailTextF.getText().isEmpty() && !passwordTextF.getText().isEmpty())||(studentRadioButton.isSelected() || adminRadioButton.isSelected())){
        if(studentRadioButton.isSelected()){
            File student = new File("StudentsFile.csv");
            Scanner studentRead = new Scanner(student);
            studentRead.useDelimiter(",|\n");
            String line = studentRead.nextLine();
            String emailFound = "";
            String passwordFound = "";
            while(studentRead.hasNext()){
                System.out.println(studentRead.nextLine());
                if(studentRead.next().equals(emailTextF.getText())){
                    emailFound = studentRead.next();
                }
            }
            System.out.println(emailFound);
            File student2 = new File("StudentsFile.csv");
            Scanner studentRead2 = new Scanner(student2);
            studentRead2.useDelimiter(",|\n");
            String line2 = studentRead2.nextLine();
            while(studentRead2.hasNext()){
                if(studentRead2.next().equals(passwordTextF.getText())){
                    passwordFound = studentRead2.next();
                }
            }
            if(!emailFound.equals("")&& !passwordFound.equals("")){
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
            }else{
                System.out.println("Email or Password is/are incorrect");
            }
        }else if(adminRadioButton.isSelected()){
            File admin = new File("AdminsFile.csv");
            Scanner adminRead = new Scanner(admin);
            adminRead.useDelimiter(",|\n");
            while(adminRead.hasNext()){
                if(adminRead.next().equals(emailTextF.getText())){
                    while(adminRead.hasNext()){
                        if(adminRead.next().equals(passwordTextF.getText())){
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
            }
            System.out.println("Email or Password is/are incorrect");
        }
     } else if(! emailTextF.getText().isEmpty() && !passwordTextF.getText().isEmpty()){
        System.out.println("Which one will log in ");
     }
     if(emailTextF.getText().isEmpty()){
        System.out.println("Email is missing");
        emailLable.setTextFill(Paint.valueOf("#FF0000"));
     }
     if(passwordTextF.getText().isEmpty()){
        System.out.println("passowrd is missing");
        emailLable.setTextFill(Paint.valueOf("#FF0000"));
     }

    }
}

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
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
     if(allFilled()){
        if(studentRadioButton.isSelected()){
            File StudentForEmail = new File("StudentsFile.csv");
            Scanner studentRead1 = new Scanner(StudentForEmail);
            studentRead1.useDelimiter(",|\n");
            String line1 = studentRead1.nextLine();
            String emailFound = "";
            while(studentRead1.hasNext()){
                if(studentRead1.next().equals(emailTextF.getText())){
                    emailFound = emailFound + emailTextF.getText();
                }
            }
            String passwordFound = "";
            File studentForPassword = new File("StudentsFile.csv");
            Scanner studentRead2 = new Scanner(studentForPassword);
            studentRead2.useDelimiter(",|\n");
            String line2 = studentRead2.nextLine();
            while(studentRead2.hasNext()){
                if(studentRead2.next().equals(passwordTextF.getText())){
                    passwordFound = passwordFound + passwordTextF.getText();
                }
            }
            if(!emailFound.equals("") && !passwordFound.equals("")){
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
        }
     }

    }




    public boolean allFilled(){
        boolean allFilled = true;
        passwordLable.setTextFill(Paint.valueOf("#386641")); emailLable.setTextFill(Paint.valueOf("#386641"));
        emailTextF.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));
        passwordTextF.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));
        if(studentRadioButton.isSelected() || adminRadioButton.isSelected()){
            allFilled = true;
        }else{
            allFilled = false;
        }


        if(emailTextF.getText().isEmpty()){
            emailLable.setTextFill(Paint.valueOf("#BC4749"));
            emailTextF.setBorder(new Border(new BorderStroke(Paint.valueOf("#BC4749"), BorderStrokeStyle.SOLID,null,null)));
            allFilled = false;
        }


        if(passwordTextF.getText().isEmpty()){
            passwordLable.setTextFill(Paint.valueOf("#BC4749"));
            passwordTextF.setBorder(new Border(new BorderStroke(Paint.valueOf("#BC4749"), BorderStrokeStyle.SOLID,null,null)));
            allFilled = false;
        }
        return allFilled;
    }
}

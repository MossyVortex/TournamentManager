import classes.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class LoginController {

    @FXML
    private ImageView KFUPMIcon;

    @FXML
    private RadioButton adminRadioButton;

    @FXML
    private Label IDLable;

    @FXML
    private TextField IDTextF;

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
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("RegisterStudentScene.fxml")));
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
    void loginButtonMovedOn(MouseEvent event) {
        loginButton.setBackground(new Background(new BackgroundFill(Color.GREEN,new CornerRadii(12),null)));
    }


    @FXML
    void loginButtonOnClicked(ActionEvent event) throws IOException {
        if(allFilled()) {
            try {
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src\\LogedinPerson.dat"));
                if (adminRadioButton.isSelected()){
                    ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream("src\\AdminsBFile.dat"));
                    HashMap<String, Admin> readAdminInfoMap = (HashMap<String, Admin>) objInStream.readObject();
                    objInStream.close();
                    if (readAdminInfoMap.containsKey(IDTextF.getText())){
                        if (readAdminInfoMap.get(IDTextF.getText()).getPassword().equals(passwordTextF.getText())) {
                            outputStream.writeObject(readAdminInfoMap.get(IDTextF.getText()));
                            Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminHomeScene.fxml")));
                            Scene homePage = new Scene(fxmlLoader);
                            Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
                            stage.setScene(homePage);
                            stage.setTitle("Tournament Manager - Home Page");
                            stage.show();

                        }
                        else
                            Alert("Wrong Password!");
                    }
                    else
                        Alert("Your ID is Not Valid");
                }
                else if (studentRadioButton.isSelected()){
                    ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream("src\\StudentsBFile.dat"));
                    HashMap<String, Student> readStudentInfoMap = (HashMap<String, Student>) objInStream.readObject();
                    objInStream.close();
                    if (readStudentInfoMap.containsKey(IDTextF.getText())){
                        if (readStudentInfoMap.get(IDTextF.getText()).getPassword().equals(passwordTextF.getText())) {
                            outputStream.writeObject(readStudentInfoMap.get(IDTextF.getText()));
                            Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StudentHomeScene.fxml")));
                            Scene homePage = new Scene(fxmlLoader);
                            Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
                            stage.setScene(homePage);
                            stage.setTitle("Tournament Manager - Home Page");
                            stage.show();
                        }
                        else
                            Alert("Wrong Password!");
                    }
                    else
                        Alert("Your ID is Not Valid");
                }
                outputStream.close();


            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public void Alert(String error){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Input not valid");
        errorAlert.setContentText(error);
        errorAlert.showAndWait();
    }



    public boolean allFilled(){
        boolean allFilled = true;
        passwordLable.setTextFill(Paint.valueOf("#386641")); IDLable.setTextFill(Paint.valueOf("#386641"));
        studentRadioButton.setTextFill(Paint.valueOf("#386641"));adminRadioButton.setTextFill(Paint.valueOf("#386641"));
        IDTextF.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));
        passwordTextF.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));
        if(studentRadioButton.isSelected() || adminRadioButton.isSelected()){
            allFilled = true;
        }else{
            allFilled = false;
        }


        if(IDTextF.getText().isEmpty()){
            IDLable.setTextFill(Paint.valueOf("#BC4749"));
            IDTextF.setBorder(new Border(new BorderStroke(Paint.valueOf("#BC4749"), BorderStrokeStyle.SOLID,null,null)));
            allFilled = false;
        }


        if(passwordTextF.getText().isEmpty()){
            passwordLable.setTextFill(Paint.valueOf("#BC4749"));
            passwordTextF.setBorder(new Border(new BorderStroke(Paint.valueOf("#BC4749"), BorderStrokeStyle.SOLID,null,null)));
            allFilled = false;
        }
        if (!(studentRadioButton.isSelected()||adminRadioButton.isSelected())){
            studentRadioButton.setTextFill(Paint.valueOf("#BC4749"));
            adminRadioButton.setTextFill(Paint.valueOf("#BC4749"));
            allFilled = false;
        }
        return allFilled;
    }
}

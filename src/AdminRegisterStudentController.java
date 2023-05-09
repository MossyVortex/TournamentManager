import classes.Person;
import classes.Student;
import classes.Tournament;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
public class AdminRegisterStudentController {
    @FXML
    private Label IDLable;

    @FXML
    private TextField IDTextField;

    @FXML
    private ImageView backButton;

    @FXML
    private Label emailLable;

    @FXML
    private TextField emailTextField;

    @FXML
    private Label heightLable;

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
    private Button registerButton;

    @FXML
    private Label usernameLable;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Label weightLable;

    @FXML
    private TextField weightTextField;

    @FXML
    void backButtonOnClicked(MouseEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminHomeScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene registerStudentPage = new Scene(fxmlLoader);
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
        stage.setScene(registerStudentPage);
        stage.setTitle("Tournament Manager - Home Page");
        stage.show();
    }

    @FXML
    void registerButtonOnClicked(ActionEvent event) {

        if (allFilled()) {
            try {

                ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream("src\\StudentsBFile.dat"));
                HashMap<String, Student> studentHashMap = (HashMap<String, Student>) objInStream.readObject();
                ArrayList<Tournament> joinedTournamentsTournament = new ArrayList<>();

                ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream("src\\StudentsBFile.dat"));

                if (!studentHashMap.containsKey(usernameTextField.getText())){
                    studentHashMap.put(usernameTextField.getText(),new Student(nameTextField.getText(),usernameTextField.getText(),phoneTextField.getText(),emailTextField.getText(), IDTextField.getText(),
                            phoneTextField.getText(), Integer.parseInt(weightTextField.getText()),Integer.parseInt(heightTextField.getText()),joinedTournamentsTournament,0,0,0));
                    objOutStream.writeObject(studentHashMap);

                    Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminHomeScene.fxml")));
                    Scene loginPage = new Scene(fxmlLoader);
                    Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
                    stage.setScene(loginPage);
                    stage.setTitle("Tournament Manager - Home Page");
                    stage.show();
                }
                objInStream.close();
                objOutStream.close();
            }
            catch (IOException  e){
                System.out.println(e.getCause());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean allFilled(){
        boolean allFilled = true;
        nameLable.setTextFill(Paint.valueOf("#386641")); phoneLable.setTextFill(Paint.valueOf("#386641"));
        passwordLable.setTextFill(Paint.valueOf("#386641")); emailLable.setTextFill(Paint.valueOf("#386641"));
        weightLable.setTextFill(Paint.valueOf("#386641")); heightLable.setTextFill(Paint.valueOf("#386641"));usernameLable.setTextFill(Paint.valueOf("#386641"));
        IDLable.setTextFill(Paint.valueOf("#386641"));
        nameTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));
        IDTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));
        emailTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));
        passwordTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));
        phoneTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));
        weightTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));
        heightTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));
        usernameTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));

        if (nameTextField.getText().isEmpty()){
            nameLable.setTextFill(Paint.valueOf("#BC4749"));
            nameTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#BC4749"), BorderStrokeStyle.SOLID,null,null)));
            allFilled = false;
        }
        if (emailTextField.getText().isEmpty()){
            emailLable.setTextFill(Paint.valueOf("#BC4749"));
            emailTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#BC4749"), BorderStrokeStyle.SOLID,null,null)));
            allFilled = false;
        }
        if (passwordTextField.getText().isEmpty()){
            passwordLable.setTextFill(Paint.valueOf("#BC4749"));
            passwordTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#BC4749"), BorderStrokeStyle.SOLID,null,null)));
            allFilled = false;
        }
        if (phoneTextField.getText().isEmpty()){
            phoneLable.setTextFill(Paint.valueOf("#BC4749"));
            phoneTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#BC4749"), BorderStrokeStyle.SOLID,null,null)));
            allFilled = false;
        }
        if (weightTextField.getText().isEmpty() && !isNumeric(heightTextField.getText())){
            weightLable.setTextFill(Paint.valueOf("#BC4749"));
            weightTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#BC4749"), BorderStrokeStyle.SOLID,null,null)));
            allFilled = false;
        }
        if (heightTextField.getText().isEmpty() && !isNumeric(heightTextField.getText())){
            heightLable.setTextFill(Paint.valueOf("#BC4749"));
            heightTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#BC4749"), BorderStrokeStyle.SOLID,null,null)));
            allFilled = false;
        }
        if (IDTextField.getText().isEmpty()){
            IDLable.setTextFill(Paint.valueOf("#BC4749"));
            IDTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#BC4749"), BorderStrokeStyle.SOLID,null,null)));
            allFilled = false;
        }
        if (usernameTextField.getText().isEmpty()){
            usernameLable.setTextFill(Paint.valueOf("#BC4749"));
            usernameTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#BC4749"), BorderStrokeStyle.SOLID,null,null)));
            allFilled = false;
        }
        return allFilled;
    }
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}

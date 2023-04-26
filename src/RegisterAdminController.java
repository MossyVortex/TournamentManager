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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

public class RegisterAdminController {

    @FXML
    private ImageView backButton;

    @FXML
    private Label emailLable;

    @FXML
    private TextField emailTextField;

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
    private Button regesterButton;

    @FXML
    void regesterButtonOnclicked(ActionEvent event) {
        if (allFilled()){

            Person adminObject = new Person(nameTextField.getText());
            adminObject.generateID("Admin");

            String csvFile = "src\\AdminsFile.csv";
            try {
                FileWriter writer = new FileWriter(csvFile, true);
                BufferedWriter bw = new BufferedWriter(writer);

                String name, ID, email, phoneNum, password, tournamentsCreated, isAuthorized;
                name = nameTextField.getText();
                ID = adminObject.getID();
                email = emailTextField.getText();
                phoneNum = phoneTextField.getText();
                password = passwordTextField.getText();
                tournamentsCreated = "0";
                isAuthorized = "true";


                // collect data into a string
                String dataToWrite = name + "," + ID + "," + phoneNum + "," + email + "," + password + "," + tournamentsCreated + "," + isAuthorized;
                
                // Write data rows
                bw.write(dataToWrite);
                bw.newLine();
                
                // Close resources
                bw.close();
                writer.close();
            } 
            catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public boolean allFilled(){
        boolean allFilled = true;
        nameLable.setTextFill(Paint.valueOf("#386641")); phoneLable.setTextFill(Paint.valueOf("#386641"));
        passwordLable.setTextFill(Paint.valueOf("#386641")); emailLable.setTextFill(Paint.valueOf("#386641"));
        nameTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));
        emailTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));
        passwordTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));
        phoneTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));

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
        return allFilled;
    }

    @FXML
    void setBackButton(MouseEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("RegesterScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene loginStudentPage = new Scene(fxmlLoader);
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
        stage.setScene(loginStudentPage);
        stage.setTitle("Tournament Manager - Register");
        stage.show();
    }


}

import java.io.*;
import java.net.URL;
import java.util.HashMap;
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
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class EditAdminProfileController implements Initializable {

    @FXML
    private TextField EmailTextField;

    @FXML
    private Label IDLabel;

    @FXML
    private Label authorizedLabel;

    @FXML
    private TextField authorizedTextField;

    @FXML
    private ImageView backButton;

    @FXML
    private Label usernameLable;

    @FXML
    private ImageView editButton;

    @FXML
    private Label emailLable1;

    @FXML
    private Label nameLable;

    @FXML
    private Label nameLable1;

    @FXML
    private Label nameLable11;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField phoneTextField;

    @FXML
    private Button saveButton;

    @FXML
    private Label tournamentsNumLabel;

    @FXML
    private TextField tournamentsNumTextField;

    @FXML
    void editButtonOnClicked(MouseEvent event) {
        saveButton.setVisible(true); editButton.setVisible(false);
        nameTextField.setEditable(true);EmailTextField.setEditable(true);
        phoneTextField.setEditable(true);passwordTextField.setEditable(true);


    }

    @FXML
    void regesterButtonOnclicked(ActionEvent event) {

    }

    @FXML
    void SaveButtonOnClicked(ActionEvent event) {
        boolean isChanged = false;
        try {
            ObjectInputStream objInStreamLogedinPerson = new ObjectInputStream(new FileInputStream("src\\LogedinPerson.dat"));
            Admin admin = (Admin) objInStreamLogedinPerson.readObject();
            objInStreamLogedinPerson.close();


            String breviousUN = admin.getUserName();

            if (!nameTextField.getText().equals(admin.getName())) {
                admin.setName(nameTextField.getText()); isChanged=true;
            }
            if (!EmailTextField.getText().equals(admin.getEmail())) {
                admin.setEmail(EmailTextField.getText()); isChanged=true;
            }
            if (!passwordTextField.getText().equals(admin.getPassword())) {
                admin.setPassword(passwordTextField.getText()); isChanged=true;
            }
            if (!phoneTextField.getText().equals(admin.getPhoneNumber())) {
                admin.setPhoneNumber(phoneTextField.getText()); isChanged=true;
            }


            if (isChanged){
                ObjectInputStream objInStreamAdminsBFile = new ObjectInputStream(new FileInputStream("src\\AdminsBFile.dat"));
                HashMap<String, Admin> AdminHashMap = (HashMap<String, Admin>) objInStreamAdminsBFile.readObject();
                objInStreamAdminsBFile.close();

                AdminHashMap.remove(breviousUN);
                AdminHashMap.put(admin.getUserName(),admin);

                ObjectOutputStream objOutStreamAdminsBFile = new ObjectOutputStream(new FileOutputStream("src\\AdminsBFile.dat"));
                objOutStreamAdminsBFile.writeObject(AdminHashMap);
                objOutStreamAdminsBFile.close();

                ObjectOutputStream objOutStreamLogedinPerson = new ObjectOutputStream(new FileOutputStream("src\\LogedinPerson.dat"));
                objOutStreamLogedinPerson.writeObject(admin);
                objOutStreamLogedinPerson.close();
            }



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        editButton.setVisible(true); saveButton.setVisible(false);
        nameTextField.setEditable(false);EmailTextField.setEditable(false);
        phoneTextField.setEditable(false);passwordTextField.setEditable(false);
        authorizedTextField.setEditable(false);tournamentsNumTextField.setEditable(false);
    }

    @FXML
    void setBackButton(MouseEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminHomeScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene loginStudentPage = new Scene(fxmlLoader);
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
        stage.setScene(loginStudentPage);
        stage.setTitle("Tournament Manager - Admin Home");
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream("src\\LogedinPerson.dat"));
            Admin admin = (Admin) objInStream.readObject();
            objInStream.close();
            saveButton.setVisible(false);



            IDLabel.setText(admin.getID());
            usernameLable.setText(admin.getUserName());

            nameTextField.setText(admin.getName());
            EmailTextField.setText(admin.getEmail());
            authorizedTextField.setText(String.valueOf(admin.getIsAuthorized()));
            phoneTextField.setText(admin.getPhoneNumber());
            passwordTextField.setText(admin.getPassword());
            tournamentsNumTextField.setText(String.valueOf(admin.getTournamentsCreated()));


            nameTextField.setEditable(false);EmailTextField.setEditable(false);
            phoneTextField.setEditable(false);passwordTextField.setEditable(false);
            authorizedTextField.setEditable(false);tournamentsNumTextField.setEditable(false);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    
    }
}

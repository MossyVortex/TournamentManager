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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
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
     if(allFilled()){
        if(studentRadioButton.isSelected()){
        String path = "src/StudentsFile.csv";
        String line = "";
        String emailFound = "";
        String passwordFound = "";

        try{
            BufferedReader readStudent = new BufferedReader(new FileReader(path));

            while((line = readStudent.readLine()) != null){
                String[] values = line.split(",");
                if(values[3].equals(emailTextF.getText())){
                    emailFound = emailFound + emailTextF.getText();
                }
                if(values[4].equals(passwordTextF.getText())){
                    passwordFound = passwordFound + passwordTextF.getText();
                }
                
            }
            if(! emailFound.equals("") && ! passwordFound.equals("")){
                Parent fxmlLoader = null;
                try {
                    fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StudentHomeScene.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // read the person information from the bin file 
                HashMap<String, ArrayList<String>> myHashMap = new HashMap<>();
                
                try {

                    String readFile;
                    if(adminRadioButton.isSelected()){
                        readFile = "src\\studentInfoBinFile.bin";
                    }
                    else{
                        readFile = "src\\adminInfoBinFile.bin";
                    }

                    // Open the file in binary mode
                    FileInputStream fileInputStream = new FileInputStream(readFile);
        
                    // Create an ObjectInputStream to read objects from the file
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        
                    // Read the object from the file
                    Object obj = objectInputStream.readObject();
        
                    // Close the object input stream and file input stream
                    objectInputStream.close();
                    fileInputStream.close();
        
                    // Cast the object to the desired type (in this case, a HashMap)
                    if (obj instanceof HashMap) {
                        myHashMap = (HashMap<String, ArrayList<String>>) obj;

                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

                // write the info of the logged-in person in a binary file

                try {
                    // Open the file in binary append mode
                    FileOutputStream fileOutputStream = new FileOutputStream("src\\loggedInPerson.bin", true);
        
                    // Create an ObjectOutputStream to write objects to the file
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        
                    // Write the hashmap to the file
                    objectOutputStream.writeObject(myHashMap);
        
                    // Close the object output stream and file output stream
                    objectOutputStream.close();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Scene homePage = new Scene(fxmlLoader);
                Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
                stage.setScene(homePage);
                stage.setTitle("Tournament Manager - Home Page");
                stage.show();
            }else{
                System.out.println("Email or password is/are incorrect");
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }else if(adminRadioButton.isSelected()){
        String path = "src/AdminsFile.csv";
        String line = "";
        String emailFound = "";
        String passwordFound = "";

        BufferedReader AdminRead = new BufferedReader(new FileReader(path));

        while((line = AdminRead.readLine())!= null){
            String[] values = line.split(",");
            if(values[3].equals(emailTextF.getText())){
                emailFound = emailFound + emailTextF.getText();
            }
            if(values[4].equals(passwordTextF.getText())){
                passwordFound = passwordFound + passwordTextF.getText();
            }
        }
        if(! emailFound.equals("") && ! passwordFound.equals("")){
            Parent fxmlLoader = null;
            try {
                fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminHomeScene.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene homePage = new Scene(fxmlLoader);
            Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
            stage.setScene(homePage);
            stage.setTitle("Tournament Manager - Home Page");
            stage.show();
        }else{
            System.out.println("Email or password is/are incorrect");
        }
    }
    } else{
        System.out.println("Fill the missing values");
    }
    }

    




    public boolean allFilled(){
        boolean allFilled = true;
        passwordLable.setTextFill(Paint.valueOf("#386641")); emailLable.setTextFill(Paint.valueOf("#386641"));
        studentRadioButton.setTextFill(Paint.valueOf("#386641"));adminRadioButton.setTextFill(Paint.valueOf("#386641"));
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
        if (!(studentRadioButton.isSelected()||adminRadioButton.isSelected())){
            studentRadioButton.setTextFill(Paint.valueOf("#BC4749"));
            adminRadioButton.setTextFill(Paint.valueOf("#BC4749"));
            allFilled = false;
        }
        return allFilled;
    }
}

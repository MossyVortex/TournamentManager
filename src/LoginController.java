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
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class LoginController {

    @FXML
    private ImageView KFUPMIcon;

    @FXML
    private RadioButton adminRadioButton;

    @FXML
    private Label usernameLable;

    @FXML
    private TextField usernameTextF;

    @FXML
    private TextField passwordText;

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
    void passwordEyeOnPressed(MouseEvent event) {
        passwordText.setText(passwordTextF.getText());
        passwordTextF.setVisible(false);
        passwordText.setVisible(true);
    }

    @FXML
    void passwordEyeReleased(MouseEvent event) {
        passwordTextF.setVisible(true);
        passwordText.setVisible(false);
    }

    @FXML
    void RegExit(MouseEvent event) {
        registerLable.setUnderline(false);
    }
    @FXML
    void RegMoved(MouseEvent event) {
        registerLable.setUnderline(true);
    }


    @FXML
    void loginButtonOnClicked(ActionEvent event) throws IOException, ClassNotFoundException {
        if(allFilled()) {
            if(studentRadioButton.isSelected()){
                if(!inAPIStudent(usernameTextF.getText(), passwordTextF.getText())){
                    try{
                        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src\\LogedinPerson.dat"));
                        ObjectOutputStream outputStreamFrom = new ObjectOutputStream(new FileOutputStream("src\\LogedinFrom.dat"));
                        ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream("src\\StudentsBFile.dat"));
                                 HashMap<String, Student> readStudentInfoMap = (HashMap<String, Student>) objInStream.readObject();
                                 objInStream.close();
                                 if (readStudentInfoMap.containsKey(usernameTextF.getText())){
                                     if (readStudentInfoMap.get(usernameTextF.getText()).getPassword().equals(passwordTextF.getText())) {
                                         outputStream.writeObject(readStudentInfoMap.get(usernameTextF.getText()));
                                         outputStreamFrom.writeObject("Binary");
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
                                     outputStream.close();
                                     outputStreamFrom.close();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }else{
                    Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StudentHomeScene.fxml")));
                    Scene homePage = new Scene(fxmlLoader);
                    Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
                    stage.setScene(homePage);
                    stage.setTitle("Tournament Manager - Home Page");
                    stage.show();
                }
            }else{
                if(!inAPIAdmin(usernameTextF.getText(),passwordTextF.getText())){
                        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminHomeScene.fxml")));
                        Scene homePage = new Scene(fxmlLoader);
                        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
                        stage.setScene(homePage);
                        stage.setTitle("Tournament Manager - Home Page");
                        stage.show();


                }else{
                    Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminHomeScene.fxml")));
                    Scene homePage = new Scene(fxmlLoader);
                    Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
                    stage.setScene(homePage);
                    stage.setTitle("Tournament Manager - Home Page");
                    stage.show();
                }
            }

        }
    }
    private static HttpURLConnection connection;
    
    public boolean logInBinaryFileStudent(String ID, String password) throws FileNotFoundException, IOException, ClassNotFoundException{
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src\\LogedinPerson.dat"));
        ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream("src\\StudentsBFile.dat"));
        HashMap<String, Student> readStudentInfoMap = (HashMap<String, Student>) objInStream.readObject();
        objInStream.close();
        if(readStudentInfoMap.containsKey(ID)){
            if(readStudentInfoMap.get(ID).getPassword().equals(password)){
                outputStream.writeObject(readStudentInfoMap.get(ID));
                outputStream.close();
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    public boolean inAPIStudent(String ID, String password) {
        try {
            String path = "https://us-central1-swe206-221.cloudfunctions.net/app/UserSignIn?username=" + ID + "&password=" + password;
            URL url = new URL(path);

            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if (status <= 299) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                StringBuffer responseContent = new StringBuffer();
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
                String dataFromAPI = responseContent.substring(9);
                String name = "";
                int i = 0;
                while (dataFromAPI.charAt(i) != '"') {
                    name += dataFromAPI.charAt(i);
                    i++;
                }
                Student student = new Student(name, ID, "Abdulmjeed.alothman222@gmail.com", password);
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src\\LogedinPerson.dat"));
                ObjectOutputStream outputStreamFrom = new ObjectOutputStream(new FileOutputStream("src\\LogedinFrom.dat"));
                outputStream.writeObject(student);
                outputStreamFrom.writeObject("API");
                outputStream.close();
                outputStreamFrom.close();
            }

            return status<=299;
        }
        catch (Exception e){
            return false;
        }
    }
    public boolean inAPIAdmin(String ID, String password) {
        try {

            String path = "https://us-central1-swe206-221.cloudfunctions.net/app/UserSignIn?username=" + ID + "&password=" + password;
            URL url = new URL(path);

            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if (status <= 299) {
                Admin admin = new Admin(ID, password);
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src\\LogedinPerson.dat"));
                outputStream.writeObject(admin);
                outputStream.close();
            }
            return status <= 299;

        }
        catch (Exception e){
            return false;
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
        passwordLable.setTextFill(Paint.valueOf("#386641")); usernameLable.setTextFill(Paint.valueOf("#386641"));
        studentRadioButton.setTextFill(Paint.valueOf("#386641"));adminRadioButton.setTextFill(Paint.valueOf("#386641"));
        usernameTextF.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));
        passwordTextF.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));
        if(studentRadioButton.isSelected() || adminRadioButton.isSelected()){
            allFilled = true;
        }else{
            allFilled = false;
        }


        if(usernameTextF.getText().isEmpty()){
            usernameLable.setTextFill(Paint.valueOf("#BC4749"));
            usernameTextF.setBorder(new Border(new BorderStroke(Paint.valueOf("#BC4749"), BorderStrokeStyle.SOLID,null,null)));
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
    @FXML
    void passwordEyeExit(MouseEvent event) {
        passwordEye.setOpacity(1);
    }
    @FXML
    void passwordEyeMoved(MouseEvent event) {
        passwordEye.setOpacity(0.8);
    }
    @FXML
    void AdminRBOnMoved(MouseEvent event) {
        adminRadioButton.setOpacity(0.8);
    }
    @FXML
    void AdminRBOnExit(MouseEvent event) {
        adminRadioButton.setOpacity(1);
    }

    @FXML
    void stdRBOnMoved(MouseEvent event) {
        studentRadioButton.setOpacity(0.8);
    }
    @FXML
    void stdRBOnExit(MouseEvent event) {
        studentRadioButton.setOpacity(1);
    }
    @FXML
    void loginButtonMovedOn(MouseEvent event) {
        loginButton.setOpacity(0.8);
    }
    @FXML
    void loginButtonMouseExit(MouseEvent event) {
        loginButton.setOpacity(1);
    }
}

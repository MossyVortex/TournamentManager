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

public class RegisterStudentController {

    @FXML
    private ImageView backButton;

    @FXML
    private Label IDLable;

    @FXML
    private TextField IDTextField;

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
    private Label weightLable;

    @FXML
    private TextField weightTextField;

    @FXML
    void backButtonOnClicked(MouseEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("RegesterScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene registerStudentPage = new Scene(fxmlLoader);
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
        stage.setScene(registerStudentPage);
        stage.setTitle("Tournament Manager - Register");
        stage.show();
    }

//    @FXML
//    void registerButtonOnClicked(ActionEvent event) {
//
//        if (allFilled()){
//
//            Person studentObject = new Person(nameTextField.getText());
//            studentObject.generateID("Student");
//
//            HashMap<String, ArrayList<Object>> studentInfoMap = new HashMap<>();
//
//
//
//            String csvFile = "src\\StudentsFile.csv";
//
//            boolean registered = false;
//
//            try {
//                FileWriter writer = new FileWriter(csvFile, true);
//                BufferedWriter bw = new BufferedWriter(writer);
//
//                String name, ID, phoneNum, email, password, tournamentsCreated, weight, height, wins, losses, ties;
//                name = nameTextField.getText();
//                ID = studentObject.getID();
//                email = emailTextField.getText();
//                phoneNum = phoneTextField.getText();
//                password = passwordTextField.getText();
//                tournamentsCreated = "0";
//                weight = weightTextField.getText();
//                height = heightTextField.getText();
//                wins = "0";
//                losses = "0";
//                ties = "0";
//
//                // check the email in the binary file
//
//                // check if the email is already available
//
//                String newEmail = email;
//                String line = "";
//                String cvsSplitBy = ",";
//                boolean emailExists = false;
//
//                try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
//                    while ((line = br.readLine()) != null) {
//                        String[] data = line.split(cvsSplitBy);
//                        if (data.length >= 4 && data[3].equals(newEmail)) {
//                            emailExists = true;
//                            break;
//                        }
//                    }
//                }
//                catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                if (emailExists) {
//                    // make the email box red
//
//                    boolean validEmail = true;
//                    emailTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));
//                    emailLable.setTextFill(Paint.valueOf("#BC4749"));
//                    emailTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#BC4749"), BorderStrokeStyle.SOLID,null,null)));
//                    validEmail = false;
//                }
//                else {
//
//                    ArrayList<Object> temp = new ArrayList<>();
//                    studentInfoMap.put(ID, temp);
//                    addStudentInfo(ID, studentInfoMap, name, phoneNum, newEmail, password, tournamentsCreated, weight, height, wins, losses, ties);
//
//                    // collect data into a string
//                    String dataToWrite = name + "," + ID + "," + phoneNum + "," + email + "," + password + "," + tournamentsCreated + "," + weight + "," + height +
//                    "," + wins + "," + losses + "," + ties;
//
//                    // Write data rows
//                    bw.write(dataToWrite);
//                    bw.newLine();
//
//                    // Close resources
//                    bw.close();
//                    writer.close();
//
//                    registered = true;
//                }
//            }
//            catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            // after successful registeration send the user to log in page
//
//            if(registered){
//                Parent fxmlLoader = null;
//                try {
//                    fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginScene.fxml")));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Scene loginPage = new Scene(fxmlLoader);
//                Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
//                stage.setScene(loginPage);
//                stage.setTitle("Tournament Manager - Login");
//                stage.show();
//            }
//        }
//
//    }
    @FXML
    void registerButtonOnClicked(ActionEvent event) {

        if (allFilled()) {
            try {

                FileInputStream fileInputStream = new FileInputStream("src\\StudentsBFile.dat");
                ObjectInputStream objInStream = new ObjectInputStream(fileInputStream);
                ArrayList<Tournament> joinedTournamentsTournament = new ArrayList<>();

                System.out.println("Y");
                HashMap<String, Student> studentHashMap ;
                studentHashMap = (HashMap<String, Student>) objInStream.readObject();
                System.out.println("X");
                FileOutputStream fileOutputStream = new FileOutputStream("src\\StudentsBFile.dat");
                ObjectOutputStream objOutStream = new ObjectOutputStream(fileOutputStream);

                if (!studentHashMap.containsKey(IDTextField.getText())){
                    studentHashMap.put(IDTextField.getText(),new Student(nameTextField.getText(),phoneTextField.getText(),emailTextField.getText(), IDTextField.getText(),
                            phoneTextField.getText(), Integer.parseInt(weightTextField.getText()),Integer.parseInt(heightTextField.getText()),joinedTournamentsTournament,0,0,0));
//                    new FileOutputStream("src\\StudentsBFile.dat").close();
                    objOutStream.writeObject(studentHashMap);

                    Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginScene.fxml")));
                    Scene loginPage = new Scene(fxmlLoader);
                    Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
                    stage.setScene(loginPage);
                    stage.setTitle("Tournament Manager - Login");
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
        weightLable.setTextFill(Paint.valueOf("#386641")); heightLable.setTextFill(Paint.valueOf("#386641"));
        IDLable.setTextFill(Paint.valueOf("#386641"));
        nameTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));
        IDTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));
        emailTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));
        passwordTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));
        phoneTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));
        weightTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));
        heightTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));

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

    public static void addStudentInfo(String studentID, HashMap<String, ArrayList<Object>> studentsInfoMap,
     String name, String phoneNum, String email, String password, String tournamentsCreated, String weight,
      String height, String wins, String losses, String ties ) {            

        ArrayList<Object> studentData = studentsInfoMap.get(studentID);
        studentData.add(name);
        studentData.add(studentID);
        studentData.add(phoneNum);
        studentData.add(email);
        studentData.add(password);
        studentData.add(tournamentsCreated);
        studentData.add(weight);
        studentData.add(height);
        studentData.add(wins);
        studentData.add(losses);
        studentData.add(ties);

        try {
            // Open the file in binary append mode
            FileOutputStream fileOutputStream = new FileOutputStream("src\\studentInfoBinFile.bin", true);

            // Create an ObjectOutputStream to write objects to the file
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            // Write the hashmap to the file
            objectOutputStream.writeObject(studentsInfoMap);

            // Close the object output stream and file output stream
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

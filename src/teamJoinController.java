import classes.Student;
import classes.Tournament;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

public class teamJoinController implements Initializable {

    @FXML
    private ImageView backTeamJoinButton;

    @FXML
    private Button enterButton;

    @FXML
    private Button joinButton;

    @FXML
    private ImageView logoImage;

    @FXML
    private TextField memberUserNameTextField;

    @FXML
    private Label remaningNumLable;

    @FXML
    private Label teamNameLable;

    @FXML
    private TextField teamNameTextField;

    @FXML
    private VBox membersHB;

    private ArrayList<Student> students = new ArrayList<>();

    @FXML
    void enterButtonOnClicked(ActionEvent event) {
        if (!memberUserNameTextField.getText().isEmpty()) {
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src\\TournamentView.dat"));
                Tournament tournament = (Tournament) objectInputStream.readObject();
                objectInputStream.close();

                ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream("src\\StudentsBFile.dat"));
                HashMap<String, Student> studentHashMap = (HashMap<String, Student>) objInStream.readObject();
                objInStream.close();

                if (studentHashMap.containsKey(memberUserNameTextField.getText())) {
                    if (students.size()<tournament.getMembersPerTeam()) {
                        students.add(studentHashMap.get(memberUserNameTextField.getText()));
                        remaningNumLable.setText(String.valueOf(tournament.getMembersPerTeam()-students.size()));
                    }
                    else
                        Alert("Team is Full");
                }



            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else
            memberUserNameTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#BC4749"), BorderStrokeStyle.SOLID,null,null)));


    }

    @FXML
    void joinButtonOnClicked(ActionEvent event) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src\\TournamentView.dat"));
            Tournament tournament = (Tournament) objectInputStream.readObject();
            objectInputStream.close();



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void setTeamBackButton(MouseEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("JoinTournamentScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene registerPage = new Scene(fxmlLoader);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setScene(registerPage);
        stage.setTitle("classes.Tournament Manager - Join Tournament");
        stage.show();
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void Alert(String error){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Input not valid");
        errorAlert.setContentText(error);
        errorAlert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src\\TournamentView.dat"));
            Tournament tournament = (Tournament) objectInputStream.readObject();
            objectInputStream.close();
            remaningNumLable.setText(String.valueOf(tournament.getMembersPerTeam()));


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean inAPIStudent(String username) {
        try {
            String path = "https://us-central1-swe206-221.cloudfunctions.net/app/UserSignIn?username=" + username ;
            URL url = new URL(path);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

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
                Student student = new Student(name);
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src\\LogedinPerson.dat"));
                outputStream.writeObject(student);
                outputStream.close();
            }

            return status<=299;
        }
        catch (Exception e){
            return false;
        }
    }

}

import classes.Student;
import classes.Team;
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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
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

    private static ArrayList<Student> students = new ArrayList<>();

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

                if (students.size()<tournament.getMembersPerTeam()) {
                    if (!alreadyExist(memberUserNameTextField.getText())) {
                        if (inAPIStudent(memberUserNameTextField.getText()))
                            remaningNumLable.setText(String.valueOf(tournament.getMembersPerTeam() - students.size()));

                        else if (studentHashMap.containsKey(memberUserNameTextField.getText()) && students.size() < tournament.getMembersPerTeam()) {
                            students.add(studentHashMap.get(memberUserNameTextField.getText()));
                            remaningNumLable.setText(String.valueOf(tournament.getMembersPerTeam() - students.size()));
                        } else
                            Alert("Student is Unavailable");
                    }
                    else
                        Alert("Student is Already Exist");
                }
                else
                    Alert("Team is Full");

                membersHB.getChildren().clear();
                for (Student student : students) {
                    Label label = new Label(student.getName());
                    label.setTextFill(Paint.valueOf("#386641"));
                    label.setFont(Font.font(20));
                    membersHB.getChildren().add(label);
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

            if (allFilled()){
                Team team = new Team(students,teamNameTextField.getText());
                tournament.addTeam(team);

                ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(new FileOutputStream("src\\TournamentView.dat"));
                objectOutputStream1.writeObject(tournament);
                objectOutputStream1.close();

                ObjectInputStream objInStreamTournament = new ObjectInputStream(new FileInputStream("src\\TournamentsBFile.dat"));
                HashMap<String, Tournament> tournamentHashMap = (HashMap<String,Tournament>) objInStreamTournament.readObject();
                objInStreamTournament.close();

                tournamentHashMap.remove(tournament.getTournamentID());
                tournamentHashMap.put(tournament.getTournamentID(),tournament);

                ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new FileOutputStream("src\\TournamentsBFile.dat"));
                objectOutputStream2.writeObject(tournamentHashMap);
                objectOutputStream2.close();

                for (Student student : students){
                    addTournamentToStudent(student.getUserName(),tournament);
                }

                Parent fxmlLoader = null;
                try {
                    fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StudentHomeScene.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene registerPage = new Scene(fxmlLoader);
                Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                stage.setScene(registerPage);
                stage.setTitle("Tournament Manager - Home Page");
                stage.show();
            }
            else
                Alert("Something is Empty");





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

    public boolean alreadyExist(String username){
        for (Student student : students){
            if(student.getUserName().equals(username))
                return true;
        }
        return false;
    }

    public boolean allFilled(){
        boolean allFilled = true;
        teamNameLable.setTextFill(Paint.valueOf("#386641"));
        teamNameTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));
        memberUserNameTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));


        if (teamNameTextField.getText().isEmpty()){
            teamNameLable.setTextFill(Paint.valueOf("#BC4749"));
            teamNameTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#BC4749"), BorderStrokeStyle.SOLID,null,null)));
            allFilled = false;
        }
        if (students.isEmpty()){
            memberUserNameTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#BC4749"), BorderStrokeStyle.SOLID,null,null)));
            allFilled = false;
        }

        return allFilled;
    }

    public static boolean inAPIStudent(String ID) {
        try {
            String path = "https://us-central1-swe206-221.cloudfunctions.net/app/User?username=" + ID ;

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
                Student student = new Student(name, ID, "Abdulmjeed.alothman222@gmail.com");
                students.add(student);
            }

            return status<=299;
        }
        catch (Exception e){
            return false;
        }
    }

    public void addTournamentToStudent(String username,Tournament tournament){
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src\\StudentsTournaments.dat"));
            HashMap<String,ArrayList<Tournament>> stringArrayListHashMap = (HashMap<String, ArrayList<Tournament>>) objectInputStream.readObject();
            objectInputStream.close();

            if (stringArrayListHashMap.containsKey(username)){
                ArrayList<Tournament> tournaments = stringArrayListHashMap.get(username);
                if (!tournaments.contains(tournament)) {
                    tournaments.add(tournament);
                    stringArrayListHashMap.remove(username);
                    stringArrayListHashMap.put(username, tournaments);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("src\\StudentsTournaments.dat"));
                    objectOutputStream.writeObject(stringArrayListHashMap);
                    objectOutputStream.close();
                }
            }
            else {
                ArrayList<Tournament> tournaments = new ArrayList<>();
                tournaments.add(tournament);
                stringArrayListHashMap.put(username,tournaments);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("src\\StudentsTournaments.dat"));
                objectOutputStream.writeObject(stringArrayListHashMap);
                objectOutputStream.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void joinButtonExit(MouseEvent event) {
        joinButton.setOpacity(1);
    }
    @FXML
    void joinButtonMoved(MouseEvent event) {
        joinButton.setOpacity(0.8);
    }
    @FXML
    void backTeamJoinButtonExit(MouseEvent event) {
        backTeamJoinButton.setOpacity(1);
    }

    @FXML
    void backTeamJoinButtonMoved(MouseEvent event) {
        backTeamJoinButton.setOpacity(0.8);
    }

    @FXML
    void enterButtonExit(MouseEvent event) {
        enterButton.setOpacity(1);
    }

    @FXML
    void enterButtonMoved(MouseEvent event) {
        enterButton.setOpacity(0.8);
    }
}

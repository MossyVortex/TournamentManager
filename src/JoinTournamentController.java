import classes.Student;
import classes.Team;
import classes.Tournament;
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

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class JoinTournamentController {

    @FXML
    private ImageView backTIDButton;

    @FXML
    private RadioButton individualRB;

    @FXML
    private Button joinButton;

    @FXML
    private RadioButton teamRB;

    @FXML
    private TextField tournamentIDTextField;

    @FXML
    private ImageView backTeamJoinButton;

    @FXML
    private Button enterButton;

    @FXML
    private TextField memberIDTextField;

    @FXML
    private Label remaningNumLable;

    @FXML
    void enterButtonOnClicked(ActionEvent event) {

    }

    @FXML
    void setTeamBackButton(MouseEvent event) {

    }

    @FXML
    void individualRBOn(ActionEvent event) {
        teamRB.setSelected(false);
    }

    @FXML
    void joinButtonOnClicked(ActionEvent event) {
        if (allFilled()){
            try {
                ObjectInputStream objInStreamTournament = new ObjectInputStream(new FileInputStream("src\\TournamentsBFile.dat"));
                HashMap<String, Tournament> tournamentHashMap = (HashMap<String,Tournament>) objInStreamTournament.readObject();
                objInStreamTournament.close();
                ObjectInputStream objectInStreamSt = new ObjectInputStream(new FileInputStream("src\\LogedinPerson.dat"));
                Student student = (Student) objectInStreamSt.readObject();
                objectInStreamSt.close();
                if (tournamentHashMap.containsKey(tournamentIDTextField.getText())){
                    Tournament tournament = tournamentHashMap.get(tournamentIDTextField.getText());
                    if (individualRB.isSelected()){
                        if (tournament.areAllTeamsFilled()){
                            if (tournament.isFullOfTeams() || !tournament.getRegisterationStatus()){
                                Alert("Unable to Join This Tournament");
                            }
                            else {
                                ArrayList<Student> studentArrayList = new ArrayList<>();
                                studentArrayList.add(student);
                                Team team = new Team(studentArrayList, "Team"+tournament.getTeams().size()+1);
                                tournament.addTeam(team);
                            }
                        }
                        else {
                            for (Team team : tournament.getTeams()){
                                if (team.getTeamMembers().size() < tournament.getMembersPerTeam()){
                                    tournament.addStudentInTeam(student,team);
                                    break;
                                }
                            }
                        }
                        tournamentHashMap.remove(tournament.getTournamentID());
                        tournamentHashMap.put(tournament.getTournamentID(),tournament);
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("src\\TournamentsBFile.dat"));
                        objectOutputStream.writeObject(tournamentHashMap);
                        objectOutputStream.close();

                        addTournamentToStudent(student.getUserName(),tournament);

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
                    else{
                        if (!tournament.isFullOfTeams()) {
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("src\\TournamentView.dat"));
                            objectOutputStream.writeObject(tournament);
                            objectOutputStream.close();
                            Parent fxmlLoader = null;
                            try {
                                fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("teamJoinScene.fxml")));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Scene registerAdminPage = new Scene(fxmlLoader);
                            Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
                            stage.setScene(registerAdminPage);
                            stage.setTitle("Tournament Manager - Team Join");
                            stage.show();
                        }
                        else {
                            Alert("Full of Teams");
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean allFilled(){
        boolean allFilled = true;
        teamRB.setTextFill(Paint.valueOf("#386641"));individualRB.setTextFill(Paint.valueOf("#386641"));
        tournamentIDTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));
        if(teamRB.isSelected() || individualRB.isSelected()){
            allFilled = true;
        }else{
            allFilled = false;
        }
        if(tournamentIDTextField.getText().isEmpty()){
            tournamentIDTextField.setBorder(new Border(new BorderStroke(Paint.valueOf("#BC4749"), BorderStrokeStyle.SOLID,null,null)));
            allFilled = false;
        }
        if (!(teamRB.isSelected()||individualRB.isSelected())){
            individualRB.setTextFill(Paint.valueOf("#BC4749"));
            teamRB.setTextFill(Paint.valueOf("#BC4749"));
            allFilled = false;
        }
        return allFilled;
    }


    @FXML
    void setTIDBackButton(MouseEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StudentHomeScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene homePage = new Scene(fxmlLoader);
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
        stage.setScene(homePage);
        stage.setTitle("Tournament Manager - Home Page");
        stage.show();
    }

    @FXML
    void teamRBOn(ActionEvent event) {
        individualRB.setSelected(false);
    }

    public void ViweButtonOnClicked1(ActionEvent actionEvent) {
    }

    public void Alert(String error){
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Input not valid");
        errorAlert.setContentText(error);
        errorAlert.showAndWait();
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

}

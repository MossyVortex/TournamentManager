import classes.Student;
import classes.Team;
import classes.Tournament;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
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
                        ArrayList<Team> teams = tournament.getTeams();
                        for (int i = 0; i<teams.size(); i++){
                            Team team = teams.get(i);
                            ArrayList<Student> studentArrayList = team.getTeamMembers();
                            if (studentArrayList.size() < tournament.getMembersPerTeam()){
                                studentArrayList.add(student);
                                team.setTeamMembers(studentArrayList);
                                break;
                                /* Note Now update the team in "ArrayList<Team> teams"
                                    Then in "Tournament tournament"
                                 */
                            }
                        }
                        if (teams.size() < tournament.getNumOfTeams()){
                            ArrayList<Student> arrayList = new ArrayList<>();
                            arrayList.add(student);
                            Team team = new Team(arrayList);
                            tournament.addTeam(team);
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
}

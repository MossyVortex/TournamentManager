import classes.Elimination;
import classes.Person;
import classes.RoundRobin;
import classes.Student;
import classes.Team;
import classes.Tournament;
import javafx.ElimnationTournementPage;
import javafx.RoundRobinTournementPage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

import static javafx.RoundRobinTournementPage.createTourneyPage;
import static javafx.ElimnationTournementPage.createTourneyPage;

public class GeneratedTablesController implements Initializable {

    @FXML
    private Label IDLabel;

    @FXML
    private Label WinsLabel;

    @FXML
    private Label WinsLabel1;

    @FXML
    private Label WinsLabel2;

    @FXML
    private Label WinsLabel21;

    @FXML
    private ImageView backButton;

    @FXML
    private TextField gameTextField;

    @FXML
    private HBox infoPane;

    @FXML
    private TextField stdNumTextField;

    @FXML
    private HBox studentsPane;

    @FXML
    private TextField teamsNumTextField;

    @FXML
    private HBox teamsPane;

    @FXML
    private TextField typeTextField;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    void infoPaneOnCklicked(MouseEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ViewTournamentScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene registerPage = new Scene(fxmlLoader);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setScene(registerPage);
        stage.setTitle("Tournament Manager - View Tournament");
        stage.show();
    }

    @FXML
    void setBackButton(MouseEvent event) {
        Parent fxmlLoader = null;
        try {
            ObjectInputStream objInStreamLogedinPerson = new ObjectInputStream(new FileInputStream("src\\LogedinPerson.dat"));
            Person person = (Person) objInStreamLogedinPerson.readObject();
            objInStreamLogedinPerson.close();
            if (person instanceof Student)
                fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StudentHomeScene.fxml")));
            else
                fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminHomeScene.fxml")));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Scene registerPage = new Scene(fxmlLoader);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setScene(registerPage);
        stage.setTitle("Tournament Manager - Home Page");
        stage.show();
    }

    @FXML
    void studentsPaneOnClicked(MouseEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StudentsViewScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene registerStudentPage = new Scene(fxmlLoader);
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
        stage.setScene(registerStudentPage);
        stage.setTitle("Tournament Manager - Students View");
        stage.show();
    }

    @FXML
    void teamsPaneOnCklicked(MouseEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TeamsViewScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene registerStudentPage = new Scene(fxmlLoader);
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
        stage.setScene(registerStudentPage);
        stage.setTitle("Tournament Manager - Teams View");
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
//            if(tournamentType == "roundRobin"){
//                tourney.createMatchHistory(); // do this 1 time only
//                tourney.createTables(); //  do this one time only
//                ScrollPane mainTourney = createTourneyPage(tourney);
//            }else if(tournmaneType == "elimnation"){
//                tourney.createMatchHistory(); // do this one time only
//                VBox mainTourney = createTourneyPage(tourney);}


            ObjectInputStream objInStreamTournament = new ObjectInputStream(new FileInputStream("src\\TournamentView.dat"));
            Tournament tournament = (Tournament) objInStreamTournament.readObject();
            IDLabel.setText(tournament.getTournamentID());
            typeTextField.setText(tournament.getType());
            gameTextField.setText(tournament.getGameType());
            teamsNumTextField.setText(String.valueOf(tournament.getNumOfTeams()));
            stdNumTextField.setText(String.valueOf(tournament.getMembersPerTeam()));

            typeTextField.setEditable(false);
            gameTextField.setEditable(false);
            teamsNumTextField.setEditable(false);
            stdNumTextField.setEditable(false);

            objInStreamTournament.close();


            // code for the elimination and roundrobin genereted tables

            try{
                ObjectInputStream objInStreamTournament2 = new ObjectInputStream(new FileInputStream("src\\TournamentsBFile.dat"));
                    HashMap<String, Tournament> tournamentHashMap = (HashMap<String,Tournament>) objInStreamTournament2.readObject();
                    objInStreamTournament2.close();
                    ObjectInputStream objectInStreamSt = new ObjectInputStream(new FileInputStream("src\\LogedinPerson.dat"));
                    Admin admin = (Admin) objectInStreamSt.readObject();
                    objectInStreamSt.close();
    
                    ObjectInputStream getTournamentIDStream = new ObjectInputStream(new FileInputStream("src\\TournamentView.dat"));
                    Tournament viewedTournament = (Tournament) getTournamentIDStream.readObject();
                    getTournamentIDStream.close();
    
                    if(viewedTournament.getType().equals("Elimination")){
                        Team noOne = new Team();
                        Elimination convertedToElim = new Elimination(viewedTournament.getName(),viewedTournament.getGameType(),viewedTournament.getType(),viewedTournament.getTournamentID(),noOne,viewedTournament.getStartingDate(),viewedTournament.getEndingDate(),viewedTournament.getTeams(),viewedTournament.getNumOfTeams(),viewedTournament.getStudents(),viewedTournament.getMembersPerTeam(),viewedTournament.getRegisterationStatus(),viewedTournament.getIsGenerated());
                        convertedToElim.createMatchHistory();
                        ScrollPane elimScrollpane = ElimnationTournementPage.createTourneyPage(convertedToElim);
                        scrollPane.setContent(elimScrollpane);
                        
                    }
                    else{
                        RoundRobin convertedToRoundRobin = new RoundRobin(viewedTournament.getName(),viewedTournament.getGameType(),viewedTournament.getType(),viewedTournament.getTournamentID(),viewedTournament.getWinner(),viewedTournament.getStartingDate(),viewedTournament.getEndingDate(),viewedTournament.getTeams(),viewedTournament.getNumOfTeams(),viewedTournament.getStudents(),viewedTournament.getMembersPerTeam(),viewedTournament.getRegisterationStatus(),viewedTournament.getIsGenerated());
                        convertedToRoundRobin.createMatchHistory();
                        convertedToRoundRobin.createTables();
                        ScrollPane roundScrollpane = RoundRobinTournementPage.createTourneyPage(convertedToRoundRobin);
                        scrollPane.setContent(roundScrollpane);
    
    
                    }
    
            }
            catch(Exception e){
                e.printStackTrace();
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

}

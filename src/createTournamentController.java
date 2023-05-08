import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import javafx.scene.Node;
import classes.Elimination;
import classes.RoundRobin;
import classes.Student;
import classes.Team;
import classes.Tournament;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
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
import classes.Person;
import java.io.*;


public class createTournamentController {

    @FXML
    private Label chooseTournamentTypeLabel;

    @FXML
    private Button createTournamentButton;

    @FXML
    private Label createTournamentLabel;

    @FXML
    private ImageView createTournamentLogo;

    @FXML
    private RadioButton eliminationRadioButton;

    @FXML
    private Label endDateLabel;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TextField gameTypeTextField;

    @FXML
    private TextField maxNumOfTeamsTextField;

    @FXML
    private RadioButton roundRobinRadioButton;

    @FXML
    private Label startDateLabel;


    @FXML
    private ImageView backButton;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private Label tournamentNameLabel;

    @FXML
    private Label membersPerTeamLabel;

    @FXML
    private TextField membersPerTeamTextField;

    @FXML
    private TextField tournamentNameTextField;

    private LocalDate pickedStartDate, pickedEndDate;

    @FXML
    void createTournamentOnClicked(ActionEvent event) {
        // choose type
        if(eliminationRadioButton.isDisabled() && roundRobinRadioButton.isDisabled()){

            // this is when the user did not choose any of the options

            // this does not work
            roundRobinRadioButton.setTextFill(Paint.valueOf("#386641"));
            roundRobinRadioButton.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));
            eliminationRadioButton.setTextFill(Paint.valueOf("#386641"));
            eliminationRadioButton.setBorder(new Border(new BorderStroke(Paint.valueOf("#386641"), BorderStrokeStyle.SOLID,null,null)));
        }
        else if(eliminationRadioButton.isSelected()){

            // create Elimination tournament

            if (allFilled()){
                String tempTournamentID = Tournament.generateID();

                HashMap<String, ArrayList<Object>> tournamentsInfoMap = new HashMap<>();

                String name, tournamenType, gameType, tournamentID, bannedStudentsIDs, numOfTeams, students;
                Team winner;
                boolean registerationStatus;
                LocalDate startDate, endDate;
                ArrayList<Team> teams;
                int membersPerTeam;

                name = tournamentNameTextField.getText();
                tournamenType = "Elimination";
                gameType = gameTypeTextField.getText();
                tournamentID = tempTournamentID;
                winner = null;
                startDate = pickedStartDate;
                endDate = pickedEndDate;
                bannedStudentsIDs = "";
                numOfTeams = maxNumOfTeamsTextField.getText();
                students = "";
                ArrayList<Team> teamArr = new ArrayList<>();
                teams = teamArr;
                registerationStatus = true;
                membersPerTeam = Integer.parseInt(membersPerTeamTextField.getText());

                ArrayList<Object> temp = new ArrayList<>();
                tournamentsInfoMap.put(tournamentID, temp);
                addTournamentInfo(tournamentsInfoMap,name, tournamenType, gameType, tournamentID, winner, startDate, endDate,
                 bannedStudentsIDs, numOfTeams, students,teams ,registerationStatus, membersPerTeam);

                Parent fxmlLoader = null;
                try {
                    fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminHomeScene.fxml")));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene loginPage = new Scene(fxmlLoader);
                Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
                stage.setScene(loginPage);
                stage.setTitle("Tournament Manager - Login");
                stage.show();


            }

        }
        else if(roundRobinRadioButton.isSelected()){

            // create roundrobin tournament
            if(allFilled()){
                    String tempTournamentID = Tournament.generateID();

                    HashMap<String, ArrayList<Object>> tournamentsInfoMap = new HashMap<>();

                    String name, gameType, tournamentID, bannedStudentsIDs, numOfTeams, students, tournamentType;
                    Team winner;
                    boolean registerationStatus;
                    LocalDate startDate, endDate;
                    ArrayList<Team> teams;
                    int membersPerTeam;

                    name = tournamentNameTextField.getText();
                    gameType = gameTypeTextField.getText();
                    tournamentID = tempTournamentID;
                    tournamentType = "RoundRobin";
                    winner = null;
                    startDate = pickedStartDate;
                    endDate = pickedEndDate;
                    bannedStudentsIDs = "";
                    numOfTeams = maxNumOfTeamsTextField.getText();
                    students = "";
                    registerationStatus = true;
                    ArrayList<Team> teamArr = new ArrayList<>();
                    teams = teamArr;
                    membersPerTeam = Integer.parseInt(membersPerTeamTextField.getText());

                    ArrayList<Object> temp = new ArrayList<>();
                    tournamentsInfoMap.put(tournamentID, temp);
                    addTournamentInfo(tournamentsInfoMap, name, tournamentType, gameType, tournamentID, winner, startDate, endDate,
                    bannedStudentsIDs, numOfTeams, students, teams, registerationStatus, membersPerTeam);

                    Parent fxmlLoader = null;
                    try {
                        fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminHomeScene.fxml")));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene loginPage = new Scene(fxmlLoader);
                    Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
                    stage.setScene(loginPage);
                    stage.setTitle("Tournament Manager - Login");
                    stage.show();
            }
        }
        else{
            // this is when somehow the user chose both options

            // make the radio buttons red
        }
    }

    @FXML
    void eliminationRadioButtonOnClicked(ActionEvent event) {
        roundRobinRadioButton.setSelected(false);

    }

    @FXML
    void getEndDate(ActionEvent event) {

        LocalDate endDateVariable = endDatePicker.getValue();
        this.pickedEndDate = endDateVariable;

    }

    @FXML
    void getStartDate(ActionEvent event) {

        LocalDate startDateVariable = startDatePicker.getValue(); 
        this.pickedStartDate = startDateVariable;

    }

    @FXML
    void roundRobinRadioButtonOnClicked(ActionEvent event) {
        eliminationRadioButton.setSelected(false);

    }

    @FXML
    void backButtonOnClicked(MouseEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminHomeScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene loginPage = new Scene(fxmlLoader);
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
        stage.setScene(loginPage);
        stage.setTitle("Tournament Manager - Login");
        stage.show();

    }

    public boolean allFilled(){
        return true; // placeHolder
    }

    public static void addTournamentInfo(HashMap<String, ArrayList<Object>> tournamentInfoMap ,String name, String tournamentType,String gameType, String tournamentID, Team winner, LocalDate startDate,
    LocalDate endDate, String bannedStudentsIDs, String maxNumOfTeams, String students, ArrayList<Team> teams, boolean registerationStatus, int membersPerTeam){

        ArrayList<Object> tournamentData = tournamentInfoMap.get(tournamentID);

        tournamentData.add(name);
        tournamentData.add(tournamentID);
        tournamentData.add(tournamentType);
        tournamentData.add(bannedStudentsIDs);
        tournamentData.add(students);
        tournamentData.add(maxNumOfTeams);
        tournamentData.add(gameType);
        tournamentData.add(winner);
        tournamentData.add(startDate);
        tournamentData.add(endDate);
        tournamentData.add(teams);
        tournamentData.add(registerationStatus);

        
        try {

            FileInputStream fileInputStream = new FileInputStream("src\\TournamentsBFile.dat");
            ObjectInputStream objInStream = new ObjectInputStream(fileInputStream);

            HashMap<String, Tournament> tournamentsInfoHashMap = (HashMap<String, Tournament>) objInStream.readObject();

            if(tournamentType.equals("Elimination")){
                FileOutputStream fileOutputStream = new FileOutputStream("src\\TournamentsBFile.dat");
                ObjectOutputStream objOutStream = new ObjectOutputStream(fileOutputStream);
                if (!tournamentsInfoHashMap.containsKey(tournamentID)){
                    tournamentsInfoHashMap.put(tournamentID, new Elimination(name, gameType,tournamentType, tournamentID, winner, startDate, endDate, teams, 0, null, membersPerTeam ,registerationStatus )); // the zero before the reg status is a placeholder we need a textfield!
                    objOutStream.writeObject(tournamentsInfoHashMap);
                    
                }
                objInStream.close();
                objOutStream.close();
            }
            else{
                FileOutputStream fileOutputStream = new FileOutputStream("src\\TournamentsBFile.dat");
                ObjectOutputStream objOutStream = new ObjectOutputStream(fileOutputStream);
                if (!tournamentsInfoHashMap.containsKey(tournamentID)){
                    tournamentsInfoHashMap.put(tournamentID, new RoundRobin(name, gameType,tournamentType, tournamentID, winner, startDate, endDate, teams, 0, null,0 ,registerationStatus));
                    objOutStream.writeObject(tournamentsInfoHashMap);
                }    
                objInStream.close();
                objOutStream.close();
            }


        }
        catch (IOException  e){
            System.out.println(e.getCause());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        
    }

}

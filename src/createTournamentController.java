import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import classes.Elimination;
import classes.RoundRobin;
import classes.Team;
import classes.Tournament;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

                Elimination elimTObj = new Elimination();
                String tempTournamentID = Tournament.generateID();

                HashMap<String, ArrayList<Object>> tournamentsInfoMap = new HashMap<>();

                String name, tournamenType, gameType, tournamentID, winner, bannedStudentsIDs, numOfTeams, students; 
                boolean registerationStatus;
                LocalDate startDate, endDate;
                ArrayList<Team> teams;

                name = tournamentNameTextField.getText();
                tournamenType = "Elimination";
                gameType = gameTypeTextField.getText();
                tournamentID = tempTournamentID;
                winner = "no winner";
                startDate = pickedStartDate;
                endDate = pickedEndDate;
                bannedStudentsIDs = "";
                numOfTeams = maxNumOfTeamsTextField.getText();
                students = "";
                ArrayList<Team> teamArr = new ArrayList<>();
                teams = teamArr;
                registerationStatus = true;

                ArrayList<Object> temp = new ArrayList<>();
                tournamentsInfoMap.put(tournamentID, temp);
                addTournamentInfo(tournamentsInfoMap,name, tournamenType, gameType, tournamentID, winner, startDate, endDate,
                 bannedStudentsIDs, numOfTeams, students,teams ,registerationStatus);

            }

        }
        else if(roundRobinRadioButton.isSelected()){

            // create roundrobin tournament
            if(allFilled()){
                RoundRobin roundTObj = new RoundRobin();
                    String tempTournamentID = Tournament.generateID();

                    HashMap<String, ArrayList<Object>> tournamentsInfoMap = new HashMap<>();

                    String name, gameType, tournamentID, winner, bannedStudentsIDs, numOfTeams, students, tournamentType; 
                    boolean registerationStatus;
                    LocalDate startDate, endDate;
                    ArrayList<Team> teams;

                    name = tournamentNameTextField.getText();
                    gameType = gameTypeTextField.getText();
                    tournamentID = tempTournamentID;
                    tournamentType = "RoundRobin";
                    winner = "no winner";
                    startDate = pickedStartDate;
                    endDate = pickedEndDate;
                    bannedStudentsIDs = "";
                    numOfTeams = maxNumOfTeamsTextField.getText();
                    students = "";
                    registerationStatus = true;
                    ArrayList<Team> teamArr = new ArrayList<>();
                    teams = teamArr;

                    ArrayList<Object> temp = new ArrayList<>();
                    tournamentsInfoMap.put(tournamentID, temp);
                    addTournamentInfo(tournamentsInfoMap, name, tournamentType, gameType, tournamentID, winner, startDate, endDate,
                    bannedStudentsIDs, numOfTeams, students, teams,registerationStatus);
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

    public static void addTournamentInfo(HashMap<String, ArrayList<Object>> tournamentInfoMap ,String name, String tournamentType,String gameType, String tournamentID, String winner, LocalDate startDate,
    LocalDate endDate, String bannedStudentsIDs, String maxNumOfTeams, String students, ArrayList<Team> teams, boolean registerationStatus){

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
            // Open the file in binary append mode
            FileOutputStream fileOutputStream = new FileOutputStream("src\\tournamentInfoBinFile.bin", true);

            // Create an ObjectOutputStream to write objects to the file
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            // Write the hashmap to the file
            objectOutputStream.writeObject(tournamentInfoMap);

            // Close the object output stream and file output stream
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        
    }

}

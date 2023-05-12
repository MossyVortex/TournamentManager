package javafx;

import classes.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;

import static javafx.application.Application.launch;

public class RoundRobinTournementPage extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        RoundRobin tourney = new RoundRobin();
        tourney.setStartingDate(LocalDate.of(2023,5,5));
        tourney.setEndingDate(LocalDate.of(2023,5,25));
        tourney.setName("tour");
        ArrayList<Student> stu = new ArrayList<>();
        stu.add(new Student("mads"));
        stu.add(new Student("sads"));
        stu.add(new Student("aads"));
        Team t1 = new Team(stu,"ssd");
        addTeams(tourney, t1 , stu);


        tourney.createMatchHistory();
        tourney.createTables();
        ScrollPane mainTourney = createTourneyPage(tourney);

        Scene scene = new Scene(mainTourney,1000,800);



        stage.setScene(scene);
        stage.show();
    }
    public static ScrollPane createArchivedTourneyPage(RoundRobin tourney){
        VBox roundRobinVBox = new VBox(createArchivedTourney(tourney));
        HBox pointsTable = createPointsTable(tourney);
        HBox realPlacmentTable = makeTruePlacement(tourney);
        Button returnButton = new Button("return to previous page");

//        Button returnToTournementPage = new Button("return to tournement page");

        HBox buttonsBox = new HBox(returnButton);
        VBox mainTourney = new VBox(buttonsBox, roundRobinVBox, pointsTable, realPlacmentTable);

        ScrollPane tourneyPage = new ScrollPane(mainTourney);
        return tourneyPage;
    }
    public static ScrollPane createTourneyPage(RoundRobin tourney){
        ArrayList<TextField> textFields = new ArrayList<>();
        VBox roundRobinVBox = new VBox(createTourney(tourney,textFields));
        HBox pointsTable = createPointsTable(tourney);
        HBox realPlacmentTable = makeTruePlacement(tourney);
//        Button calculateWinnerButton = new Button();

        Button updatePointsButton = new Button();

//        Button returnToTournementPage = new Button("return to tournement page");

        HBox buttonsBox = new HBox(updatePointsButton);
//        calculateWinnerButton.setText("calcuate winners ");
        updatePointsButton.setText("updatePoints");
        VBox mainTourney = new VBox(buttonsBox, roundRobinVBox, pointsTable, realPlacmentTable);

        ScrollPane tourneyPage = new ScrollPane(mainTourney);
        updatePointsButton.setOnAction(e ->{



            updateMatches(tourney,textFields);
            tourney.updateTables();
            tourney.getPlacement();

//            System.out.print("goal differnce : ");tourney.printGoalDiffernce();
//            System.out.print("points table beautifed : ");tourney.printPointsTableBeautifed();
//            tourney.printMatchHistoryBeautified();
            VBox updatedRoundRobin = new VBox(createTourney(tourney,textFields));
            HBox updatedPointsTable = createPointsTable(tourney);
            HBox updatedPlacmentTable = makeTruePlacement(tourney);

            mainTourney.getChildren().remove(1);
            mainTourney.getChildren().remove(1);
            mainTourney.getChildren().remove(1);
            mainTourney.getChildren().add(updatedRoundRobin);
            mainTourney.getChildren().add(updatedPointsTable);
            mainTourney.getChildren().add(updatedPlacmentTable);
//            System.out.println(tourney.printPointsTable());
            try {
                Tournament tournamentToWrite = tourney;
                ObjectInputStream objInStreamTournament = new ObjectInputStream(new FileInputStream("src\\TournamentsBFile.dat"));
                HashMap<String, Tournament> tournamentHashMap = (HashMap<String, Tournament>) objInStreamTournament.readObject();
                objInStreamTournament.close();

                tournamentHashMap.remove(tournamentToWrite.getTournamentID());
                tournamentHashMap.put(tournamentToWrite.getTournamentID(), tournamentToWrite);

                ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new FileOutputStream("src\\TournamentsBFile.dat"));
                objectOutputStream2.writeObject(tournamentHashMap);
                objectOutputStream2.close();

                ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(new FileOutputStream("src\\TournamentView.dat"));
                objectOutputStream1.writeObject(tournamentToWrite);
                objectOutputStream1.close();

            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }

        });
        return tourneyPage;

    }
    public static VBox createArchivedMatchUp(Match match){
        VBox matchup = new VBox();

        Label team1Label = new Label(match.getTeamOneName() + " ");
        team1Label.setMaxWidth(100);
        team1Label.setMinWidth(100);

        Text team1Score = new Text(match.getScoreOne() +"");
        HBox team1HBox = new HBox();
        team1HBox.getChildren().addAll(team1Label,team1Score);
        team1HBox.setSpacing(10);

        Label team2Label = new Label(match.getTeamTwoName() + "");
        team2Label.setMaxWidth(100);
        team2Label.setMinWidth(100);
        Text team2Score = new Text(match.getScoreTwo() +"");
        HBox team2HBox = new HBox();
        team2HBox.getChildren().addAll(team2Label,team2Score );
        team2HBox.setSpacing(10);
        matchup.getChildren().addAll(team1HBox, team2HBox);
        Text dateText = new Text(match.getDate());
        matchup.getChildren().add(dateText);
        return matchup;
        
    }
    public static VBox createArchivedRound(ArrayList<Match> matches, int roundIndex ){
        VBox round = new VBox(new Text("round" + roundIndex));
        round.setSpacing(20);
        for(int i = 0 ; i < matches.size() ; i++){
            round.getChildren().add(createArchivedMatchUp(matches.get(i)));
        }
        return round;
    }
    public static VBox createArchivedTourney(RoundRobin tourney){
        HBox tourneyHbox = new HBox();
        tourneyHbox.setSpacing(20);
        Hashtable<Integer, ArrayList<Match>> matchHistory = tourney.printMatchHistory();
        for(int i = 0 ; i < matchHistory.size() ; i++){
            tourneyHbox.getChildren().add(createArchivedRound(matchHistory.get(i), i));
        }
        VBox tourneyVbox = new VBox(new Text("tournement"));
        tourneyVbox.getChildren().add(tourneyHbox);
        return tourneyVbox;
    }


    public static VBox createMatchUP(Match match , int roundIndex , int matchIndex , ArrayList<TextField> textFields){
        VBox matchup = new VBox();

        Label team1Label = new Label(match.getTeamOneName() + " ");
        team1Label.setMaxWidth(100);
        team1Label.setMinWidth(100);

        Text team1Score = new Text(match.getScoreOne() +"");
        TextField team1TextField = new TextField();
        team1TextField.setMaxWidth(40);
        team1TextField.setId(roundIndex + "-" + matchIndex + "-" + 0);
        HBox team1HBox = new HBox();
        team1HBox.getChildren().addAll(team1Label,team1Score,team1TextField);
        team1HBox.setSpacing(10);

        Label team2Label = new Label(match.getTeamTwoName() + "");
        team2Label.setMaxWidth(100);
        team2Label.setMinWidth(100);
        Text team2Score = new Text(match.getScoreTwo() +"");
        TextField team2TextField = new TextField();
        team2TextField.setMaxWidth(40);

        team2TextField.setId(roundIndex + "-" + matchIndex + "-" + 1 );
        HBox team2HBox = new HBox();
        team2HBox.getChildren().addAll(team2Label,team2Score ,team2TextField);
        team2HBox.setSpacing(10);

        textFields.add(team1TextField);
        textFields.add(team2TextField);
        matchup.getChildren().addAll(team1HBox, team2HBox);
        if(match.getTeamOne().getTeamName().equals("Bye") || match.getTeamTwo().getTeamName().equals("Bye")){

            team1TextField.setStyle("-fx-background-color: black");
            team1TextField.setEditable(false);
            team2TextField.setEditable(false);
            team2TextField.setStyle("-fx-background-color: black");
        }
        Text dateText = new Text(match.getDate());
        if(match.getLocalDate().isBefore(LocalDate.now()) && (match.getScoreOne() == -1 || match.getScoreTwo() == -1))
            matchup.setStyle("-fx-background-color: red");
        matchup.getChildren().add(dateText);
        return matchup;
    }
    public static VBox createRound(ArrayList<Match> matches, int roundIndex , ArrayList<TextField> textFields  ){
        VBox round = new VBox(new Text("round" + roundIndex));
        round.setSpacing(20);
        for(int i = 0 ; i < matches.size() ; i++){
            round.getChildren().add(createMatchUP(matches.get(i),roundIndex , i , textFields));
        }
        return round;
    }
    public static VBox createTourney(RoundRobin tourney, ArrayList<TextField> textFields){
        textFields.clear();
        HBox tourneyHbox = new HBox();
        tourneyHbox.setSpacing(20);
        Hashtable<Integer, ArrayList<Match>> matchHistory = tourney.printMatchHistory();
        for(int i = 0 ; i < matchHistory.size() ; i++){
            tourneyHbox.getChildren().add(createRound(matchHistory.get(i) , i , textFields));
        }
        VBox tourneyVbox = new VBox(new Text("tournement"));
        tourneyVbox.getChildren().add(tourneyHbox);
        return tourneyVbox;
    }
    public static VBox createTeamPoints(String teamName , int points){
        Text teamNameText = new Text(teamName);
        Text pointsText = new Text(points +"");
        VBox teamPoints = new VBox(teamNameText,pointsText);
        return teamPoints;
    }
    public static HBox createPointsTable(RoundRobin tour){
        HBox pointsTable = new HBox();
        pointsTable.setSpacing(20);
        LinkedHashMap<Team, Integer> pointsHashTable = tour.printPointsTable();
        pointsHashTable.forEach((key , value) ->{
            pointsTable.getChildren().add(createTeamPoints(key.getTeamName(),value));
        });
        return pointsTable;
    }
    public static void addTeams(RoundRobin tour, Team team , ArrayList<Student> stu){

        tour.addTeam(new Team(stu,"team1"));
        tour.addTeam(new Team(stu,"team2"));
        tour.addTeam(new Team(stu,"team3"));
        tour.addTeam(new Team(stu,"team4"));
//        tour.addTeam(new Team(stu,"team5"));
//        tour.addTeam(new Team(stu,"team6"));
//        tour.addTeam(new Team(stu,"team7"));
//        tour.addTeam(new Team(stu,"team8"));
//        tour.addTeam(new Team(stu,"team9"));
//        tour.addTeam(new Team(stu,"team10"));
//        tour.addTeam(new Team(stu,"team11"));
//        tour.addTeam(new Team(stu,"team12"));
//        tour.addTeam(new Team(stu,"team13"));
//        tour.addTeam(new Team(stu,"team14"));
//        tour.addTeam(new Team(stu,"team15"));
//        tour.addTeam(new classes.Team(stu,"team16"));
//        for(int i = 0 ; i < 16 ; i++){
//            tour.addTeam(team);
//        }
    }
    public static void updateMatches(RoundRobin tourney, ArrayList<TextField> textFields){
        for(int i = 0 ; i < textFields.size() ; i++){
            String textInput = textFields.get(i).getText();
            if(textInput.isEmpty()){
                continue;
            }
            int score =Integer.parseInt(textFields.get(i).getText()) ;

            String[] info =  textFields.get(i).getId().split("-");
            int roundIndex = Integer.parseInt(info[0]) ;
            int matchIndex = Integer.parseInt(info[1]) ;
            int teamIndex = Integer.parseInt(info[2]) ;
            tourney.updateMatchSingleteam(roundIndex , matchIndex, score , teamIndex );
        }
    }
    public static HBox makeTruePlacement(RoundRobin tourney){
        HBox placementTable = new HBox();
        placementTable.setSpacing(20);
        placementTable.getChildren().add(new Text("real placement"));
        Team[] placementTeams  = tourney.getPlacement();
        for(int i = 0 ; i < placementTeams.length ; i++ ){
            placementTable.getChildren().add(createPlacmentTeam(placementTeams[i].getTeamName(),i+1));
        }
        return placementTable;
    }
    public static VBox createPlacmentTeam(String teamName , int placement){
        Text teamNameText = new Text(teamName);
        Text placmentText = new Text(placement +"");
        VBox teamPoints = new VBox(teamNameText,placmentText);
        return teamPoints;
    }
    

//    public static VBox createPointsTable(){
//
//    }
}

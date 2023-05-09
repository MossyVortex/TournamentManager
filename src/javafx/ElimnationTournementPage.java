package javafx;

import classes.Elimination;
import classes.Match;
import classes.Student;
import classes.Team;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;

public class ElimnationTournementPage extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        System.out.println("Hello, World!");
        Elimination tourney = new Elimination();
        tourney.setName("tour");
        ArrayList<Student> stu = new ArrayList<>();
        stu.add(new Student("mads"));
        stu.add(new Student("sads"));
        stu.add(new Student("aads"));
        Team t1 = new Team(stu,"ssd");
        addTeams(tourney, t1 , stu);
        tourney.setEndingDate(LocalDate.of(2023,5,10));
        tourney.setStartingDate(LocalDate.of(2023,5,5));
        tourney.createMatchHistory();
//        ArrayList<TextField> textFields = new ArrayList<>();
//        VBox elimnationVbox = new VBox(createTourney(tourney,textFields));
//        System.out.println(textFields);
//        Button updateScoreButton = new Button();
//        updateScoreButton.setText("calcuateWinners");
//        VBox mainTourney = new VBox(updateScoreButton, elimnationVbox);

        VBox mainTourney = createTourneyPage(tourney);


        Scene scene = new Scene(mainTourney,1000,800);

//        updateScoreButton.setOnAction(e ->{
//            updateMatches(tourney, textFields);
//            tourney.calcuateWinnersMatches();
//            tourney.printMatchHistoryBeautified();
//            VBox elimnationVboxUpdated = new VBox(createTourney(tourney, textFields));
//            mainTourney.getChildren().remove(1);
//            mainTourney.getChildren().add(elimnationVboxUpdated);
//
//            System.out.println("no errors");
//        });

        stage.setScene(scene);
        stage.show();
    }
    public static VBox createTourneyPage(Elimination tourney){
        tourney.createMatchHistory();
        ArrayList<TextField> textFields = new ArrayList<>();
        VBox elimnationVbox = new VBox(createTourney(tourney,textFields));
        System.out.println(textFields);
        Button updateScoreButton = new Button();
        updateScoreButton.setText("calcuateWinners");
        Text winnerText = new Text("tournement winner :  "  + tourney.getWinner());
        VBox mainTourney = new VBox(updateScoreButton, elimnationVbox, winnerText);
        updateScoreButton.setOnAction(e ->{
            updateMatches(tourney, textFields);
            tourney.calcuateWinnersMatches();
            tourney.printMatchHistoryBeautified();
            VBox elimnationVboxUpdated = new VBox(createTourney(tourney, textFields));
            Text winnerTextUpdated = new Text("tournement winner :  "  + tourney.getWinner());
            mainTourney.getChildren().remove(1);
            mainTourney.getChildren().remove(1);
            mainTourney.getChildren().add(elimnationVboxUpdated);
            mainTourney.getChildren().add(winnerTextUpdated);

            System.out.println("no errors");
        });
        return mainTourney;
    }
    public static void addTeams(Elimination tour, Team team , ArrayList<Student> stu){

        tour.addTeam(new Team(stu,"team1"));
        tour.addTeam(new Team(stu,"team2"));
        tour.addTeam(new Team(stu,"team3"));
        tour.addTeam(new Team(stu,"team4"));
//        tour.addTeam(new Team(stu,"team5"));.
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
    public static VBox createMatchUP(Match match ,int roundIndex ,int matchIndex , ArrayList<TextField> textFields){
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

        Text dateText = new Text(match.getDate());
        matchup.getChildren().add(dateText);

        if(match.getLocalDate().isBefore(LocalDate.now()))
            matchup.setStyle("-fx-background-color: red");
        return matchup;
    }
    public static VBox createRound(ArrayList<Match> matches, int roundIndex , ArrayList<TextField> textFields  ){
        VBox round = new VBox();
        round.setSpacing(20);
        for(int i = 0 ; i < matches.size() ; i++){
            round.getChildren().add(createMatchUP(matches.get(i),roundIndex , i , textFields));
        }
        return round;
    }
    public static VBox createTourney(Elimination tourney, ArrayList<TextField> textFields){
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
    public static void updateMatches(Elimination tourney, ArrayList<TextField> textFields){
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
//    public static Button createUpdateButton(){
//        Button updateButton = new Button("update");
//
//    }
}


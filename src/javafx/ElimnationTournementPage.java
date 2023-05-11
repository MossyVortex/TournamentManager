package javafx;

import classes.*;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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

        ScrollPane mainTourney = createTourneyPage(tourney);


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
    //        tourney.createMatchHistory();
    public static ScrollPane createTourneyPage(Elimination tourney){

        ArrayList<TextField> textFields = new ArrayList<>();
        ArrayList<VBox>  matchUps = new ArrayList<>();
        VBox elimnationVbox = new VBox(createTourney(tourney,textFields,matchUps));
        for(int i = 0 ; i < matchUps.size() ; i++){
            Button editButton = new Button("Edit this match");
            Node hb = matchUps.get(i).getChildren().get(0);

            matchUps.get(i).getChildren().add(editButton);
            editButton.setOnAction(e->{
                System.out.println(hb.getId());
            });
        }
        Button updateScoreButton = new Button();
        updateScoreButton.setText("calcuateWinners / updateMatches");
        HBox placementTable = makeTruePlacement(tourney);

        VBox mainTourney = new VBox(updateScoreButton, elimnationVbox, placementTable);
//        VBox mainTourney = new VBox(updateScoreButton, elimnationVbox);
        updateScoreButton.setOnAction(e ->{

            updateMatches(tourney, textFields);

            tourney.calcuateWinnersMatches();
            ArrayList<VBox>  updatedMatchups = new ArrayList<>();
            VBox elimnationVboxUpdated = new VBox(createTourney(tourney, textFields,updatedMatchups));

            tourney.updatePlacementTable();
            System.out.println(placementTable);
            for(int i = 0 ; i < updatedMatchups.size() ; i++){
                Button editButton = new Button("Edit this match");
                Node hb = updatedMatchups.get(i).getChildren().get(0);


                updatedMatchups.get(i).getChildren().add(editButton);
                editButton.setOnAction(y->{
                    String[] id = hb.getId().split("-");
                    int roundIndex = Integer.parseInt(id[0]);
                    int matchIndex = Integer.parseInt(id[1]);
                    tourney.updateMatchup(roundIndex, matchIndex);
                    tourney.printMatchHistoryBeautified();
//                    updateScoreButton.getOnAction();
                });
            }





            HBox updatedPlacementTable = makeTruePlacement(tourney);
            mainTourney.getChildren().remove(1);
            mainTourney.getChildren().remove(1);
            mainTourney.getChildren().add(elimnationVboxUpdated);
            mainTourney.getChildren().add(updatedPlacementTable);


        });
        ScrollPane mainTt = new ScrollPane(mainTourney);
        return mainTt;
    }
    public static void addTeams(Elimination tour, Team team , ArrayList<Student> stu){

        tour.addTeam(new Team(stu,"team1"));
        tour.addTeam(new Team(stu,"team2"));
        tour.addTeam(new Team(stu,"team3"));
        tour.addTeam(new Team(stu,"team4"));
        tour.addTeam(new Team(stu,"team5"));
        tour.addTeam(new Team(stu,"team6"));
        tour.addTeam(new Team(stu,"team7"));
        tour.addTeam(new Team(stu,"team8"));
        tour.addTeam(new Team(stu,"team9"));
        tour.addTeam(new Team(stu,"team10"));
        tour.addTeam(new Team(stu,"team11"));
        tour.addTeam(new Team(stu,"team12"));
        tour.addTeam(new Team(stu,"team13"));
        tour.addTeam(new Team(stu,"team14"));
        tour.addTeam(new Team(stu,"team15"));
//        tour.addTeam(new classes.Team(stu,"team16"));
//        for(int i = 0 ; i < 16 ; i++){
//            tour.addTeam(team);
//        }
    }
    public static VBox createMatchUP(Match match ,int roundIndex ,int matchIndex , ArrayList<TextField> textFields,ArrayList<VBox> matchUps){
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




        if(match.getLocalDate().isBefore(LocalDate.now()) && match.getScoreOne() == -1 || match.getScoreTwo() == -1 )
            matchup.setStyle("-fx-background-color: red");
        matchup.setId(roundIndex + "-" + matchIndex + "-");
        team1HBox.setId(roundIndex + "-" + matchIndex );
        matchUps.add(matchup);
        return matchup;
    }
    public static VBox createRound(ArrayList<Match> matches, int roundIndex , ArrayList<TextField> textFields ,ArrayList<VBox> matchUps  ){
        VBox round = new VBox();
        round.setSpacing(20);
        for(int i = 0 ; i < matches.size() ; i++){
            round.getChildren().add(createMatchUP(matches.get(i),roundIndex , i , textFields, matchUps));
        }
        return round;
    }
    public static VBox createTourney(Elimination tourney, ArrayList<TextField> textFields ,ArrayList<VBox> matchUps){
        textFields.clear();
        HBox tourneyHbox = new HBox();
        tourneyHbox.setSpacing(20);
        Hashtable<Integer, ArrayList<Match>> matchHistory = tourney.printMatchHistory();
        for(int i = 0 ; i < matchHistory.size() ; i++){
            tourneyHbox.getChildren().add(createRound(matchHistory.get(i) , i , textFields ,matchUps));
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
    public static HBox makeTruePlacement(Elimination tourney){
        tourney.updatePlacementTable();
        HBox placementTable = new HBox();
        placementTable.setSpacing(20);
        placementTable.getChildren().add(new Text("real placement"));
        Hashtable<Integer, ArrayList<Team>> placementTeams  = tourney.getPlacementTable();
        for(int i = tourney.getRounds(); i >= 0 ; i--){
            ArrayList<Team> currentTeams = placementTeams.get(i);
            placementTable.getChildren().add(createPlacmentTeam(currentTeams, i));
        }

        return placementTable;
    }
    public static VBox createPlacmentTeam(ArrayList<Team> teams , int placement){
        Text round = new Text(placement+1 + "");
        VBox teamsNames = new VBox();
        if(teams == null) {
            teamsNames.getChildren().add(new Text("empty"));
        }
        else{
            for(int i = 0 ; i < teams.size() ; i++){
                teamsNames.getChildren().add(new Text(teams.get(i).getTeamName()));
            }
        }


        VBox teamPoints = new VBox(round,teamsNames);
        return teamPoints;
    }

//    public static Button createUpdateButton(){
//        Button updateButton = new Button("update");
//
//    }
}


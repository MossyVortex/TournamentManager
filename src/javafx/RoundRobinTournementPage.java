package javafx;

import classes.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Statement;
import java.util.ArrayList;

import static javafx.application.Application.launch;

public class RoundRobinTournementPage extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        System.out.println("Hello, World!");
        RoundRobin tourney = new RoundRobin();
        tourney.setName("tour");
        ArrayList<Student> stu = new ArrayList<>();
        stu.add(new Student("mads"));
        stu.add(new Student("sads"));
        stu.add(new Student("aads"));
        Team t1 = new Team(stu,"ssd");
        addTeams(tourney, t1 , stu);


        VBox mainTourney = createTourneyPage(tourney);


        Scene scene = new Scene(mainTourney,1000,800);



        stage.setScene(scene);
        stage.show();
    }
    public static VBox createTourneyPage(RoundRobin tourney){
       return new VBox();
    }
    public static void addTeams(RoundRobin tour, Team team , ArrayList<Student> stu){

        tour.addTeam(new Team(stu,"team1"));
        tour.addTeam(new Team(stu,"team2"));
        tour.addTeam(new Team(stu,"team3"));
        tour.addTeam(new Team(stu,"team4"));
        tour.addTeam(new Team(stu,"team5"));
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



//    public static VBox createPointsTable(){
//
//    }

    public static VBox createMatchUP(Match match , int roundIndex , int matchIndex , ArrayList<TextField> textFields){
        VBox matchup = new VBox();

        Label team1Label = new Label(match.getTeamOneName() + " ");
        Text team1Score = new Text(match.getScoreOne() +"");
        TextField team1TextField = new TextField();
        team1TextField.setId(roundIndex + "-" + matchIndex + "-" + 0);
        HBox team1HBox = new HBox();
        team1HBox.getChildren().addAll(team1Label,team1Score,team1TextField);
        team1HBox.setSpacing(10);

        Label team2Label = new Label(match.getTeamTwoName() + "");
        Text team2Score = new Text(match.getScoreTwo() +"");
        TextField team2TextField = new TextField();
        team2TextField.setId(roundIndex + "-" + matchIndex + "-" + 1 );
        HBox team2HBox = new HBox();
        team2HBox.getChildren().addAll(team2Label,team2Score ,team2TextField);
        team2HBox.setSpacing(10);

        textFields.add(team1TextField);
        textFields.add(team2TextField);
        matchup.getChildren().addAll(team1HBox, team2HBox);
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
}

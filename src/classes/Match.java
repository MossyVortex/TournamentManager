package classes;

import java.io.Serializable;
import java.sql.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Match implements Serializable, Comparable<Match> {
    private Team teamOne;
    private Team teamTwo;
    private int scoreOne;
    private int scoreTwo;
    private LocalDate matchDate;
    private String timeOfMatch;
    private String gameType;
    private String matchStatus;
    private int matchNum;
    private boolean undeletedTeam1;
    private boolean undeletedTeam2;

    public Match(Team teamOne, Team teamTwo, String gameType, int matchNum){
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.gameType = gameType;
        this.matchNum = matchNum;
        this.matchDate = null;

    }
    public Match(Team teamOne, Team teamTwo, String gameType){
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.gameType = gameType;
        this.matchDate = null;

    }
    public Match(Team teamOne, Team teamTwo){
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.scoreOne = -1;
        this.scoreTwo = -1;
    }
    public Match(Team teamOne, Team teamTwo , LocalDate date){
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.scoreOne = -1;
        this.scoreTwo = -1;
        this.matchDate = date;
    }
    public Match(){
        this.scoreOne = -1;
        this.scoreTwo = -1;
    }
    public void updateScores(int score1, int score2){

        this.scoreOne = score1;
        this.scoreTwo = score2;

    }
    public void updateScoreSingleTeam(int score, int teamIndex){
        if(teamIndex == 0){
            this.scoreOne = score;
        }
        else if(teamIndex == 1){
            this.scoreTwo = score;
        }
        else System.out.println("error");
    }
    public void addTeam(Team team){
        if(teamOne ==null && teamTwo == null){
            teamOne = team;
        }
        else if(teamTwo == null){
            if(teamOne != team)
                teamTwo = team;
        }
        else if(teamOne == null){
            if(teamTwo != team)
                teamOne =team;
        }
        else{
            System.out.println("probleim");
            System.out.println("teamOne name " +teamOne.getTeamName());
            System.out.println("teamTwo name " + teamTwo.getTeamName());
        }
    }

    public void updateDate(LocalDate newDate){

        matchDate = newDate;
    }

    public int[] returnScore(){

        int[] scoreArray = new int[1];
        scoreArray[0] = this.scoreOne;
        scoreArray[1] = this.scoreTwo;

        return scoreArray; // make it print it same as toString?
    }

    public String returnWinner(){
        if(scoreOne>scoreTwo){
            return "classes.Team " + teamOne.getTeamName() + " is the winner";
        }
        else{
            return "classes.Team " + teamTwo.getTeamName() + " is the winner";
        }
    }
    public Object returnWinnerTeam(){
        if(scoreOne>scoreTwo){
            return teamOne;
        }
        else if(scoreOne < scoreTwo){
            return teamTwo;
        }
        else if(scoreOne == -1 || scoreTwo == -1){
            return "undefined";
        }
        else {
            return "draw";
        }
    }
    public Object returnLoserTeam(){
        if(scoreOne>scoreTwo){
            return teamTwo;
        }
        else if(scoreOne < scoreTwo){
            return teamOne;
        }
        else if(scoreOne == -1 || scoreTwo == -1){
            return "undefined";
        }
        else {
            return "draw";
        }
    }

    @Override
    public String toString() {

        String teamOneName = "";
        String teamTwoName = "";
        int teamOneScore = -1;
        int teamTwoScore = -1;

        if(teamOne == null) {
            teamOneName = "notFound";
        }
        else{
            teamOneName = teamOne.getTeamName();
            teamOneScore = scoreOne;
        }
        if(teamTwo == null){
            teamTwoName = "notFound";
        }
        else{
            teamTwoName = teamTwo.getTeamName();
            teamTwoScore = scoreTwo;
        }


        return teamOneName + " " + teamOneScore + " VS " + teamTwoName + " " + teamTwoScore + " " + matchDate ;
    }
    public String getTeamOneName(){
        if (teamOne != null)
            return teamOne.getTeamName();
        return "undefined";
    }
    public String getTeamTwoName(){
        if(teamTwo != null)
            return teamTwo.getTeamName();
        return "undefined";
    }
    public int getScoreOne(){

        return scoreOne;
    }
    public String getDate(){
        if(matchDate == null) return "date not selected";
        return matchDate.toString();
    }
    public void undeleteableUpdate(){
        if(!undeletedTeam1) undeletedTeam1 = true;
        else if(!undeletedTeam2) undeletedTeam2 = true;
    }
    public LocalDate getLocalDate(){
        return matchDate;
    }
    public ArrayList<Team> getTeams(){
        ArrayList<Team> teamsArray =new ArrayList<>();
        teamsArray.add(teamOne);
        teamsArray.add(teamTwo);
        return teamsArray;
    }
    public void forceTeamChange(Team team1 , Team team2){
        if(!team1.equals(teamOne)){
            teamOne = team1;
            teamTwo = team2;
            int tmp = scoreOne;
            scoreOne = scoreTwo;
            scoreTwo = scoreOne;
        }

    }
    public void forceScoreZero(){
        this.scoreTwo = -1;
        this.scoreOne = -1;
    }
    public void  forceTeamsVanish(){
        this.teamOne = null;
        this.teamTwo = null;
    }
    public void forceTeamOneOut(){
        if(undeletedTeam1 && undeletedTeam2) return;
        else if(!undeletedTeam1) this.teamOne = null;
        else this.teamTwo = null;
    }
    public void forceTeamTwoOut(){
        if(undeletedTeam1 && undeletedTeam2) return;
        else if(!undeletedTeam2) this.teamTwo = null;
        else this.teamOne = null;
    }
    public Team getTeamOne(){return teamOne;}
    public Team getTeamTwo(){return teamTwo;}
    public int getScoreTwo(){
        return scoreTwo;
    }
    @Override
    public int compareTo(Match o) {

        if(getDate().compareTo(o.getDate())>0)
            return 1;
        else if(getDate().compareTo(o.getDate())<0)
            return -1;
        else 
            return 0;
    }
//    @Override
//    public int compareTo(Match o) {
//        return this.matchDate.compareTo(o.matchDate);
//    }

}

package classes;

import java.util.Date;

public class Match {
    private Team teamOne;
    private Team teamTwo;
    private int scoreOne;
    private int scoreTwo;
    private Date matchDate;
    private String timeOfMatch;
    private String gameType;
    private String matchStatus;
    private int matchNum;

    public Match(Team teamOne, Team teamTwo, String gameType, int matchNum){
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.gameType = gameType;
        this.matchNum = matchNum;
        this.matchDate = new Date();

    }
    public Match(Team teamOne, Team teamTwo, String gameType){
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.gameType = gameType;
        this.matchDate = new Date();

    }
    public Match(Team teamOne, Team teamTwo){
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
    }
    public Match(){


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

    public void updateDate(Date newDate){

        // why does this exist in the class diagram ?
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
        else return "draw";
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


        return teamOneName + " " + teamOneScore + " VS " + teamTwoName + " " + teamTwoScore;
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
    public int getScoreTwo(){
        return scoreTwo;
    }
}

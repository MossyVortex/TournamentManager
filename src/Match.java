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
    public void updateScore(int score1, int score2){

        this.scoreOne = score1;
        this.scoreTwo = score2;
        System.out.println("Updated the sc ore for match number " + this.matchNum);

    }
    public void addTeam(Team team){
        if(teamOne ==null){
            teamOne = team;
        }
        else if(teamTwo == null){
            teamTwo = team;
        }
        else System.out.println("probleim");
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
            return "Team " + teamOne.getTeamName() + " is the winner";
        }
        else{
            return "Team " + teamTwo.getTeamName() + " is the winner";
        }
    }
    public Team returnWinnerTeam(){
        if(scoreOne>scoreTwo){
            return teamOne;
        }
        else{
            return teamTwo;
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


        return teamOneName + " " + teamOneScore + " VS " + teamTwoName + " " + teamTwoScore;
    }
}

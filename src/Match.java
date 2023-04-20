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

    public void updateScore(int score1, int score2){

        this.scoreOne = score1;
        this.scoreTwo = score2;
        System.out.println("Updated the score for match number " + this.matchNum);

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



}

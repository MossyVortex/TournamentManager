import java.util.ArrayList;
import java.util.Date;

public class RoundRobin extends Tournament {
    
    public RoundRobin(String name, String gameType, String tournamentID, String winner,  Date startingDate, Date endingDate,
    ArrayList<Team> teams, int numOfTeams, String[] bannedStudentsIDs, ArrayList<Student> students){ // constructor

        super(name, gameType, tournamentID, winner, startingDate, endingDate, teams, numOfTeams, bannedStudentsIDs, students);                   
    }

    public void createRoundRobin(){

    }

    public double calculatePoints(){
        return 0.0;
    }

    public void updatePlaces(){

    }

    public Date[] getDates(){
        Date[] datesArray = new Date[999999];
        return datesArray;
    }

    public void getPlaces(){ // return type was written dictionary in the class diagram

    }
//
//    public Team[] getTeams(){
//        Team[] teamsArray = new Team[999999];
//        return teamsArray;
//    }

    public void udpateMatchHistory(){

    }

    public void makeMatchHistory(){

    }

    public void updateMatch(int scoreOne, int scoreTwo){

    }



}

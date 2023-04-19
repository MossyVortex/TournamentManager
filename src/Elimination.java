import java.util.ArrayList;
import java.util.Date;

public class Elimination extends Tournament{

    public Elimination(String name, String gameType, String tournamentID, String winner,  Date startingDate, Date endingDate,
    ArrayList<Team> teams, int numOfTeams, String[] bannedStudentsIDs, ArrayList<Student> students){ // constructor

        super(name, gameType, tournamentID, winner, startingDate, endingDate, teams, numOfTeams, bannedStudentsIDs, students);                   
    }

}
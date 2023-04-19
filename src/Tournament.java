import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;

public abstract class Tournament {
    private String name, type, gameType, tournamentID, winner;
    private Date startingDate, endingDate;
    private ArrayList<Team> teams; private Boolean tournamentRegistrationStatus;
    private int numOfTeams; private Dictionary<Integer,Team> teamPlace; private Dictionary<String,Match[]> matchHistory;
    private String[] bannedStudentsIDs; private ArrayList<Student> students;

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public  Date[] getDates() {
        Date[] dates = {startingDate,endingDate};
        return dates;
    }

    public void leave(int ID){
//        students.remove()
    }
    public void join(int ID){
//        students.remove()
    }
    public void finishTournement(File file){

    }
    public String generateTournamentID(){
        return Integer.toString((int) (Math.random() * 10000)+100000);
    }
}

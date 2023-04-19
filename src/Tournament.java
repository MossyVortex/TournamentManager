import java.util.ArrayList;
import java.util.Date;

public abstract class Tournament {
    private String name, type, gameType, tournamentID, winner;
    private Date startingDate, endingDate;
    private ArrayList<Team> teams; private Boolean tournamentRegistrationStatus;
    private int numOfTeams;

//    public ArrayList<Team> getTeams() {
//        return teams;
//    }

    public  Date[] getDates() {
        Date[] dates = {startingDate,endingDate};
        return dates;
    }
}

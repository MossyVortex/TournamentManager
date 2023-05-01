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
    private boolean registerationStatus;

    public Tournament(String name, String gameType, String tournamentID, String winner, Date startingDate, Date endingDate,
     ArrayList<Team> teams, int numOfTeams, String[] bannedStudentsIDs, ArrayList<Student> students, boolean registerationStatus){ // abstract constructor

        this.name = name;
        this.gameType = gameType;
        this.tournamentID = tournamentID;
        this.winner = winner;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.teams = teams;
        this.numOfTeams = numOfTeams;
        this.bannedStudentsIDs = bannedStudentsIDs;
        this.students = students;
        this.registerationStatus = registerationStatus;

    }
    public Tournament(){ // abstract constructor

        this.name = "";
        this.gameType = "" ;
        this.tournamentID = "";
        this.winner = "";
        this.startingDate = null;
        this.endingDate = null;
        this.teams = new ArrayList<>();
        this.numOfTeams = 0;
        this.bannedStudentsIDs = null;
        this.students = null;

    }
    public void addTeam(Team team){
        teams.add(team);
    }
    public void setName(String name){
        this.name = name;
    }



    public ArrayList<Team> getTeams() {
        return teams;
    }

    public  Date[] getDates() {
        Date[] dates = {startingDate,endingDate};
        return dates;
    }
    public int getTeamsLength(){
        return teams.size();
    }
    public void leave(int ID){
//        students.remove()
    }
    public void join(int ID){
//        students.remove()
    }
    public void finishTournement(File file){

    }

    public void freezeRegisteration(){
        this.registerationStatus = false;
    }

    public void unfreezeRegisteration(){
        this.registerationStatus = true;
    }

    public boolean getRegisterationStatus(){
        return this.registerationStatus;
    }

    public String generateTournamentID(){
        return Integer.toString((int) (Math.random() * 10000)+100000);
    }
}

package classes;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class Tournament {
    private String name, type, gameType, tournamentID, winner;
    private LocalDate startingDate, endingDate;
    private ArrayList<Team> teams; private Boolean tournamentRegistrationStatus;
    private int numOfTeams; private Dictionary<Integer,Team> teamPlace; private Dictionary<String,Match[]> matchHistory;
    private String[] bannedStudentsIDs; private ArrayList<Student> students;
    private boolean registerationStatus;
    private int membersPerTeam;

    public Tournament(String name, String gameType, String tournamentID, String winner, LocalDate startingDate, LocalDate endingDate,
     ArrayList<Team> teams, int numOfTeams, String[] bannedStudentsIDs, ArrayList<Student> students, boolean registerationStatus,
     int membersPerTeam){ // abstract constructor

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
        this.membersPerTeam = membersPerTeam;

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



    public  ArrayList<Team> getTeams() {
        return teams;
    }

    public  LocalDate[] getDates() {
        LocalDate[] dates = {startingDate,endingDate};
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

    public static String generateID() {
        Set<String> existingIDs = new HashSet<>(); // Keep track of existing IDs
        int uniqueIDLength = 5;
        String uniqueID = null;

        Random randomizer = new Random();

        // Generate a new unique ID that doesn't exist in the set of existing IDs
        do {
            uniqueID = String.format("%05d", randomizer.nextInt(100000)); // Generate a 5-digit number with leading zeros
        } while (existingIDs.contains(uniqueID));

        existingIDs.add(uniqueID); // Add the new ID to the set of existing IDs
        return uniqueID;
    }

    public String getID(){
        return this.tournamentID;
    }
}

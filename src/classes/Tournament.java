package classes;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public abstract class Tournament implements Serializable {
    private String name, type, gameType, tournamentID;
    Team winner;
    private LocalDate startingDate, endingDate;
    private ArrayList<Team> teams; private Boolean tournamentRegistrationStatus;
    private int numOfTeams; private Dictionary<Integer,Team> teamPlace; private Dictionary<String,Match[]> matchHistory;
    private String[] bannedStudentsIDs; private ArrayList<Student> students;
    private boolean registerationStatus;
    private int membersPerTeam;
    private static final long serialVersionUID = 8237865757165260457L;

    public Tournament(String name, String gameType, String type, String tournamentID, Team winner, LocalDate startingDate, LocalDate endingDate,
     ArrayList<Team> teams, int numOfTeams, ArrayList<Student> students, boolean registerationStatus,
     int membersPerTeam){ // abstract constructor

        this.name = name;
        this.gameType = gameType;
        this.type = type;
        this.tournamentID = tournamentID;
        this.winner = winner;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.teams = teams;
        this.numOfTeams = numOfTeams;
        this.students = students;
        this.registerationStatus = registerationStatus;
        this.membersPerTeam = membersPerTeam;

    }
    public Tournament(){ // abstract constructor

        this.name = "";
        this.gameType = "" ;
        this.tournamentID = "";
        this.winner = null;
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



    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Team getWinner() {
        return winner;
    }
//
//    public void setWinner(String winner) {
//        this.winner = winner;
//    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public LocalDate getEndingDate() {
        return endingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public void setEndingDate(LocalDate endingDate) {
        this.endingDate = endingDate;
    }

    public String getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(String tournamentID) {
        this.tournamentID = tournamentID;
    }

    public Dictionary<Integer, Team> getTeamPlace() {
        return teamPlace;
    }

    public Boolean getTournamentRegistrationStatus() {
        return tournamentRegistrationStatus;
    }

    public void setTournamentRegistrationStatus(Boolean tournamentRegistrationStatus) {
        this.tournamentRegistrationStatus = tournamentRegistrationStatus;
    }

    public void setTeamPlace(Dictionary<Integer, Team> teamPlace) {
        this.teamPlace = teamPlace;
    }

    public Dictionary<String, Match[]> getMatchHistory() {
        return matchHistory;
    }

    public void setMatchHistory(Dictionary<String, Match[]> matchHistory) {
        this.matchHistory = matchHistory;
    }

    public String[] getBannedStudentsIDs() {
        return bannedStudentsIDs;
    }

    public void setBannedStudentsIDs(String[] bannedStudentsIDs) {
        this.bannedStudentsIDs = bannedStudentsIDs;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public int getNumOfTeams() {
        return numOfTeams;
    }

    public void setNumOfTeams(int numOfTeams) {
        this.numOfTeams = numOfTeams;
    }

    public int getMembersPerTeam() {
        return membersPerTeam;
    }

    public void setMembersPerTeam(int membersPerTeam) {
        this.membersPerTeam = membersPerTeam;
    }

    public void setRegisterationStatus(boolean registerationStatus) {
        this.registerationStatus = registerationStatus;
    }

    public boolean isRegisterationStatus() {
        return registerationStatus;
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = teams;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", gameType='" + gameType + '\'' +
                ", tournamentID='" + tournamentID + '\'' +
                ", winner='" + winner + '\'' +
                ", startingDate=" + startingDate +
                ", endingDate=" + endingDate +
                ", teams=" + teams +
                ", tournamentRegistrationStatus=" + tournamentRegistrationStatus +
                ", numOfTeams=" + numOfTeams +
                ", teamPlace=" + teamPlace +
                ", matchHistory=" + matchHistory +
                ", bannedStudentsIDs=" + Arrays.toString(bannedStudentsIDs) +
                ", students=" + students +
                ", registerationStatus=" + registerationStatus +
                ", membersPerTeam=" + membersPerTeam +
                '}';
    }
}

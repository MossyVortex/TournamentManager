package classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

public class RoundRobin extends Tournament {
    private Hashtable<Integer, ArrayList<Match>> matchHistory;
    private Hashtable<Team, Integer> pointsTable;

    public RoundRobin(String name, String gameType, String tournamentID, String winner,  LocalDate startingDate, LocalDate endingDate,
    ArrayList<Team> teams, int numOfTeams, String[] bannedStudentsIDs, ArrayList<Student> students, boolean registerationStatus){ // constructor

        super(name, gameType, tournamentID, winner, startingDate, endingDate, teams, numOfTeams, bannedStudentsIDs, students, true);                   
    }

    public RoundRobin(){
        super();
    }

    public void createMatchHistory(){
        Hashtable<Integer, ArrayList<Match>> matchHistory = new Hashtable<>();
        ArrayList<Team> teams = new ArrayList<>();
//        int incremental = teams.size()/2
//        teams are even
//        comment
        if(teams.size() + 1 % 2 == 0){
            for(int i = 0 ; i < teams.size()  - 1 ; i++){
                ArrayList<Match> currentRound = new ArrayList<>();
                for(int j = 0 ; j < (teams.size()+1 / 2)  ; j++){
                    Match currentMatch = new Match();
                    currentMatch.addTeam(teams.get(i));
                    currentMatch.addTeam(teams.get(j));
                }
            }
        }
    }

    public double calculatePoints(){
        return 0.0;
    }

    public void updatePlaces(){

    }

    public LocalDate[] getDates(){
        LocalDate[] datesArray = new LocalDate[999999];
        return datesArray;
    }

    public void getPlaces(){ // return type was written dictionary in the class diagram

    }
//
//    public classes.Team[] getTeams(){
//        classes.Team[] teamsArray = new classes.Team[999999];
//        return teamsArray;
//    }

    public void udpateMatchHistory(){

    }

    public void makeMatchHistory(){

    }

    public void updateMatch(int scoreOne, int scoreTwo){

    }



}

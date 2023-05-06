package classes;

import java.time.LocalDate;
import java.util.*;

public class RoundRobin extends Tournament {
    private Hashtable<Integer, ArrayList<Match>> matchHistory;
    private Hashtable<Team, Integer> winsHistory;
    private Hashtable<Team, Integer> goalDifferance;
    private Hashtable<Team, Integer> pointsTable;

    private ArrayList<Team> placement;


    public RoundRobin(String name, String gameType, String tournamentID, String winner,  LocalDate startingDate, LocalDate endingDate,
    ArrayList<Team> teams, int numOfTeams, String[] bannedStudentsIDs, ArrayList<Student> students, boolean registerationStatus){ // constructor

        super(name, gameType, tournamentID, winner, startingDate, endingDate, teams, numOfTeams, bannedStudentsIDs, students, true);                   
    }

    public RoundRobin(){
        super();
    }

    public void createMatchHistory(){
        int n = getTeams().size();
        Hashtable<Integer, ArrayList<Match>> matchHistory = new Hashtable<>();
        Team[] teams = Arrays.copyOfRange(getTeams().toArray(new Team[getTeams().size()]),1,getTeams().size()) ;
        Team pivot = getTeams().get(0);
        System.out.println(Arrays.toString(teams));
        if(n % 2 == 1 ){
            teams = Arrays.copyOf(teams, teams.length + 1);
            teams[teams.length - 1] = new Team("Bye");
            n++;
        }
        for (int r = 1; r < n ; r++){
            Team[] lst2 = new Team[teams.length + 1];
            lst2[0] = pivot;
            System.arraycopy(teams,0,lst2,1 , teams.length);
            ArrayList<Match> roundMatches = new ArrayList<>();
            for(int i = 0 ; i < n/2 ; i++ ){
                roundMatches.add(new Match(lst2[i],lst2[n-1-i]));
            }
            matchHistory.put(r-1,roundMatches);
            rotate(teams);
        }
        this.matchHistory = matchHistory;
    }

    public void createTables(){
        Hashtable<Team, Integer> pointsTable = new Hashtable<>();
        Hashtable<Team, Integer> winsHistory = new Hashtable<>();
        Hashtable<Team, Integer> goalDiffernce = new Hashtable<>();
        ArrayList<Team> teams = getTeams();
        for(int i = 0 ; i < teams.size(); i++ ){
            pointsTable.put(teams.get(i),0);
            winsHistory.put(teams.get(i),0);
            goalDiffernce.put(teams.get(i),0);
        }
        this.pointsTable = pointsTable;
        this.winsHistory = winsHistory;
        this.goalDifferance = goalDiffernce;
    }
    public void updateWinsHistory(){
        for(int i = 0 ; i< matchHistory.size(); i++){
            ArrayList<Match> currentRound = matchHistory.get(i);
            for(int j = 0 ; j < currentRound.size() ; j++){
                Match currentMatch = currentRound.get(j);
                Team team1 = currentMatch.getTeamOne();
                Team team2 = currentMatch.getTeamTwo();
                if(!currentMatch.returnWinnerTeam().equals("draw")){
                    winsHistory.put((Team) currentMatch.returnWinnerTeam(),winsHistory.get((Team) currentMatch.returnWinnerTeam()) + 1);
                }
            }
        }
    }
    public void updateGoalDiffernce(){
        for(int i = 0 ; i< matchHistory.size(); i++){
            ArrayList<Match> currentRound = matchHistory.get(i);
            for(int j = 0 ; j < currentRound.size() ; j++){
                Match currentMatch = currentRound.get(j);
                Team team1 = currentMatch.getTeamOne();
                Team team2 = currentMatch.getTeamTwo();

                goalDifferance.put(team1,goalDifferance.get(team1) + currentMatch.getScoreOne() - currentMatch.getScoreTwo());
                goalDifferance.put(team2,goalDifferance.get(team2) + currentMatch.getScoreTwo()- currentMatch.getScoreOne());
            }
        }
    }

    public void updatePointsTable(){
        createTables();
        for(int i = 0 ; i< matchHistory.size(); i++){
            ArrayList<Match> currentRound = matchHistory.get(i);
            for(int j = 0 ; j < currentRound.size() ; j++){
                Match currentMatch = currentRound.get(j);
                Team team1 = currentMatch.getTeamOne();
                Team team2 = currentMatch.getTeamTwo();
                if(currentMatch.returnWinnerTeam().equals("draw")){
                    pointsTable.put(team1,pointsTable.get(team1) + 1);
                    pointsTable.put(team2,pointsTable.get(team2) + 1);
                }
                else{
                    pointsTable.put((Team) currentMatch.returnWinnerTeam(),pointsTable.get((Team) currentMatch.returnWinnerTeam()) + 3);
                }
            }
        }
    }
    public  void rotate(Team[] lst) {
        int len = lst.length;
        Team last = lst[len - 1];
        for (int i = len - 1; i >= 1; i--) {
            lst[i] = lst[i - 1];
        }
        lst[0] = last;
    }
    public void updateMatch(int roundIndex , int matchIndex , int scoreOne , int scoreTwo){
        Match currentMatch =  matchHistory.get(roundIndex).get(matchIndex);
        currentMatch.updateScores(scoreOne, scoreTwo);
    }
    public void updateMatchSingleteam(int roundIndex , int matchIndex , int score, int teamIndex){
        Match currentMatch =  matchHistory.get(roundIndex).get(matchIndex);
        currentMatch.updateScoreSingleTeam(score,teamIndex);
    }



    public LocalDate[] getDates(){
        LocalDate[] datesArray = new LocalDate[999999];
        return datesArray;
    }


    public Hashtable<Team, Integer> printPointsTable(){return this.pointsTable;}

    public Hashtable<Integer, ArrayList<Match>> printMatchHistory(){
        return matchHistory;
    }

    public void printMatchHistoryBeautified(){
        for(Map.Entry<Integer, ArrayList<Match>> entry : matchHistory.entrySet() ){
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
        }
    }
    public void printPointsTableBeautifed(){
        for(Map.Entry<Team, Integer> entry : pointsTable.entrySet() ){
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
        }
    }
    public void printWinsHistory(){
        for(Map.Entry<Team, Integer> entry : winsHistory.entrySet() ){
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
        }
    }
    public void printGoalDiffernce(){
        for(Map.Entry<Team, Integer> entry : goalDifferance.entrySet() ){
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
        }
    }

}

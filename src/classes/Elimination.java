package classes;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Elimination extends Tournament implements Serializable {
    private Hashtable<Integer, ArrayList<Match>> matchHistory;
    private int rounds;
    public Elimination(String name, String gameType, String type, String tournamentID, String winner,  LocalDate startingDate, LocalDate endingDate,
    ArrayList<Team> teams, int numOfTeams,  ArrayList<Student> students, int membersPerTeam, boolean registerationStatus){ // constructor

        super(name, gameType,type, tournamentID, winner, startingDate, endingDate, teams, numOfTeams, students, registerationStatus, membersPerTeam);
    }
    public Elimination(){
        super();
    }
    public void createMatchHistory(){
        Hashtable<Integer, ArrayList<Match>> matchHistory = new Hashtable<Integer, ArrayList<Match>>();

        boolean visitedOdd = false;
        ArrayList<Team> allTeams  = getTeams();
        ArrayList<Team> availableTeams = getTeams();
        calculateRounds();
        int originalLength =allTeams.size();
        System.out.println("teams length " + allTeams.size());
        System.out.println("rounds "+rounds);
        for(int i = rounds; i > 0 ; i--){
            if(Math.log(availableTeams.size())/Math.log(2) % 1 == 0  || availableTeams.size() == 0 || visitedOdd){

                int numMatchesCurrentRound = (int) Math.pow(2,i-1);

                ArrayList<Match> matchesCurrentRound = new ArrayList<>();
//                System.out.println("better matches ? " + Math.pow(2,i-1));
//                System.out.println("matches current round" +numMatchesCurrentRound);

                for(int j = numMatchesCurrentRound; j > 0 ; j--){
                    int z = 0;
                    Match currentMatch = new Match();
                    if(availableTeams.size() == 1){
                        currentMatch.addTeam(availableTeams.remove(0));
                    } else if(availableTeams.size() != 0){
                        currentMatch.addTeam(availableTeams.remove(0));
                        currentMatch.addTeam(availableTeams.remove(0));
                    }
//                    System.out.println("teams size" +availableTeams.size());
                    matchesCurrentRound.add(currentMatch);
//                    z +=2 ;
                }
                Collections.reverse(matchesCurrentRound);
                matchHistory.put(rounds-i, matchesCurrentRound);

            }
            else{
                visitedOdd = true;
                ArrayList<Match> matchesCurrentRound = new ArrayList<>();

                int numMatchesCurrentRound =(int) (allTeams.size() - Math.pow(2,rounds-1));
                System.out.println(numMatchesCurrentRound + " numbersrsr");
                for(int j =numMatchesCurrentRound ; j > 0 ; j-- ){

                    Match currentMatch = new Match();
                    if(availableTeams.size() == 1){
                        currentMatch.addTeam(availableTeams.remove(0));
                    } else if(availableTeams.size() != 0){
                        currentMatch.addTeam(availableTeams.remove(0));
                        currentMatch.addTeam(availableTeams.remove(0));
                    }
//                    System.out.println("teams size" +availableTeams.size());
                    matchesCurrentRound.add(currentMatch);
//
                }
                matchHistory.put(rounds-i, matchesCurrentRound);

            }
        }
        this.matchHistory = matchHistory;
    }
    public void updateMatch(int roundIndex , int matchIndex , int scoreOne , int scoreTwo){
        Match currentMatch =  matchHistory.get(roundIndex).get(matchIndex);
        currentMatch.updateScores(scoreOne, scoreTwo);
    }
    public void updateMatchSingleteam(int roundIndex , int matchIndex , int score, int teamIndex){
        Match currentMatch =  matchHistory.get(roundIndex).get(matchIndex);
        currentMatch.updateScoreSingleTeam(score,teamIndex);
    }
    public void calcuateWinnersMatches(){
        for(int i = 0  ; i < rounds - 1 ; i++){
            ArrayList<Match> currentRound = matchHistory.get(i) ;
            ArrayList<Match> nextRound = matchHistory.get(i+1) ;

            for(int j = 0 ; j < currentRound.size() ; j++ ){
                Match currentMatch = currentRound.get(j);
                if(currentMatch.returnWinnerTeam().equals("draw")){
                    continue;
                }
                Team winnerTeam = (Team) currentMatch.returnWinnerTeam();
                Match nextMatch = nextRound.get((int) Math.floor(j/2));
                nextMatch.addTeam(winnerTeam);

            }
        }


    }
    private void calculateRounds(){
        this.rounds = (int) Math.ceil((Math.log(getTeams().size())/Math.log(2)));
    }
    public Hashtable<Integer, ArrayList<Match>> printMatchHistory(){
       return matchHistory;
    }
    public void printMatchHistoryBeautified(){
        for(Map.Entry<Integer, ArrayList<Match>> entry : matchHistory.entrySet() ){
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
        }
    }
}
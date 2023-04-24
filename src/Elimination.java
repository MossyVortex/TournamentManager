import java.util.*;

public class Elimination extends Tournament{
    private Hashtable<Integer, ArrayList<Match>> matchHistory;
    private int rounds;
    public Elimination(String name, String gameType, String tournamentID, String winner,  Date startingDate, Date endingDate,
    ArrayList<Team> teams, int numOfTeams, String[] bannedStudentsIDs, ArrayList<Student> students){ // constructor

        super(name, gameType, tournamentID, winner, startingDate, endingDate, teams, numOfTeams, bannedStudentsIDs, students);                   
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
    private void calculateRounds(){
        this.rounds = (int) Math.ceil((Math.log(getTeams().size())/Math.log(2)));
    }
    public Dictionary printMatchHistory(){
       return matchHistory;
    }
}
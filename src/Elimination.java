import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;

public class Elimination extends Tournament{
    private Hashtable<Integer, ArrayList<Match>> matchHistory;
    public Elimination(String name, String gameType, String tournamentID, String winner,  Date startingDate, Date endingDate,
    ArrayList<Team> teams, int numOfTeams, String[] bannedStudentsIDs, ArrayList<Student> students){ // constructor

        super(name, gameType, tournamentID, winner, startingDate, endingDate, teams, numOfTeams, bannedStudentsIDs, students);                   
    }
    public Elimination(){
        super();
    }
    public void createMatchHistory(){
        Hashtable<Integer, ArrayList<Match>> matchHistory = new Hashtable<Integer, ArrayList<Match>>();


        ArrayList<Team> allTeams  = getTeams();
        ArrayList<Team> availableTeams = getTeams();
        int rounds =(int) Math.ceil((Math.log(getTeams().size())/Math.log(2)));
        int originalLength =allTeams.size();
        System.out.println("teams length " + allTeams.size());
        System.out.println("rounds "+rounds);
        for(int i = rounds; i > 0 ; i--){
            if(Math.log(availableTeams.size())/Math.log(2) % 1 == 0  || availableTeams.size() == 0){

                int numMatchesCurrentRound = (int) Math.pow(2,i-1);

                ArrayList<Match> matchesCurrentRound = new ArrayList<>();
                System.out.println("better matches ? " + Math.pow(2,i-1));
                System.out.println("matches current round" +numMatchesCurrentRound);

                for(int j = numMatchesCurrentRound; j > 0 ; j--){
                    int z = 0;
                    Match currentMatch = new Match();
                    if(availableTeams.size() != 0){
                        currentMatch.addTeam(availableTeams.remove(z+1));
                        currentMatch.addTeam(availableTeams.remove(z));
                    }
                    System.out.println("teams size" +availableTeams.size());
                    matchesCurrentRound.add(currentMatch);
                    z +=2 ;
                }
                matchHistory.put(rounds-i, matchesCurrentRound);

            }
            else{

            }
        }
        this.matchHistory = matchHistory;
    }
    public Dictionary printMatchHistory(){
       return matchHistory;
    }
}
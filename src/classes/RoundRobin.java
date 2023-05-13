package classes;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class RoundRobin extends Tournament implements Serializable {
    private Hashtable<Integer, ArrayList<Match>> matchHistory;
    private Hashtable<Team, Integer> winsHistory;
    private Hashtable<Team, Integer> goalDifferance;
    private LinkedHashMap<Team, Integer> pointsTable;
    private static final long serialVersionUID = -56722074485822064L;

    private ArrayList<Team> placement;
    private int rounds;


    public RoundRobin(String name, String gameType, String type, String tournamentID, Team winner,  LocalDate startingDate, LocalDate endingDate,
    ArrayList<Team> teams, int numOfTeams, ArrayList<Student> students, int membersPerTeam, boolean registerationStatus, boolean isGenerated){ // constructor

        super(name, gameType,type, tournamentID, winner, startingDate, endingDate, teams, numOfTeams,  students, true, membersPerTeam, isGenerated);
    }

    public RoundRobin(){
        super();
    }

    public void createMatchHistory(){
        int n = getTeams().size();
        double incremenByDay = calcuateDayIncrement();
        Hashtable<Integer, ArrayList<Match>> matchHistory = new Hashtable<>();
        Team[] teams = Arrays.copyOfRange(getTeams().toArray(new Team[getTeams().size()]),1,getTeams().size()) ;
        Team pivot = getTeams().get(0);
//        System.out.println(Arrays.toString(teams));
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
                Match currentMatch = new Match(lst2[i],lst2[n-1-i]);
                if(getStartingDate() != null && getEndingDate() != null)
                    currentMatch.updateDate(getStartingDate().plusDays((long)Math.floor(incremenByDay *(r -1))));
                roundMatches.add(currentMatch);
            }
            matchHistory.put(r-1,roundMatches);
            rotate(teams);
        }
        this.matchHistory = matchHistory;
    }

    public void createTables(){
        LinkedHashMap<Team, Integer> pointsTable = new LinkedHashMap<>();
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
    public void updateTables(){
        updateWinsHistory();
        updateGoalDiffernce();
        updatePointsTable();
    }
    public void updateWinsHistory(){
        zeroWinsTable();
        for(int i = 0 ; i< matchHistory.size(); i++){
            ArrayList<Match> currentRound = matchHistory.get(i);
            for(int j = 0 ; j < currentRound.size() ; j++){
                Match currentMatch = currentRound.get(j);
                Team team1 = currentMatch.getTeamOne();
                Team team2 = currentMatch.getTeamTwo();
                if(!currentMatch.returnWinnerTeam().equals("draw") && !currentMatch.returnWinnerTeam().equals("undefined")){
                    winsHistory.put((Team) currentMatch.returnWinnerTeam(),winsHistory.get((Team) currentMatch.returnWinnerTeam()) + 1);
                }
            }
        }
    }
    public void updateGoalDiffernce(){
        zeroGoalsTable();
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
    public void zeroPointsTable(){
        for(Map.Entry<Team, Integer> entry : pointsTable.entrySet() ){
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
            pointsTable.put(entry.getKey(), 0);
        }
    }
    public void zeroWinsTable(){
        for(Map.Entry<Team, Integer> entry : winsHistory.entrySet() ){
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
            winsHistory.put(entry.getKey(), 0);
        }
    }

    public void zeroGoalsTable(){
        for(Map.Entry<Team, Integer> entry : goalDifferance.entrySet() ){
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
            goalDifferance.put(entry.getKey(), 0);
        }
    }
    public void updatePointsTable(){
//        createTables();
        zeroPointsTable();
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
                else if (!currentMatch.returnWinnerTeam().equals("undefined")){
                    pointsTable.put((Team) currentMatch.returnWinnerTeam(),pointsTable.get((Team) currentMatch.returnWinnerTeam()) + 3);
                }
            }
        }
        sortPointsTable();
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
    public void sortPointsTable(){
        List<Map.Entry<Team, Integer>> list = new ArrayList<Map.Entry<Team, Integer>>(pointsTable.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Team, Integer>>(){

            public int compare(Map.Entry<Team, Integer> entry1, Map.Entry<Team, Integer> entry2) {
                return entry2.getValue().compareTo( entry1.getValue() );
            }

        });
        LinkedHashMap <Team, Integer> mapSortedByValues = new LinkedHashMap <Team, Integer>();
        for( Map.Entry<Team, Integer> entry : list  ){
            mapSortedByValues.put(entry.getKey(), entry.getValue());
        }
//        System.out.println(mapSortedByValues);
        this.pointsTable = mapSortedByValues;
    }

    public double calcuateDayIncrement(){
        double increment = 0;

        if(getStartingDate() == null || getEndingDate() == null) return 0;
        int diffInDays = (int) DAYS.between(getStartingDate(), getEndingDate());
//        System.out.println(diffInDays);
        getRounds();
        if(rounds == 0) return 0;
        increment = diffInDays/ rounds;
        if(increment < 0) return 0;
        return increment;

    }

    public int twoTeamsbestScorer(Match match){
        if(match.getScoreOne() > match.getScoreTwo()){
            return 1;
        }
        else if(match.getScoreOne() < match.getScoreTwo()){
            return -1;
        }
        else return 0;
    }
    public Match searchForMatch(Team team1 , Team team2){
        for(int i = 0 ; i < matchHistory.size() ; i++){
            ArrayList<Match> currentRound = matchHistory.get(i);
            for(int j = 0 ; j < currentRound.size() ; j++ ){
                Match currentMatch = currentRound.get(j);
                if(currentMatch.getTeams().contains(team1) && currentMatch.getTeams().contains(team2))
                    return currentMatch;
            }

        }
        return new Match();
    }
    public int compareTo(Team team1, Team team2){
        if(pointsTable.get(team1) > pointsTable.get(team2)){
            return 1;
        }
        else if(pointsTable.get(team2) > pointsTable.get(team1)){
            return  -1;
        }
        else{
            Match match = searchForMatch(team1,team2);
            match.forceTeamChange(team1,team2);
            if(match.getScoreOne() > match.getScoreTwo() ){
                return 1;
            }
            else if(match.getScoreOne() < match.getScoreTwo()){
                return -1;
            }
            else{
                if(winsHistory.get(team1) > winsHistory.get(team2)){
                    return 1;
                }
                else if(winsHistory.get(team1) < winsHistory.get(team2)){
                    return -1;
                }
                else{
                    if(goalDifferance.get(team1) > goalDifferance.get(team2)){
                        return 1;
                    }
                    else if(goalDifferance.get(team2) > goalDifferance.get(team1)){
                        return -1;
                    }
                    else return 1;
                }
            }
        }
    }
    public Team[] getPlacement(){
        Team[] placement = getTeams().toArray(new Team[0]);
        sort(placement);
        System.out.println(Arrays.toString(placement));
        return placement;
    }
    public void sort(Team[] arr){
        int n = arr.length;
        do {
            int newN = 0;
            for(int i = 1; i < n; i++) {
                if(compareTo(arr[i], arr[i-1]) == 1) {
                    Team temp = arr[i-1];
                    arr[i-1] = arr[i];
                    arr[i] = temp;

                    newN = i;
                }
            }
            n = newN;
        } while (n != 0);
    }
    public LinkedHashMap<Team, Integer> printPointsTable(){return this.pointsTable;}

    public Hashtable<Integer, ArrayList<Match>> printMatchHistory(){
        return matchHistory;
    }

    public ArrayList<Match> getMatches(){
        ArrayList<Match> matches = new ArrayList<>();
        if(! (this.matchHistory== null)) {
            for (int i = 0; i < matchHistory.size(); i++) {
                matches.addAll(matchHistory.get(i));
            }
        }
        return  matches;

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

    public void getRounds(){
        if(getTeams().size() % 2 == 0) rounds= getTeams().size();
        else rounds = getTeams().size() - 1;
    }
    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public LocalDate getStartingDate() {
        return super.getStartingDate();
    }

    @Override
    public LocalDate getEndingDate() {
        return super.getEndingDate();
    }

    @Override
    public String getGameType() {
        return super.getGameType();
    }

    @Override
    public String getType() {
        return super.getType();
    }

    @Override
    public boolean getRegisterationStatus() {
        return super.getRegisterationStatus();
    }

    @Override
    public String getTournamentID() {
        return super.getTournamentID();
    }

    @Override
    public String toString() {
        return "RoundRobin{" + super.toString()+
                "\nmatchHistory=" + matchHistory +
                ", \nwinsHistory=" + winsHistory +
                ", \ngoalDifferance=" + goalDifferance +
                ", \npointsTable=" + pointsTable +
                ", \nplacement=" + placement +
                '}';
    }

    @Override
    public int compareTo(Match o) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'compareTo'");
    }
}

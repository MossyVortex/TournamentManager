public class Team {
    private String TeamName;
    private int numberOfMembers;
    private int numberOfWins;
    private int numberOfLosses;
    private int numberOFTie;
    private String[] nameOfEachMember;
    private int totalGoalsBcored;
    private int totalRecivedGoals;
    private int totalWins;
    private int totalLosses;
    public Team(){

    }
    public String[] getNameOfEachMember() {
        return nameOfEachMember;
    }
    public String findMember(String member){
        boolean IsFound = false;
        int i= 0;
        while (!IsFound){
            if(nameOfEachMember[i].equals(member)){
                return "Member name " + member +" is exist";
            } else{
                i++;
            }
        }
        return "Member name " + member +" is not exist";
    }
    public String getTeamName() {
        return TeamName;
    }
    public int getNumberOFTie() {
        return numberOFTie;
    }
    public int getNumberOfWins() {
        return numberOfWins;
    }
    public int getNumberOfLosses() {
        return numberOfLosses;
    } 
}

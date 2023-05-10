package classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Team implements Serializable {
    private ArrayList<Student> teamMembers;
    private String teamName;
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
    public Team(ArrayList<Student> teamMembers , String teamName){
        this.teamMembers = teamMembers;
        this.teamName  = teamName;
        this.numberOfMembers = teamMembers.size();
    }

    public Team(ArrayList<Student> e) {
        this.teamMembers = e;
        this.numberOfMembers = teamMembers.size();

    }
    public Team(String name ){this.teamName = name;}

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
        return teamName;
    }
    public String toString(){
        return teamName;
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

    public int getNumberOfMembers(){
        return teamMembers.size();
    }
    public ArrayList<Student> getTeamMembers() {
        return teamMembers;
    }

    public void addStudent(Student student){
        teamMembers.add(student);
    }


    public void setTeamMembers(ArrayList<Student> teamMembers) {
        this.teamMembers = teamMembers;
    }
}

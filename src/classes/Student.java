package classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Student extends Person implements Serializable{
    private String name;
    private int wins;
    private int losses;
    private int ties;
    private int weight;
    private int height;
    private static final long serialVersionUID = -1382814192376511076L;

    private ArrayList<Tournament> joinedTournamentsTournament;

    public Student(String name, String userName , String phoneNumber, String email, String ID, String password,int weight, int height,ArrayList<Tournament> joinedTournamentsTournament, int wins, int losses, int ties){
        super(name,userName,phoneNumber,email,ID,password);
        this.height = height;
        this.weight = weight;
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
        this.joinedTournamentsTournament = joinedTournamentsTournament;
    }
    public Student(String name){
        super(name);
    }

    public Student(String name, String userName,String email, String password){
        super(name,userName,email,password);
    }

    @Override
    public String getName() {
        
        return super.getName();
    }
    @Override
    public String getID() {
        return super.getID();
    }
    public int getWins() {
        return wins;
    }
    public int getLosses() {
        return losses;
    }
    public int getTies() {
        return ties;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    public void setHeight(int height) {
        this.height = height;
    }


    public ArrayList<Tournament> getJoinedTournamentsID() {
        return joinedTournamentsTournament;
    }

    public String getStudentName(){
        return this.name;
    }

    @Override
    public String toString() {
        return "Student{" +
                super.toString() + '\'' +
                ", wins=" + wins +
                ", losses=" + losses +
                ", ties=" + ties +
                ", weight=" + weight +
                ", height=" + height +
                ", joinedTournamentsID=" + getJoinedTournamentsID().toString() +
                '}';
    }
}

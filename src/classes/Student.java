package classes;

import java.util.ArrayList;

public class Student extends Person{
    private String name;
    private int wins;
    private int losses;
    private int ties;
    private double weight;
    private int height;
    private ArrayList<String> joinedTournamentsID;

    public Student(String name, String phoneNumber, String email, String ID, String password,int weight, int height,ArrayList<String> joinedTournamentsID, int wins, int losses, int ties){
        super(name,phoneNumber,email,ID,password);
        this.height = height;
        this.weight = weight;
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
        this.joinedTournamentsID = joinedTournamentsID;
    }
    public Student(String name){
        super(name);
    }

    @Override
    public String getName() {
        
        return super.getName();
    }
    @Override
    public String getID() {
        // TODO Auto-generated method stub
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
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public void setHeight(int height) {
        this.height = height;
    }

    public ArrayList<String> getJoinedTournamentsID() {
        return joinedTournamentsID;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", wins=" + wins +
                ", losses=" + losses +
                ", ties=" + ties +
                ", weight=" + weight +
                ", height=" + height +
                ", joinedTournamentsID=" + joinedTournamentsID +
                '}';
    }
}

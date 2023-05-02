package classes;

public class Student extends Person{
    private String name;
    private int wins;
    private int losses;
    private int ties;
    private double weight;
    private int height;

    public Student(String name, String phoneNumber, String email, String ID, String password,int weight, int height, int wins, int losses, int ties){
        super(name,phoneNumber,email,ID,password);
        this.height = height;
        this.weight = weight;
        this.wins = wins;
        this.losses = losses;
        this.ties = ties;
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
}

public class Student extends Person{
    private int wins;
    private int losses;
    private int ties;
    private double weight;
    private int hight;

    public Student(){
        super();
    }

    @Override
    public String getName() {
        
        return super.getName();
    }
    @Override
    public int getID() {
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
    public void setHight(int hight) {
        this.hight = hight;
    }
}

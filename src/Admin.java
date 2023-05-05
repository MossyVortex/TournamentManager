import classes.Person;

public class Admin extends Person {
    private int tournamentsCreated;
    private boolean IsAuthorized;
    
    public Admin(String name, String phoneNumber, String email, String ID, String password,int tournamentsCreated,boolean IsAuthorized){
        super(name,phoneNumber,email,ID,password);
        this.tournamentsCreated = tournamentsCreated;
        this.IsAuthorized = IsAuthorized;
    }
     public int getTournamentsCreated() {
         return tournamentsCreated;
     }
     public boolean getIsAuthorized() {
         return IsAuthorized;
     }
}

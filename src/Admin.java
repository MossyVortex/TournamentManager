import classes.Person;

import java.io.Serializable;

public class Admin extends Person implements Serializable {
    private int tournamentsCreated;
    private boolean IsAuthorized;
    
    public Admin(String name, String userName , String phoneNumber, String email, String ID, String password,int tournamentsCreated,boolean IsAuthorized){
        super(name,userName,phoneNumber,email,ID,password);
        this.tournamentsCreated = tournamentsCreated;
        this.IsAuthorized = IsAuthorized;
    }

    public Admin(String userName, String password){
        super(userName,password);
    }
     public int getTournamentsCreated() {
         return tournamentsCreated;
     }
     public boolean getIsAuthorized() {
         return IsAuthorized;
     }
}

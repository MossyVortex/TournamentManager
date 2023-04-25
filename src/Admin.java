public class Admin extends Person{
    private int tournamentsCreated;
    private boolean IsAuthorized;
    private String club;

    public Admin(String name, String phoneNumber, String email, int ID, String password,int tournamentsCreated,boolean IsAuthorized){
        super(name,phoneNumber,email,ID,password);
        this.tournamentsCreated = tournamentsCreated;
    }
     public int getTournamentsCreated() {
         return tournamentsCreated;
     }
     public boolean getIsAuthorized() {
         return IsAuthorized;
     }
}

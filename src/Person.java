public class Person {
    private String name;
    private String phoneNumber;
    private String email;
    private int ID;
    private String password;
    
    public Person(String name, String phoneNumber, String email, int ID, String password){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.ID = ID;
        this.password = password;
    }
    
    private String generateID(int ID){
        return Integer.toString((int) (Math.random() * 10000)+200000);
    }
    public String getName() {
        return name;
    }
    public int getID() {
        return ID;
    } 
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
}

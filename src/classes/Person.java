package classes;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Person implements Serializable {
    private String name;
    private String phoneNumber;
    private String email;
    private String ID;
    private String password;
    private String userName;
    private static final long serialVersionUID = -4584107448582206664L;

    public Person(String name, String userName ,String phoneNumber, String email, String ID, String password){
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.ID = ID;
        this.password = password;
        this.userName = userName;
    }
    public Person(String name){
        this.name = name;
    }

    public Person(String userName,String password){
        this.userName = userName;
        this.password=password;
    }

    public Person(String name, String userName,String email, String password){
        this.name = name;
        this.email = email;
        this.password = password;
        this.userName = userName;
    }

    public Person(String name, String userName ,String email){
        this.name = name;
        this.email = email;
        this.userName = userName;
    }

    public void generateID(String registerType){

        String csvFileStudent = "src\\StudentsFile.csv";
        String csvFileAdmin = "src\\AdminsFile.csv";

        if(registerType.equals("Admin")){

            String lastID = getLastID(csvFileAdmin);
            String generatedID = incrementString(lastID);
            this.ID = "22" + generatedID;
        }
        else{

            String lastID = getLastID(csvFileStudent);
            String generatedID = incrementString(lastID);

            this.ID = "11" + generatedID;        
        }
    }
    public String getName() {
        return name;
    }
    public String getID() {
        return ID;
    } 
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static String getLastID(String filePath){
        String csvFile = filePath;
        String line = "";
        String csvSplitBy = ",";

        String lastID = null;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                lastID = data[1]; // Assuming ID is in the second column
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastID;
    }

    public static String incrementString(String oldStr){

        String shortenedStr = oldStr.substring(2, oldStr.length() - 1); // remove last digit and first two digits

        String lastDigit = oldStr.substring(oldStr.length() - 1);

        int lastDigitInt = Integer.parseInt(lastDigit);

        if(lastDigitInt < 9){
            
            lastDigitInt++;
            
            String incrementedString = shortenedStr + lastDigitInt;
            
            return incrementedString;
        }
        else{
            return incrementString(shortenedStr, 1); // remove last digit
        }
    }

    public static String incrementString(String oldStr, int recursiveCount){

        String shortenedStr = oldStr.substring(0, oldStr.length() - 1); // remove last digit

        String lastDigit = oldStr.substring(oldStr.length() - 1);

        int lastDigitInt = Integer.parseInt(lastDigit);

        if(lastDigitInt < 9){

            lastDigitInt++;
            
            String incrementedString = shortenedStr + lastDigitInt;

            for(int i = 0; i < recursiveCount; i++){
                incrementedString += "0";
            }
            
            return incrementedString;
        }
        else{
            return incrementString(shortenedStr, recursiveCount+1); 
        }

    }

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", ID='" + ID + '\'' +
                ", password='" + password + '\'' ;
    }
}

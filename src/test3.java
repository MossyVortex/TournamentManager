import classes.RoundRobin;
import classes.Student;
import classes.Tournament;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class test3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(new FileOutputStream("src\\TournamentsBFile.dat"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
            ArrayList<Tournament> tournaments = new ArrayList<>();
            HashMap<String, Tournament> studentHashMap = new HashMap<>() ;
            Tournament tournament = new RoundRobin();
            studentHashMap.put(tournament.getID(),tournament);
    
            objectOutputStream.writeObject(studentHashMap);
            
            objectOutputStream.close();

            ObjectInputStream objSInStream = null;
            try {
                objSInStream = new ObjectInputStream(new FileInputStream("src\\StudentsBFile.dat"));
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            HashMap<String, Student> readStudentInfoMap ;
            readStudentInfoMap = (HashMap<String, Student>) objSInStream.readObject();
            readStudentInfoMap.forEach((x,y) -> System.out.println(y));
            
            objSInStream.close();
    }
    
}

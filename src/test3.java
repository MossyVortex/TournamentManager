import classes.RoundRobin;
import classes.Student;
import classes.Team;
import classes.Tournament;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class test3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        try {

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("src\\TournamentsBFile.dat"));

            HashMap<String, Tournament> tournaments = new HashMap<>() ;
            ArrayList<Team > teams = new ArrayList<>();
            Tournament tournament =new RoundRobin("Term221","Football","RoundRobin","10000",null, LocalDate.now(),LocalDate.now(),teams,5,null,3,true, false);
            tournaments.put(tournament.getTournamentID(), tournament);
            objectOutputStream.writeObject(tournaments);
            objectOutputStream.close();
            ObjectInputStream objSInStream = new ObjectInputStream(new FileInputStream("src\\TournamentsBFile.dat"));
            HashMap<String, Tournament> readStudentInfoMap ;
            readStudentInfoMap = (HashMap<String, Tournament>) objSInStream.readObject();
            readStudentInfoMap.forEach((x,y) -> System.out.println(y));

            objSInStream.close();
        }

        catch (IOException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

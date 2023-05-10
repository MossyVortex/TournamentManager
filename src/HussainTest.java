import classes.RoundRobin;
import classes.Student;
import classes.Team;
import classes.Tournament;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class HussainTest {
//    public static void main(String[] args) {
//        ArrayList<Team> teams = new ArrayList<>();
//        Tournament tournament =new RoundRobin("Term221","Football","RoundRobin","10000",null, LocalDate.now(),LocalDate.now(),teams,5,null,3,true, false);
//        System.out.println(tournament);
//        for (Team team : tournament.getTeams()){
//            for (Student student:team.getTeamMembers()) {
//                System.out.println(student.getName());
//            }
//        }
//        System.out.println("________________________________\n");
//        Student student = new Student("Hussain");
//        Student student1 = new Student("Ali");
//        Student student2 = new Student("Saud");
//        ArrayList<Student> students = new ArrayList<>();
//        students.add(student);
//        students.add(student1);
//        Team team = new Team(students, "Football 1");
//
//        tournament.addTeam(team);
//
//        for (Team team2 : tournament.getTeams()){
//            for (Student student4:team2.getTeamMembers()) {
//                System.out.println(student4.getName());
//            }
//        }
//        System.out.println("________________________________\n");
//        tournament.addStudentInTeam(student2,team);
//        for (Team team2 : tournament.getTeams()){
//            for (Student student4:team2.getTeamMembers()) {
//                System.out.println(student4.getName());
//            }
//        }
//
//    }
public static void main(String[] args) {

//    System.out.println(inAPIStudent("6992","3443"));
    System.out.println(inAPIStudent("6992"));
    System.out.println(checkAdmin("6992"));

}

    public static boolean checkAdmin(String username) { // Using the API
        try {
            String urlPartI = "https://us-central1-swe206-221.cloudfunctions.net/app/UserSignIn?username=";


            URL obj = new URL(urlPartI + username );
            HttpsURLConnection conn = (HttpsURLConnection) obj.openConnection();
            System.out.println(conn.getResponseCode());

            // Set request method
            conn.setRequestMethod("GET");

            // Read response
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            return true;

        } catch (Exception ex) {
            return false;
        }
    }
    public static boolean inAPIStudent(String ID) {
        try {
            String path = "https://us-central1-swe206-221.cloudfunctions.net/app/UserSignIn?username=" + ID ;
            URL url = new URL(path);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if (status <= 299) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                StringBuffer responseContent = new StringBuffer();
                while ((line = reader.readLine()) != null) {
                    responseContent.append(line);
                }
                reader.close();
                String dataFromAPI = responseContent.substring(9);
                String name = "";
                int i = 0;
                while (dataFromAPI.charAt(i) != '"') {
                    name += dataFromAPI.charAt(i);
                    i++;
                }
                Student student = new Student(name);
            }

            System.out.println(status);
            return status<=299;
        }
        catch (Exception e){
            return false;
        }
    }
//    public static boolean inAPIStudent(String ID,String password) {
//        try {
//            String path = "https://us-central1-swe206-221.cloudfunctions.net/app/UserSignIn?username=" + ID + "&password=" + password;
//            URL url = new URL(path);
//
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//
//            connection.setRequestMethod("GET");
//            connection.setConnectTimeout(5000);
//            connection.setReadTimeout(5000);
//
//            int status = connection.getResponseCode();
//
//            if (status <= 299) {
//                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                String line = "";
//                StringBuffer responseContent = new StringBuffer();
//                while ((line = reader.readLine()) != null) {
//                    responseContent.append(line);
//                }
//                reader.close();
//                String dataFromAPI = responseContent.substring(9);
//                String name = "";
//                int i = 0;
//                while (dataFromAPI.charAt(i) != '"') {
//                    name += dataFromAPI.charAt(i);
//                    i++;
//                }
//                Student student = new Student(name, ID, "Abdulmjeed.alothman222@gmail.com", password);
//                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src\\LogedinPerson.dat"));
//                outputStream.writeObject(student);
//                outputStream.close();
//            }
//
//            return status<=299;
//        }
//        catch (Exception e){
//            return false;
//        }
//    }
}
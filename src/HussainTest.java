import classes.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class HussainTest {
    public static void main(String[] args) {
        try {


            ObjectOutputStream objOutStream = new ObjectOutputStream( new FileOutputStream("StudentsBFile.dat"));
            ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream("StudentsBFile.dat"));

            File file = new File("StudentsFile.csv");
            Scanner scanner = new Scanner(file);
            String first = scanner.nextLine();
            String line; String[] strings;
            Student student;
            HashMap<String, Student> studentInfoMap = new HashMap<>();
            HashMap<String, Student> readStudentInfoMap = new HashMap<>();
            ArrayList<String> joinedTournamentsID = new ArrayList<>();
            while (scanner.hasNext()){
                line = scanner.nextLine();
                strings = line.split(",",12);
                student = new Student(strings[0],strings[2],strings[3],strings[1],strings[4],Integer.parseInt(strings[6]),Integer.parseInt(strings[7]),
                        joinedTournamentsID,Integer.parseInt(strings[8]),Integer.parseInt(strings[9]),Integer.parseInt(strings[10]));
                studentInfoMap.put(student.getID(),student);
            }
            objOutStream.writeObject(studentInfoMap);
            readStudentInfoMap = (HashMap<String, Student>) objInStream.readObject();
            readStudentInfoMap.forEach((x,y) -> System.out.println(y));

            objInStream.close();
            objOutStream.close();
            scanner.close();


        }

        catch (IOException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

import classes.Student;
import classes.Tournament;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class HussainTest {
    public static void main(String[] args) {
        try {

//            FileOutputStream fileOutputStream = new FileOutputStream("src\\AdminsBFile.dat");
//            FileInputStream fileInputStream = new FileInputStream("src\\AdminsBFile.dat");
//            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
//            BufferedInputStream bufferedInputStream =new BufferedInputStream(fileInputStream);
//            ObjectOutputStream objOutStream = new ObjectOutputStream(bufferedOutputStream);
//            ObjectInputStream objInStream = new ObjectInputStream(bufferedInputStream);
//            ObjectOutputStream objOutStream = new ObjectOutputStream(fileOutputStream);


//            ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream("src\\AdminsBFile.dat"));
//            HashMap<String, Admin> readStudentInfoMap ;
//            readStudentInfoMap = (HashMap<String, Admin>) objInStream.readObject();
//            readStudentInfoMap.forEach((x,y) -> System.out.println(y));
//            objInStream.close();

            ObjectInputStream objSInStream = new ObjectInputStream(new FileInputStream("src\\StudentsBFile.dat"));
            HashMap<String, Student> readStudentInfoMap ;
            readStudentInfoMap = (HashMap<String, Student>) objSInStream.readObject();
            readStudentInfoMap.forEach((x,y) -> System.out.println(y));
            objSInStream.close();



//            ArrayList<Tournament> tournaments = new ArrayList<>();

//            Student student = new Student("Hussain","0565000000","hussain@hotmail.com","202010000","12345",90,180,tournaments,0,0,0);
//            Student student1 = new Student("Ali","0565000000","Ali@hotmail.com","202110000","12345",90,180,tournaments,0,0,0);
//            HashMap<String, Admin> adminInfoMap = new HashMap<>();
//            studentInfoMap.put(student.getID(),student);
//            new FileOutputStream("src\\StudentsBFile.dat").close();
//            objOutStream.writeObject(adminInfoMap);

//            HashMap<String, Student> studentInfoMap = new HashMap<>();


//                objOutStream.close();


        }

        catch (IOException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
//            ArrayList<String> joinedTournamentsID = new ArrayList<>();
//            while (scanner.hasNext()){
//                line = scanner.nextLine();
//                strings = line.split(",",12);
//                System.out.println(Arrays.toString(strings));
//                System.out.println(strings[0]+strings[2]+strings[3]+strings[1]+strings[4]+Integer.parseInt(strings[6])+Integer.parseInt(strings[7])+
//                        joinedTournamentsID+Integer.parseInt(strings[8])+Integer.parseInt(strings[9])+Integer.parseInt(strings[10]));
//                student = new Student(strings[0],strings[2],strings[3],strings[1],strings[4],Integer.parseInt(strings[6]),Integer.parseInt(strings[7]),
//                        joinedTournamentsID,Integer.parseInt(strings[8]),Integer.parseInt(strings[9]),Integer.parseInt(strings[10]));
//                studentInfoMap.put(student.getID(),student);
//            }
//            objOutStream.writeObject(studentInfoMap);
//            readStudentInfoMap = (HashMap<String, Student>) objInStream.readObject();
//            readStudentInfoMap.forEach((x,y) -> System.out.println(y));
//            new FileOutputStream("src\\StudentsBFile.dat").close();
//            ObjectOutputStream objOutStream = new ObjectOutputStream( new FileOutputStream("src\\StudentsBFile.dat"));
//            ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream("src\\StudentsBFile.dat"));

//            File file = new File("src\\newSTDF.csv");
//            Scann
//            Student student;er scanner = new Scanner(file);
////            String first = scanner.nextLine();
////            String line; String[] strings;
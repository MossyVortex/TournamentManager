import classes.Student;
import classes.Tournament;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import classes.Person;
import classes.Student;
import classes.Tournament;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class test2 {
//    public static void main(String[] args) {
//        // // Create a HashMap to store student IDs and AVL trees of tournament IDs
//        HashMap<String, AVLTree<String>> joinedTournamentsMap = new HashMap<>();
//
//        // Add some student IDs and tournament IDs to the map
//        addTournament("202016000", "tournament1", joinedTournamentsMap);
//        addTournament("202016000", "tournament2", joinedTournamentsMap);
//        addTournament("202016000", "tournament3", joinedTournamentsMap);
//
//        System.out.println(joinedTournamentsMap.get("202016000").getElements());
//
//        // // Print the tournament IDs for a given student ID
//        // String studentID = "202016000";
//        // AVLTree<String> tournamentTree = studentMap.get(studentID);
//        // if (tournamentTree != null) {
//        //     System.out.println("classes.Tournament IDs for student " + studentID + ":");
//        //     tournamentTree.inOrderTraversal();
//        // } else {
//        //     System.out.println("classes.Student " + studentID + " not found");
//        // }
//    }
//
//    public static void addTournament(String studentID, String tournamentID,
//     HashMap<String, AVLTree<String>> studentMap) {
//            // Get the AVL tree of tournament IDs for the given student ID
//            AVLTree<String> tournamentTree = studentMap.get(studentID);
//            if (tournamentTree == null) {
//                // If the AVL tree doesn't exist yet, create a new one
//                tournamentTree = new AVLTree<>();
//                studentMap.put(studentID, tournamentTree);
//            }
//            // Add the tournament ID to the AVL tree
//            tournamentTree.insert(tournamentID);
//
//
//    }
//
//    // public static long convertStringToInt(String str){
//    //     int length = str.length();
//    //     long result = 0;
//
//    //     for(int i = 0; i < length; i++){
//    //         if(i < (length - 1)){
//    //             result += Integer.parseInt(str.charAt(i) + "")  * Math.pow(10, (length -1 - i));
//    //         }
//    //         else{
//    //             result += Integer.parseInt(str.charAt(i) + "");
//    //         }
//    //     }
//
//    //     return result;
//    // }
public static void main(String[] args) {
//     try {
// //        FileOutputStream fileOutputStream = new FileOutputStream("src\\StudentsBFile.dat");
// //        ObjectOutputStream objOutStream = new ObjectOutputStream(fileOutputStream);
//         FileInputStream fileInputStream = new FileInputStream("src\\StudentsBFile.dat");
//         ObjectInputStream objInStream = new ObjectInputStream(fileInputStream);
//         HashMap<String, Student> readStudentInfoMap ;
//         readStudentInfoMap = (HashMap<String, Student>) objInStream.readObject();
//         readStudentInfoMap.forEach((x,y) -> System.out.println(y));
//         objInStream.close();
//     } catch (FileNotFoundException e) {
//         System.out.println(e.getCause());
//     } catch (IOException e) {
//         System.out.println(e.getCause());
//     } catch (ClassNotFoundException e) {
//         e.printStackTrace();
//     }

try {

    HashMap<String, Admin> adminHashMap = new HashMap<>();
    ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("src\\AdminsBFile.dat"));
    objectOutputStream.writeObject(adminHashMap);
    objectOutputStream.close();

    FileInputStream fileInputStream = new FileInputStream("src\\AdminsBFile.dat");
    ObjectInputStream objInStream = new ObjectInputStream(fileInputStream);
//    ArrayList<Tournament> joinedTournamentsTournament = new ArrayList<>();

    HashMap<String, Admin> studentHashMap ;
    studentHashMap = (HashMap<String, Admin>) objInStream.readObject();

    Admin admin= new Admin("Admin","0555555555","admin@gmail.com","admin","admin",0,true);
    studentHashMap.put(admin.getID(),admin);
    FileOutputStream fileOutputStream = new FileOutputStream("src\\AdminsBFile.dat");
    ObjectOutputStream objOutStream = new ObjectOutputStream(fileOutputStream);
    objOutStream.writeObject(studentHashMap);


//    if (!studentHashMap.containsKey("202016000")){
//        studentHashMap.put("202016000",new Student("MOSA","123","123", "202016000",
//                "123", Integer.parseInt("0"),Integer.parseInt("0"),joinedTournamentsTournament,0,0,0));
//        objOutStream.writeObject(studentHashMap);
//    }
    objInStream.close();
    objOutStream.close();
}
catch (IOException  e){
    System.out.println(e.getCause());
} catch (ClassNotFoundException e) {
    e.printStackTrace();
}
}
}
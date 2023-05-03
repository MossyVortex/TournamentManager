import classes.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

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
    try {
//        FileOutputStream fileOutputStream = new FileOutputStream("src\\StudentsBFile.dat");
//        ObjectOutputStream objOutStream = new ObjectOutputStream(fileOutputStream);
        FileInputStream fileInputStream = new FileInputStream("src\\StudentsBFile.dat");
        ObjectInputStream objInStream = new ObjectInputStream(fileInputStream);
        HashMap<String, Student> readStudentInfoMap ;
        readStudentInfoMap = (HashMap<String, Student>) objInStream.readObject();
        readStudentInfoMap.forEach((x,y) -> System.out.println(y));
        objInStream.close();
    } catch (FileNotFoundException e) {
        System.out.println(e.getCause());
    } catch (IOException e) {
        System.out.println(e.getCause());
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
}
}
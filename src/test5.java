import java.time.LocalDate;
import java.util.ArrayList;

import classes.Elimination;
import classes.Student;
import classes.Team;

public class test5 {
    public static void main(String[] args) {
        Team t1 = new Team();
        Team t2 = new Team();
        Team t3 = new Team();
        Team t4 = new Team();

        Student s1 = new Student("NAME1");
        Student s2 = new Student("NAME2");
        Student s3 = new Student("NAME3");
        Student s4 = new Student("NAME4");
        Student s5 = new Student("NAME5");
        Student s6 = new Student("NAME6");
        Student s7 = new Student("NAME7");
        Student s8 = new Student("NAME8");

        
        LocalDate sd = LocalDate.now();
        LocalDate ed = LocalDate.of(2023, 8, 8);

        ArrayList<Team> teamsArr = new ArrayList<>();

        ArrayList<Student> studentsArr = new ArrayList<>();

        Elimination to1 = new Elimination("TestName", "TestGame", "RoundRobin", "2000",t1, sd, ed, teamsArr,4,studentsArr, 2, true, true);

        ArrayList<Student> tinin = new ArrayList<>();
        
        t1.addStudentToTeam(s1);
        t1.addStudentToTeam(s2);

        t2.addStudentToTeam(s3);
        t2.addStudentToTeam(s4);

        t3.addStudentToTeam(s5);
        t3.addStudentToTeam(s6);

        t4.addStudentToTeam(s7);
        t4.addStudentToTeam(s8);


        to1.addTeam(t1);
        to1.addTeam(t2);
        to1.addTeam(t3);
        to1.addTeam(t4);

        for(int i = 0; i < to1.getTeams().size(); i++){
            Team teami = to1.getTeams().get(i);
            for(int j = 0; j < teami.getTeamMembers().size(); j++){
                System.out.println(teami.getTeamMembers().get(j).getName());
            }
        }

        System.out.println("END of test5");

        // ArrayList<Student> sat = new ArrayList<>();
        // sat.add(s1);
        // sat.add(s2);

        // for(int i = 0; i < sat.size(); i++){
        //     System.out.println(sat.get(i).getName());
        // }




    }
}

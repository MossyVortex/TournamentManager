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

public class HussainTest {
    public static void main(String[] args) {
        ArrayList<Team> teams = new ArrayList<>();
        Tournament tournament =new RoundRobin("Term221","Football","RoundRobin","10000",null, LocalDate.now(),LocalDate.now(),teams,5,null,3,true, false);
        System.out.println(tournament);
        for (Team team : tournament.getTeams()){
            for (Student student:team.getTeamMembers()) {
                System.out.println(student);
            }
        }
    }
}
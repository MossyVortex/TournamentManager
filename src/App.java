import classes.Elimination;
import classes.RoundRobin;
import classes.Student;
import classes.Team;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {

//        System.out.println("Hello, World!");
//        Elimination tourney = new Elimination();
//        tourney.setName("tour");
//        ArrayList<Student> stu = new ArrayList<>();
//        stu.add(new Student("mads"));
//        stu.add(new Student("sads"));
//        stu.add(new Student("aads"));
//        Team t1 = new Team(stu,"ssd");
//        addTeams(tourney, t1 , stu);
//
//        tourney.createMatchHistory();
//        tourney.updateMatch(0,0,21,0);
//        tourney.updateMatch(0,1,21,0);
//
//
//        System.out.println(tourney.printMatchHistory());
//        tourney.calcuateWinnersMatches();
//        tourney.printMatchHistoryBeautified();
//
//        RoundRobin rr1 = new RoundRobin();
//
//        rr1.freezeRegisteration();
//        System.out.println(rr1.getRegisterationStatus());
//        rr1.unfreezeRegisteration();
//        System.out.println(rr1.getRegisterationStatus());
    }
    public static void addTeams(Elimination tour, Team team , ArrayList<Student> stu){

        tour.addTeam(new Team(stu,"team1"));
        tour.addTeam(new Team(stu,"team2"));
        tour.addTeam(new Team(stu,"team3"));
        tour.addTeam(new Team(stu,"team4"));
        tour.addTeam(new Team(stu,"team5"));
        tour.addTeam(new Team(stu,"team6"));
        tour.addTeam(new Team(stu,"team7"));
        tour.addTeam(new Team(stu,"team8"));
        tour.addTeam(new Team(stu,"team9"));
        tour.addTeam(new Team(stu,"team10"));
        tour.addTeam(new Team(stu,"team11"));
        tour.addTeam(new Team(stu,"team12"));
        tour.addTeam(new Team(stu,"team13"));
        tour.addTeam(new Team(stu,"team14"));
        tour.addTeam(new Team(stu,"team15"));

        
//        tour.addTeam(new classes.Team(stu,"team16"));
//        for(int i = 0 ; i < 16 ; i++){
//            tour.addTeam(team);
//        }
    }
}

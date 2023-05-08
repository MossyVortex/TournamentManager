import classes.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

//import static sun.security.util.Debug.args;

public class App {

    public static void rotate(int[] lst) {
        int len = lst.length;
        int last = lst[len - 1];
        for (int i = len - 1; i >= 1; i--) {
            lst[i] = lst[i - 1];
        }
        lst[0] = last;
    }

    public static void roundRobin(int n) {
        int[] lst = new int[n - 1];
        for (int i = 0; i < lst.length; i++) {
            lst[i] = i + 2;
        }
        if (n % 2 == 1) {
            lst = Arrays.copyOf(lst, lst.length + 1);
            lst[lst.length - 1] = 0;
            n++;
        }
        for (int r = 1; r < n; r++) {
            System.out.printf("Round %2d", r);
            int[] lst2 = new int[lst.length + 1];
            lst2[0] = 1;
            System.arraycopy(lst, 0, lst2, 1, lst.length);
            for (int i = 0; i < n / 2; i++) {
                System.out.printf(" (%2d vs %-2d)", lst2[i], lst2[n - 1 - i]);
            }
            System.out.println();
            rotate(lst);
        }
    }

    public static void main(String[] args) {

        LocalDate today = LocalDate.now();
        LocalDate yesterDay = LocalDate.of(2023,05,07);

        Elimination tourney = new Elimination();
//        roundRobin(5);
//        RoundRobin roundTourney = new RoundRobin();
//        roundTourney.setName("roundy");
        ArrayList<Student> stu = new ArrayList<>();
        stu.add(new Student("mads"));
        stu.add(new Student("sads"));
        stu.add(new Student("aads"));
        Team t1 = new Team(stu, "ssd");
        addTeams(tourney, t1, stu);
        LocalDate startDate = LocalDate.of(2023,5,1);
        LocalDate endingDate = LocalDate.of(2023,5,5);

        tourney.createMatchHistory();
        tourney.printMatchHistoryBeautified();
        System.out.println(tourney.calcuateDayIncrement());
//        roundTourney.createMatchHistory();
//        roundTourney.printMatchHistoryBeautified();
//        roundTourney.createTables();
//        roundTourney.printPointsTable();
//        System.out.println("Hello, World!");

//        Elimination tourney = new Elimination();
//        tourney.setName("tour");

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

    public static void addTeams(Tournament tour, Team team, ArrayList<Student> stu) {

        tour.addTeam(new Team(stu, "team1"));
        tour.addTeam(new Team(stu, "team2"));
        tour.addTeam(new Team(stu, "team3"));
        tour.addTeam(new Team(stu, "team4"));
        tour.addTeam(new Team(stu, "team5"));
//        tour.addTeam(new Team(stu, "team6"));
//        tour.addTeam(new Team(stu, "team7"));
//        tour.addTeam(new Team(stu, "team8"));
//        tour.addTeam(new Team(stu, "team9"));
//        tour.addTeam(new Team(stu, "team10"));
//        tour.addTeam(new Team(stu, "team11"));
//        tour.addTeam(new Team(stu, "team12"));
//        tour.addTeam(new Team(stu, "team13"));
//        tour.addTeam(new Team(stu, "team14"));
//        tour.addTeam(new Team(stu, "team15"));


//        tour.addTeam(new classes.Team(stu, "team16"));
//        for (int i = 0; i < 16; i++) {
//            tour.addTeam(team);
//        }
    }
}



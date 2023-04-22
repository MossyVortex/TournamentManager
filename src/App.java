import java.util.ArrayList;
import java.util.Collection;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Elimination tourney = new Elimination();
        tourney.setName("tour");
        ArrayList<Student> stu = new ArrayList<>();
        stu.add(new Student("mads"));
        stu.add(new Student("sads"));
        stu.add(new Student("aads"));
        Team t1 = new Team(stu,"ssd");
        tourney.addTeam(t1);
        tourney.addTeam(t1);
        tourney.addTeam(t1);
        tourney.addTeam(t1);
        tourney.addTeam(t1);
        tourney.addTeam(t1);
        tourney.addTeam(t1);
        tourney.addTeam(t1);

        tourney.createMatchHistory();
        System.out.println(tourney.printMatchHistory());
    }
}

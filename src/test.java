import java.util.ArrayList;
import java.util.Collection;

public class test {
    public static void main(String[] args) {
        Elimination tourney = new Elimination();
        tourney.setName("tour");
        tourney.addTeam(new Team(new ArrayList<Student>((Collection<? extends Student>) new Student("mohaa")) ));

        System.out.println(tourney.getTeamsLength());
    }
}

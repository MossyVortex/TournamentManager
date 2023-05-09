import classes.Student;
import classes.Team;
import classes.Tournament;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class test4again {

    public static void main(String[] args) {
        System.out.println("TEST4: \n");
        try {
            ObjectInputStream objInStreamTournament = new ObjectInputStream(new FileInputStream("src\\TournamentsBFile.dat"));
            HashMap<String, Tournament> tournamentHashMap = (HashMap<String, Tournament>) objInStreamTournament.readObject();
            objInStreamTournament.close();
//            ObjectInputStream objectInStreamSt = new ObjectInputStream(new FileInputStream("src\\LogedinPerson.dat"));
//            Student student = (Student) objectInStreamSt.readObject();
//            objectInStreamSt.close();
            Student student = new Student("MAHDI");



            if (tournamentHashMap.containsKey("10000")) { // if the tournament is created
                Tournament tournament = tournamentHashMap.get("10000");
                if (true) { // individual registration
                    ArrayList<Team> teams = tournament.getTeams();
                    boolean joinedSuccessfully = false;
                    for (int i = 0; i < teams.size(); i++) { // empty spot finder
                        Team availableTeam = teams.get(i);
                        ArrayList<Student> studentArrayList = availableTeam.getTeamMembers();
                        if (studentArrayList.size() < tournament.getMembersPerTeam()) { // team is not full
                            studentArrayList.add(student);
                            joinedSuccessfully = true;
                            availableTeam.setTeamMembers(studentArrayList);


                            // for loop for testing
                            for(int j = 0; j < teams.size(); i++){
                                for (int k = 0; k < tournament.getMembersPerTeam(); k++){
                                    System.out.println(studentArrayList.get(k));
                                }
                            }

                            break;
                                /* Note Now update the team in "ArrayList<Team> teams"
                                    Then in "Tournament tournament"
                                 */
                        }
                    }
                    if (teams.size() < tournament.getNumOfTeams() && !joinedSuccessfully) { // if we need to create new team
                        ArrayList<Student> arrayList = new ArrayList<>();
                        arrayList.add(student);
                        Team team = new Team(arrayList);
                        tournament.addTeam(team);
                    }
                }
                else{ // tournament is not available
                    System.out.println("Tournament has not been created yet!");
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}

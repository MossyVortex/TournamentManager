import java.util.Scanner;

public class mainMenu {
    public static void main(String[] args) {
        System.out.println("Welcome to Tournament Manager App");
        
        //JAVAFX starting point

        String accountType = "guest";
        // login page or register new account page

        // after login update accountType appropriately

        // create a tornament
        createTournament(accountType);

    }

    public static void createTournament(String accountType){

        if(accountType.equals("Admin")){

            String tournamentType = "";
            // choose tournament type page

            boolean flag = false;
            Scanner input = new Scanner(System.in);

            while(!flag){

                System.out.println("choose Touranment Type: ");
                tournamentType = input.nextLine();
                tournamentType.toLowerCase();

                System.out.println("choose Touranment name: ");
                String tournamnetName = input.nextLine();

                System.out.println("what game will you be playing?: ");
                String gameType = input.nextLine();

                String tournamnetID = generateTournamentID(1); // 1 is a placeholder it should be tournament number which will be counted continously and saved in a txt file

                System.out.println("how many teams will play?: ");
                int numOfTeams = input.nextInt();


                if(tournamentType.equals("roundrobin")){
                    System.out.println("tournament name: ");
                    
                    new RoundRobin(tournamnetName, gameType, tournamnetName, tournamnetName, null, null, null, numOfTeams, null, null);
                }
                else if(tournamentType.equals("elimination")){
                    new Elimination(tournamnetName, gameType, tournamnetName, tournamnetName, null, null, null, numOfTeams, null, null);
                }
            }
        }
    }

    public static String generateTournamentID(int numOfTournamnets){
        return "2023" + numOfTournamnets+1;
    } 
}

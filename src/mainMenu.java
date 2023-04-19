import java.util.Scanner;

public class mainMenu {
    public static void main(String[] args) {
        System.out.println("Welcome to Tournament Manager App");
        
        //JAVAFX starting point

        // login page

        // create a tornament

        String type = "";
        // choose tournament type page

        boolean flag = false;
        Scanner input = new Scanner(System.in);

        while(!flag){

            type = input.nextLine();
            type.toLowerCase();
            if(type.equals("roundrobin")){
                System.out.println("tournament name: ");
                
                // new RoundRobin(, type, type, type, null, null, null, 0, args, null)
            }
            else if(type.equals("elimination")){
                // to be continued
            }
        }
    }
}

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import classes.Elimination;
import classes.Person;
import classes.RoundRobin;
import classes.Student;
import classes.Team;
import classes.Tournament;
import javafx.ElimnationTournementPage;
import javafx.RoundRobinTournementPage;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ViewTournamentController implements Initializable {

    @FXML
    private ImageView EditButton;

    @FXML
    private Label IDLabel;

    @FXML
    private Label StatusLabel;

    @FXML
    private TextField StatusTF;

    @FXML
    private TextField WinnerTextField;

    @FXML
    private Label WinsLabel;

    @FXML
    private Label WinsLabel1;

    @FXML
    private Label WinsLabel2;

    @FXML
    private Label WinsLabel21;

    @FXML
    private ImageView backButton;

    @FXML
    private Label emailLabel;

    @FXML
    private DatePicker endingDateDatePicker;

    @FXML
    private TextField gameTextField;

    @FXML
    private HBox generatedPane;

    @FXML
    private Label nameLable;

    @FXML
    private TextField nameTextField;

    @FXML
    private Label passwordLable;

    @FXML
    private Button saveButton;

    @FXML
    private DatePicker startingDateDatePicker;

    @FXML
    private TextField stdNumTextField;

    @FXML
    private HBox studentsPane;

    @FXML
    private TextField teamsNumTextField;

    @FXML
    private HBox teamsPane;

    @FXML
    private TextField typeTextField;

    @FXML
    private Button generateButton;

    @FXML
    void GenerateButtonOnClicked(ActionEvent event) {

        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GeneratedTablesScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene registerAdminPage = new Scene(fxmlLoader);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setScene(registerAdminPage);
        stage.setTitle("Tournament Manager - Team Join");
        stage.show();

        // code for the elimination and roundrobin genereted tables


        // try{
        //     ObjectInputStream objInStreamTournament = new ObjectInputStream(new FileInputStream("src\\TournamentsBFile.dat"));
        //         HashMap<String, Tournament> tournamentHashMap = (HashMap<String,Tournament>) objInStreamTournament.readObject();
        //         objInStreamTournament.close();
        //         ObjectInputStream objectInStreamSt = new ObjectInputStream(new FileInputStream("src\\LogedinPerson.dat"));
        //         Admin admin = (Admin) objectInStreamSt.readObject();
        //         objectInStreamSt.close();

        //         ObjectInputStream getTournamentIDStream = new ObjectInputStream(new FileInputStream("src\\TournamentView.dat"));
        //         Tournament viewedTournament = (Tournament) getTournamentIDStream.readObject();
        //         getTournamentIDStream.close();

        //         if(viewedTournament.getType().equals("Elimination")){
        //             Team noOne = new Team();
        //             Elimination convertedToElim = new Elimination(viewedTournament.getName(),viewedTournament.getGameType(),viewedTournament.getType(),viewedTournament.getTournamentID(),noOne,viewedTournament.getStartingDate(),viewedTournament.getEndingDate(),viewedTournament.getTeams(),viewedTournament.getNumOfTeams(),viewedTournament.getStudents(),viewedTournament.getMembersPerTeam(),viewedTournament.getRegisterationStatus(),viewedTournament.getIsGenerated());
        //             convertedToElim.createMatchHistory();
        //             ScrollPane elimScrollpane = ElimnationTournementPage.createTourneyPage(convertedToElim);
        //             Scene scene = new Scene(elimScrollpane,1000,800);
        //             // Scene registerStudentPage = new Scene(fxmlLoader);
        //             Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
        //             stage.setScene(scene);
        //             stage.setTitle("Tournament Manager - Students View");
        //             stage.show();
                    
        //         }
        //         else{
        //             RoundRobin convertedToRoundRobin = new RoundRobin(viewedTournament.getName(),viewedTournament.getGameType(),viewedTournament.getType(),viewedTournament.getTournamentID(),viewedTournament.getWinner(),viewedTournament.getStartingDate(),viewedTournament.getEndingDate(),viewedTournament.getTeams(),viewedTournament.getNumOfTeams(),viewedTournament.getStudents(),viewedTournament.getMembersPerTeam(),viewedTournament.getRegisterationStatus(),viewedTournament.getIsGenerated());
        //             convertedToRoundRobin.createMatchHistory();
        //             convertedToRoundRobin.createTables();
        //             ScrollPane roundScrollpane = RoundRobinTournementPage.createTourneyPage(convertedToRoundRobin);
        //             Scene scene = new Scene(roundScrollpane,1000,800);
        //             Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
        //             stage.setScene(scene);
        //             stage.setTitle("Tournament Manager - Students View");
        //             stage.show();


        //         }

        // }
        // catch(Exception e){
        //     e.printStackTrace();
        // }

    }

    @FXML
    void ViweButtonOnClicked1(ActionEvent event) {

    }



    @FXML
    void ViewButtonOnClicked(ActionEvent event) {

    }

//    @FXML
//    void setTIDBackButton(MouseEvent event) {
//        Parent fxmlLoader = null;
//        try {
//            ObjectInputStream objInStreamLogedinPerson = new ObjectInputStream(new FileInputStream("src\\LogedinPerson.dat"));
//            Person person = (Person) objInStreamLogedinPerson.readObject();
//            objInStreamLogedinPerson.close();
//            if (person instanceof Student)
//                fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("src\\StudentHomeScene.fxml")));
//            else
//                fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("src\\AdminHomeScene.fxml")));
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        Scene homePage = new Scene(fxmlLoader);
//        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
//        stage.setScene(homePage);
//        stage.setTitle("Tournament Manager - Home Page");
//        stage.show();
//    }




    @FXML
    void saveButtonOnClicked(ActionEvent event) {

    }

    @FXML
    void setBackButton(MouseEvent event) {

        Parent fxmlLoader = null;
        try {
            ObjectInputStream objInStreamLogedinPerson = new ObjectInputStream(new FileInputStream("src\\LogedinPerson.dat"));
            Person person = (Person) objInStreamLogedinPerson.readObject();
            objInStreamLogedinPerson.close();
            if (person instanceof Student)
                fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StudentHomeScene.fxml")));
            else
                fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminHomeScene.fxml")));

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Scene registerPage = new Scene(fxmlLoader);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setScene(registerPage);
        stage.setTitle("Tournament Manager - Home Page");
        stage.show();

    }

    @FXML
    void studentsPaneOnClicked(MouseEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StudentsViewScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene registerStudentPage = new Scene(fxmlLoader);
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
        stage.setScene(registerStudentPage);
        stage.setTitle("Tournament Manager - Students View");
        stage.show();
    }

    @FXML
    void teamsPaneOnCklicked(MouseEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("TeamsViewScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene registerStudentPage = new Scene(fxmlLoader);
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
        stage.setScene(registerStudentPage);
        stage.setTitle("Tournament Manager - Teams View");
        stage.show();
    }

    @FXML
    void generatedPaneOnClicked(MouseEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GeneratedTablesScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene registerStudentPage = new Scene(fxmlLoader);
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
        stage.setScene(registerStudentPage);
        stage.setTitle("Tournament Manager - Generated Tables View");
        stage.show();
    }

    @FXML
    void EditButtonOnClicked(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObjectInputStream objInStreamLogedinPerson = new ObjectInputStream(new FileInputStream("src\\LogedinPerson.dat"));
            Person person = (Person) objInStreamLogedinPerson.readObject();
            objInStreamLogedinPerson.close();
            
            ObjectInputStream objInStreamTournament = new ObjectInputStream(new FileInputStream("src\\TournamentView.dat"));
            Tournament tournament = (Tournament) objInStreamTournament.readObject();
            IDLabel.setText(tournament.getTournamentID());
            typeTextField.setText(tournament.getType());
            gameTextField.setText(tournament.getGameType());
            teamsNumTextField.setText(String.valueOf(tournament.getNumOfTeams()));
            stdNumTextField.setText(String.valueOf(tournament.getMembersPerTeam()));
            nameTextField.setText(tournament.getName());
            StatusTF.setText(String.valueOf(tournament.getRegisterationStatus()));
//            WinnerTextField.setText((tournament.getWinner()).toString());

            if (person instanceof Admin){
                if(!tournament.getIsGenerated())
                    generateButton.setVisible(true);
                else
                    generateButton.setVisible(false);

                EditButton.setVisible(true);
            }else{
                generateButton.setVisible(false);
                EditButton.setVisible(false);
            }

            if(tournament.getIsGenerated())
                generatedPane.setVisible(true);
            else
                generatedPane.setVisible(false);



            typeTextField.setEditable(false);gameTextField.setEditable(false);teamsNumTextField.setEditable(false);
            stdNumTextField.setEditable(false);nameTextField.setEditable(false);StatusTF.setEditable(false);WinnerTextField.setEditable(false);


            objInStreamTournament.close();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void EditButtonExit(MouseEvent event) {
        EditButton.setOpacity(1);
    }
    @FXML
    void EditButtonMoved(MouseEvent event) {
        EditButton.setOpacity(0.8);
    }
    @FXML
    void backButtonExit(MouseEvent event) {
        backButton.setOpacity(1);
    }
    @FXML
    void backButtonMoved(MouseEvent event) {
        backButton.setOpacity(0.8);
    }
    @FXML
    void generateButtonExit(MouseEvent event) {
        generateButton.setOpacity(1);
    }
    @FXML
    void generateButtonMoved(MouseEvent event) {
        generateButton.setOpacity(0.8);
    }
    @FXML
    void generatedPaneExit(MouseEvent event) {
        generatedPane.setOpacity(1);
    }
    @FXML
    void generatedPaneMoved(MouseEvent event) {
        generatedPane.setOpacity(0.8);
    }
    @FXML
    void studentsPaneExit(MouseEvent event) {
        studentsPane.setOpacity(1);
    }
    @FXML
    void studentsPaneMoved(MouseEvent event) {
        studentsPane.setOpacity(0.8);
    }
    @FXML
    void teamsPaneExit(MouseEvent event) {
        teamsPane.setOpacity(1);
    }
    @FXML
    void teamsPaneMoved(MouseEvent event) {
        teamsPane.setOpacity(0.8);
    }
}

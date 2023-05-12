import classes.Person;
import classes.Student;
import classes.Team;
import classes.Tournament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class TeamsViewController implements Initializable {

    @FXML
    private Label IDLabel;

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
    private TextField gameTextField;

    @FXML
    private HBox generatedPane;

    @FXML
    private HBox infoPane;

    @FXML
    private TextField stdNumTextField;

    @FXML
    private HBox studentsPane;

    @FXML
    private TableColumn<Team, String> teamNameC;


    @FXML
    private TableColumn<Team, Integer> membersCountC;


    @FXML
    private TextField teamsNumTextField;

    @FXML
    private TableView<Team> teamsTableView;

    @FXML
    private TextField typeTextField;

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
    void infoPaneOnCklicked(MouseEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ViewTournamentScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene registerPage = new Scene(fxmlLoader);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setScene(registerPage);
        stage.setTitle("Tournament Manager - View Tournament");
        stage.show();
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObjectInputStream objInStreamTournament = new ObjectInputStream(new FileInputStream("src\\TournamentView.dat"));
            Tournament tournament = (Tournament) objInStreamTournament.readObject();
            IDLabel.setText(tournament.getTournamentID());
            typeTextField.setText(tournament.getType());
            gameTextField.setText(tournament.getGameType());
            teamsNumTextField.setText(String.valueOf(tournament.getNumOfTeams()));
            stdNumTextField.setText(String.valueOf(tournament.getMembersPerTeam()));

            if(tournament.getIsGenerated())
                generatedPane.setVisible(true);
            else
                generatedPane.setVisible(false);


            typeTextField.setEditable(false);gameTextField.setEditable(false);teamsNumTextField.setEditable(false);
            stdNumTextField.setEditable(false);

            membersCountC.setCellValueFactory(new PropertyValueFactory<>("numberOfMembers"));
            teamNameC.setCellValueFactory(new PropertyValueFactory<>("teamName"));

            ArrayList<Team> teamArrayList = tournament.getTeams();


            ObservableList<Team> observableList = FXCollections.observableArrayList(teamArrayList);
            teamsTableView.getItems().addAll(teamArrayList);
            teamsTableView.setItems(observableList);


            objInStreamTournament.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
    void generatedPaneExit(MouseEvent event) {
        generatedPane.setOpacity(1);
    }
    @FXML
    void generatedPaneMoved(MouseEvent event) {
        generatedPane.setOpacity(0.8);    }
    @FXML
    void infoPaneExit(MouseEvent event) {
        infoPane.setOpacity(1);
    }
    @FXML
    void infoPaneMoved(MouseEvent event) {
        infoPane.setOpacity(0.8);
    }
    @FXML
    void studentsPaneExit(MouseEvent event) {
        studentsPane.setOpacity(1);
    }
    @FXML
    void studentsPaneMoved(MouseEvent event) {
        studentsPane.setOpacity(0.8);
    }
}

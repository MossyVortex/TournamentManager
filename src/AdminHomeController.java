import classes.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminHomeController implements Initializable {

    @FXML
    private Button CreateTournamentButton;

    @FXML
    private BorderPane ViewProfilePane;

    @FXML
    private ComboBox<?> combo;

    @FXML
    private Label nameLable;

    @FXML
    private ImageView signOutIcon;

    @FXML
    private TableView<?> tournamentTableView;

    @FXML
    private Button viewTournamentButton;

    @FXML
    void CreateTournamentButtonOnClicked(ActionEvent event) {

        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("createTournamentScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene registerPage = new Scene(fxmlLoader);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setScene(registerPage);
        stage.setTitle("classes.Tournament Manager - Create classes.Tournament");
        stage.show();

    }

    @FXML
    void ViewTournamentButtonOnClicked(ActionEvent event) {

        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ViewTournamentScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene registerPage = new Scene(fxmlLoader);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setScene(registerPage);
        stage.setTitle("classes.Tournament Manager - View Tournament");
        stage.show();

    }

    @FXML
    void ViewProfilePaneOnClicked(MouseEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("EditAdminProfileScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene registerPage = new Scene(fxmlLoader);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setScene(registerPage);
        stage.setTitle("Tournament Manager - Edit Profile (Admin)");
        stage.show();

    }

    @FXML
    void signOutIconOnClicked(MouseEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene loginStudentPage = new Scene(fxmlLoader);
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
        stage.setScene(loginStudentPage);
        stage.setTitle("Tournament Manager - Login");
        stage.show();
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream("src\\LogedinPerson.dat"));
            Admin admin = (Admin) objInStream.readObject();

            nameLable.setText(admin.getName());
            objInStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

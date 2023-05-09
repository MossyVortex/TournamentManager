import classes.Student;
import classes.Tournament;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

public class AdminHomeController implements Initializable {

    @FXML
    private Button CreateTournamentButton;

    @FXML
    private TableColumn<Tournament, String> IDColumn;

    @FXML
    private BorderPane ViewProfilePane;

    @FXML
    private TableColumn<Tournament, LocalDate> endingDateColumn;

    @FXML
    private TableColumn<Tournament, String> gameColumn;

    @FXML
    private TableColumn<Tournament, String> nameColumn;

    @FXML
    private Label nameLable;

    @FXML
    private HBox registerStudentPane;

    @FXML
    private ImageView signOutIcon;

    @FXML
    private TableColumn<Tournament, LocalDate> startDateColumn;

    @FXML
    private TableColumn<Tournament, Boolean> statusColumn;

    @FXML
    private TableView<Tournament> tournamentTableView;

    @FXML
    private TableColumn<Tournament, String> typeColumn;

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
        stage.setTitle("Tournament Manager - Create Tournament");
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
    void registerStudentPaneOnClicked(MouseEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminRegisterStudent.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene registerStudentPage = new Scene(fxmlLoader);
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
        stage.setScene(registerStudentPage);
        stage.setTitle("Tournament Manager - Register");
        stage.show();
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream("src\\LogedinPerson.dat"));
            Admin admin = (Admin) objInStream.readObject();
            
            ObjectInputStream objInStreamTournament = new ObjectInputStream(new FileInputStream("src\\TournamentsBFile.dat"));
              HashMap<String,Tournament> tournamentHashMap = (HashMap<String,Tournament>) objInStreamTournament.readObject();
              ArrayList<Tournament> list = new ArrayList<>();

              tournamentHashMap.forEach((x,y) -> list.add(y));

              nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
              IDColumn.setCellValueFactory(new PropertyValueFactory<>("tournamentID"));
              typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
              gameColumn.setCellValueFactory(new PropertyValueFactory<>("gameType"));
              statusColumn.setCellValueFactory(new PropertyValueFactory<>("registerationStatus"));
              startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startingDate"));
              endingDateColumn.setCellValueFactory(new PropertyValueFactory<>("endingDate"));

              ObservableList<Tournament> observableList = FXCollections.observableArrayList(list);
              tournamentTableView.getItems().addAll(list);
              tournamentTableView.setItems(observableList);
              objInStreamTournament.close();

            nameLable.setText(admin.getName());
            objInStream.close();
            tournamentTableView.setRowFactory( tv -> {
                TableRow<Tournament> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                        Tournament rowData = row.getItem();
                        try {
                            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("src\\TournamentView.dat"));
                            objectOutputStream.writeObject(rowData);
                            objectOutputStream.close();

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
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                return row ;
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

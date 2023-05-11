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
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;


public class StudentHomeController implements Initializable {

    @FXML
    private Button JoinTournamentButton;

    @FXML
    private BorderPane ViewProfilePane;

    @FXML
    private ComboBox<String> combo;

    @FXML
    private Label nameLable;

    @FXML
    private ImageView signOutIcon;

    @FXML
    private Button viewTournamentsButton;

    @FXML
    private TableView<Tournament> tournamentTableView;

    @FXML
    private TableColumn<Tournament, String> IDColumn;

    @FXML
    private TableColumn<Tournament, LocalDate> endingDateColumn;

    @FXML
    private TableColumn<Tournament, String> gameColumn;

    @FXML
    private TableColumn<Tournament, String> nameColumn;

    @FXML
    private TableColumn<Tournament, LocalDate> startDateColumn;

    @FXML
    private TableColumn<Tournament, Boolean> statusColumn;

    @FXML
    private TableColumn<Tournament, String> typeColumn;

    @FXML
    private RadioButton yourTournamentsRB;

    @FXML
    private HBox machesHB;

    @FXML
    void YourTournamentsRBActive(ActionEvent event) {

        try {
            ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream("src\\LogedinPerson.dat"));
            Student student = (Student) objInStream.readObject();
            objInStream.close();

            if (!yourTournamentsRB.isSelected()) {
                ObjectInputStream objInStreamTournament = new ObjectInputStream(new FileInputStream("src\\TournamentsBFile.dat"));
                HashMap<String, Tournament> tournamentHashMap = (HashMap<String, Tournament>) objInStreamTournament.readObject();
                ArrayList<Tournament> list = new ArrayList<>();
                tournamentHashMap.forEach((x, y) -> list.add(y));
                setTableView(list);
                objInStreamTournament.close();
            }
            else {
//                ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src\\StudentsTournaments.dat"));
//                HashMap<String, ArrayList<Tournament>> stringArrayListHashMap = (HashMap<String, ArrayList<Tournament>>) objectInputStream.readObject();
//                objectInputStream.close();
//                if (stringArrayListHashMap.containsKey(student.getUserName()))
//                    setTableView(stringArrayListHashMap.get(student.getUserName()));
//                else
//                    setTableView(null);

            }


        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void JoinTournamentButtonOnClicked(ActionEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("JoinTournamentScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene registerPage = new Scene(fxmlLoader);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setScene(registerPage);
        stage.setTitle("classes.Tournament Manager - Join Tournament");
        stage.show();

    }

    @FXML
    void ViewProfilePaneOnClicked(MouseEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("EditStudentProfileScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene registerPage = new Scene(fxmlLoader);
        Stage stage = (Stage) (((Node) event.getSource()).getScene().getWindow());
        stage.setScene(registerPage);
        stage.setTitle("Tournament Manager - Edit Profile (Student)");
        stage.show();
    }

    @FXML
    void viewTournamentsButtonOnClicked(ActionEvent event) {

        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("EnterTournamentIDToViewScene2.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene homePage = new Scene(fxmlLoader);
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
        stage.setScene(homePage);
        stage.setTitle("Tournament Manager - View Tournaments");
        stage.show();


    }

    @FXML
    void signOutIconOncklicked(MouseEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene loginStudentPage = new Scene(fxmlLoader);
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
        stage.setScene(loginStudentPage);
        stage.setTitle("classes.Tournament Manager - Login");
        stage.show();
    }

    @FXML
    void nameLableSetText(InputMethodEvent event) {
//        try {
//            ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream("LogedinPerson.dat"));
//            Student student = (Student) objInStream.readObject();
//
//            nameLable.setText(student.getName());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }


    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
          try {
            ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream("src\\LogedinPerson.dat"));
            Student student = (Student) objInStream.readObject();
            objInStream.close();

              ObjectInputStream objInStreamTournament = new ObjectInputStream(new FileInputStream("src\\TournamentsBFile.dat"));
              HashMap<String,Tournament> tournamentHashMap = (HashMap<String,Tournament>) objInStreamTournament.readObject();
              ArrayList<Tournament> list = new ArrayList<>();
              tournamentHashMap.forEach((x,y) -> list.add(y));
              setTableView(list);
              objInStreamTournament.close();


              nameLable.setText(student.getName());

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

    public void setTableView(ArrayList<Tournament> tournaments){
        if (tournaments==null)
            tournamentTableView.getItems().clear();
        else {
            tournamentTableView.getItems().clear();
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            IDColumn.setCellValueFactory(new PropertyValueFactory<>("tournamentID"));
            typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
            gameColumn.setCellValueFactory(new PropertyValueFactory<>("gameType"));
            statusColumn.setCellValueFactory(new PropertyValueFactory<>("registerationStatus"));
            startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startingDate"));
            endingDateColumn.setCellValueFactory(new PropertyValueFactory<>("endingDate"));

            ObservableList<Tournament> observableList = FXCollections.observableArrayList(tournaments);
            tournamentTableView.getItems().addAll(tournaments);
            tournamentTableView.setItems(observableList);
        }
    }

}

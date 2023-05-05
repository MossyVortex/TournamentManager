import java.io.IOException;
import java.util.Objects;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class ViewTournamentController {

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
    private ImageView backButton;

    @FXML
    private Label emailLabel;

    @FXML
    private DatePicker endingDateDatePicker;

    @FXML
    private TextField gameTextField;

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
    private HBox generatedPane;

    @FXML
    private TableColumn<?, ?> HeightC;

    @FXML
    private TableColumn<?, ?> IDC;

    @FXML
    private TableColumn<?, ?> WeightC;

    @FXML
    private TableColumn<?, ?> emailC;

    @FXML
    private HBox infoPane;

    @FXML
    private TableColumn<?, ?> phoneC;

    @FXML
    private TableColumn<?, ?> studentNameC;

    @FXML
    private TableView<?> teamsTableView;

    @FXML
    private TableColumn<?, ?> teamNameC;

    @FXML
    private TextField typeTextField;


    @FXML
    void infoPaneOnCklicked(MouseEvent event) {

    }


    @FXML
    void editButtonOnClicked(MouseEvent event) {

    }

    @FXML
    void saveButtonOnClicked(ActionEvent event) {

    }

    @FXML
    void setBackButton(MouseEvent event) {

        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AdminHomeScene.fxml")));
        } catch (IOException e) {
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

    }

    @FXML
    void teamsPaneOnCklicked(MouseEvent event) {

    }

    @FXML
    void generatedPaneOnClicked(MouseEvent event) {

    }

    @FXML
    void EditButtonOnClicked(MouseEvent event) {

    }

}

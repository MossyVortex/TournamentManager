import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class JoinTournamentController {

    @FXML
    private ImageView backTIDButton;

    @FXML
    private RadioButton individualRB;

    @FXML
    private Button joinButton;

    @FXML
    private RadioButton teamRB;

    @FXML
    private TextField tournamentIDTextField;

    @FXML
    private ImageView backTeamJoinButton;

    @FXML
    private Button enterButton;


    @FXML
    private TextField memberIDTextField;

    @FXML
    private Label remaningNumLable;

    @FXML
    void enterButtonOnClicked(ActionEvent event) {

    }

    @FXML
    void setTeamBackButton(MouseEvent event) {

    }

    @FXML
    void individualRBOn(ActionEvent event) {
        teamRB.setSelected(false);
    }

    @FXML
    void joinButtonOnClicked(ActionEvent event) {

    }

    @FXML
    void setTIDBackButton(MouseEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("StudentHomeScene.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene homePage = new Scene(fxmlLoader);
        Stage stage = (Stage)(((Node)event.getSource()).getScene().getWindow()) ;
        stage.setScene(homePage);
        stage.setTitle("Tournament Manager - Home Page");
        stage.show();
    }

    @FXML
    void teamRBOn(ActionEvent event) {
        individualRB.setSelected(false);
    }

    public void ViweButtonOnClicked1(ActionEvent actionEvent) {
    }
}

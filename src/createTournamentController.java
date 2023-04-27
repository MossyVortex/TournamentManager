import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class createTournamentController {

    @FXML
    private Label chooseTournamentTypeLabel;

    @FXML
    private Label createTournamentLabel;

    @FXML
    private ImageView createTournamentLogo;

    @FXML
    private RadioButton eliminationRadioButton;

    @FXML
    private Label endDateLabel;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private TextField gameTypeTextField;

    @FXML
    private TextField maxNumOfTeamsTextField;

    @FXML
    private RadioButton roundRobinRadioButton;

    @FXML
    private Label startDateLabel;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private TextField tournamentNameTextField;

    @FXML
    void eliminationRadioButtonOnClicked(ActionEvent event) {

    }

    @FXML
    void getEndDate(ActionEvent event) {

    }

    @FXML
    void getStartDate(ActionEvent event) {

    }

    @FXML
    void roundRobinRadioButtonOnClicked(ActionEvent event) {

    }

}

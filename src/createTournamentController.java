import java.time.LocalDate;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class createTournamentController {

    @FXML
    private Label chooseTournamentTypeLabel;

    @FXML
    private Button createTournamentButton;

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
    private Label tournamentNameLabel;

    @FXML
    private TextField tournamentNameTextField;

    private LocalDate startDate, enDate; // IDK if this is correct :)

    @FXML
    void createTournamentOnClicked(ActionEvent event) {
        // choose type
        if(eliminationRadioButton.isDisabled() & roundRobinRadioButton.isDisabled()){

            // this is when the user did not choose any of the options
            // make the radio buttons red
        }
        else if( !(eliminationRadioButton.isDisabled())){

            // create elimination tournament

            // Elimination elimTourObj = new Elimination();

            // elimTourObj.setName(tournamentNameTextField.getText());
            // elimTourObj.setStartDate // we need these setters in both elimination and roundrobin!
            // elimTourObj.setEndDate
            // ... etc

        }
        else if( !(roundRobinRadioButton.isDisabled())){

            // create roundrobin tournament

        }
        else{
            // this is when somehow the user chose both options

            // make the radio buttons red
        }
    }

    @FXML
    void eliminationRadioButtonOnClicked(ActionEvent event) {

    }

    @FXML
    void getEndDate(ActionEvent event) {

        LocalDate endDateVariable = endDatePicker.getValue(); // IDK if this is correct :)
        this.enDate = endDateVariable;

    }

    @FXML
    void getStartDate(ActionEvent event) {

        LocalDate startDateVariable = startDatePicker.getValue(); // IDK if this is correct :)
        this.startDate = startDateVariable;

    }

    @FXML
    void roundRobinRadioButtonOnClicked(ActionEvent event) {

    }

}
